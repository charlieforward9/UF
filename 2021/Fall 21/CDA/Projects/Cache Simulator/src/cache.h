#pragma once

#include <cmath>
#include <unordered_map>
#include <vector>
#include <queue>
#include <algorithm>

#include "misc.h"

using namespace std;

class Cache
{
    const int MEMSIZE = 32;
    const int BYTE = 8;

    int sets, blocks, bytesPerBlock;
    int offsetSize, tagSize, setSize, blocksPerSet;;

    int hits, misses;


    //True: LRU
    //False: FIFO
    bool policy;

    //Line:         Set         LRU Tag            FIFO tag
    unordered_map<string, pair<vector<string>, queue<string>>> storage;

public:
    //Contstuctor
    Cache();
    Cache(int s, int b, int n, bool p);

    void init();
    void request(string address);

    int getOffsetSize();
    int getSetSize();
    int getTagSize();
    int getBlocksPerSet();

    void hit();
    void miss();
    void hitRate();

    void runFull(string t);
    void runDirect(string s, string t);
    void runSet(string s, string t);
    void runLru(vector<string> &v, string t);
    void runFifo(queue<string> &q, string t);
};

//*********** DEFINITIONS ***********//
Cache::Cache()
{
    hits = misses = 0;
    sets = blocks = 1;
    bytesPerBlock = 4;
    policy = true;

    offsetSize = getOffsetSize();
    tagSize = getTagSize();
    setSize = getSetSize();
    blocksPerSet = getBlocksPerSet();

    init();
}

Cache::Cache(int s, int b, int n, bool p)
{
    hits = misses = 0;
    sets = s;
    blocks = b;
    bytesPerBlock = n;
    policy = p;

    offsetSize = getOffsetSize();
    setSize = getSetSize();
    tagSize = getTagSize();
    blocksPerSet = getBlocksPerSet();

    init();

    cout << sets << " set cache" << endl
         << blocksPerSet << " block per set " << endl
         << bytesPerBlock << " bytes per block  " << endl;
    if (sets != blocks || sets == 1)
    {
        string pol = policy ? "LRU " : "FIFO";
        cout << "Policy: " << pol << endl;
    }
}

void Cache::init()
{
    //Allocate sufficient blocks for each set in the cache
    for (int setNum = 0; setNum < sets; setNum++)
    {
        vector<string> lru(blocksPerSet, "");
        deque<string> temp(blocksPerSet, "");
        queue<string> fifo(temp);

        //Convert to binary for simple indexing
        string i = toBin(to_string(setNum), false);

        //Concatenate the string to the length of setSize
        i = i.substr(i.size() - getSetSize(), i.size() - 1);

        //For fully associative case, there will only be one set
        if (!i.length())
            i = "0";

        storage[i] = make_pair(lru, fifo);
    }
}

//Given a memory address, find it it exists in the cache, if not, put it in.
void Cache::request(string address)
{
    string tag, set;

    //Get the tag
    tag = address.substr(0, tagSize - 1);
    set = address.substr(tagSize, setSize);

    if (sets == 1)
        //Fully Associative
        runFull(tag);
    else if (blocks == sets)
        //Direct Mapped
        runDirect(set, tag);
    else
        //Set Associative
        runSet(set, tag);
}

int Cache::getOffsetSize()
{
    return log2(bytesPerBlock);
}

int Cache::getSetSize()
{
    return log2(sets);
}

int Cache::getTagSize()
{
    return MEMSIZE - offsetSize - setSize;
}

int Cache::getBlocksPerSet()
{
    return blocks / sets;
}

void Cache::hit()
{
    hits++;
}

void Cache::miss()
{
    misses++;
}

void Cache::hitRate()
{
    if (hits + misses)
        cout << "Hit rate: " << (((float)hits / (hits + misses))) * 100 << '%' << endl;
    else
        cout << "No data to show" << endl;
}

void Cache::runFull(string t)
{
    string set = "0";
    auto i = storage.find(set);

    if (policy)
        runLru(i->second.first, t); //LRU
    else
        runFifo(i->second.second, t); //FIFO
}

void Cache::runDirect(string s, string t)
{
    //cout << "s: " << s << endl;
    auto i = storage.find(s);
    //cout << i->second.first.at(0) << " AND " << t << endl;
    if (i->second.first.at(0) == t)
    {
        // cout << "Hit!" << endl;
        hit();
    }
    else
    {
        // cout << "Miss!!" << endl;
        miss();
        i->second.first.at(0) = t;
    }
}

void Cache::runSet(string s, string t)
{
    //cout << "s: " << s << endl;
    auto i = storage.find(s);

    if (policy)
        runLru(i->second.first, t); //LRU
    else
        runFifo(i->second.second, t); //FIFO
}

//LRU check
void Cache::runLru(vector<string> &v, string t)
{
    for (int i = 0; i < v.size(); i++)
    {
        if (v.at(i) == t)
        {
            v.erase(v.begin() + i);
            v.push_back(t);
            hit();
        }
    }
    //If nothing was found, remove the first element and put the new one on the back
    v.erase(v.begin());
    v.push_back(t);
    miss();
}

//FIFO check
void Cache::runFifo(queue<string> &q, string t)
{
    queue<string> temp(q);
    bool h = false;

    while (!temp.empty())
    {
        if (t == temp.front())
        {
            hit();
        }
        temp.pop();
    }

    if (!h)
    {
        q.pop();
        q.push(t);
        miss();
    }
}