#include <iostream>
#include <string>
#include <fstream>
#include <sstream>

#include "cache.h"

using namespace std;

int main()
{
    char instr;
    string line, addr;
    int s, b, n;
    bool p;

    //Post recording added user input field
    cout << "For all input, ensure a multiple of 2 is used" << endl;
    cout << "How many sets in the cache: ";
    cin >> s;
    cout << "How many blocks per set in the cache: ";
    cin >> b;
    cout << "How many bytes per block: ";
    cin >> n;
    //Only ask about policy if necessary
    if (b != s || s == 1)
    {
        cout << "1 for LRU, 0 for FIFO: ";
        cin >> p;
    }
    //b currently holds the total blocks, but it is meant to hold the blocks per set
    b = b * s;
    Cache c = Cache(s, b, n, p);

    //Retrieve contents of the file
    ifstream file("gcc.trace");

    if (file.is_open())
    {
        while (getline(file, line))
        {
            //Extract the address from the line
            istringstream operation(line);
            operation >> instr >> addr;

            //Convert address to 32 bit binary
            addr = toBin(addr);
            c.request(addr);
        }
        //Display hit rate
        c.hitRate();
    }
    else
    {
        cout << "File error" << endl;
        return 1;
    }

    return 0;
}
