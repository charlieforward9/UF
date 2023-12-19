//Resources:
// Lines 29-39, 82-85: https://stackoverflow.com/questions/4485203/c-stl-using-map-with-priority-queue
//                     to overwrite the comparison operator for a pair in a priority queue
// Lines 181-193: https://www.geeksforgeeks.org/huffman-decoding/
//                to traverse through a huffman tree and register the proper code

#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
#include <queue>
#define N nullptr

using namespace std;

class huffman_tree {

    typedef pair<char, int> PAIR;

    struct treeNode {
        PAIR data;
        treeNode* left{};
        treeNode* right{};

        treeNode(PAIR &data, treeNode *left, treeNode *right) : data(data), left(left), right(right){}
    };

    //Comparison functors for priority queue
    struct comparePair {
        bool operator()(const pair<char, int> &a, const pair<char, int> &b) {
            return a.second >= b.second;
        }
    };
    struct compareTree {
        bool operator()(const treeNode* a, const treeNode* b) {
            return a->data.second >= b->data.second;
        }
    };

    //Data structures
    unordered_map<char, string> charCodes;
    treeNode* huffman;

public:

    //Helper functions
    treeNode* checkSubTree(PAIR p, priority_queue<treeNode *, vector<treeNode*>, compareTree> &sub) {
        //Check if the left pair is the head of a subtree
        if (!sub.empty() && p == sub.top()->data) {
            treeNode* n = sub.top();
            sub.pop();
            return n;
        } else if (huffman && p == huffman->data)
            return huffman;
        else
            return new treeNode(p, N, N);
    }
    void buildTree(priority_queue<PAIR, vector<PAIR>, comparePair> &pq, priority_queue<treeNode *, vector<treeNode*>, compareTree> &sub) {
        //Create tree from queue
        PAIR p1, p2;
        treeNode *l, *r;
        while (!pq.empty()) {
            p1 = pq.top();
            pq.pop();

            //Check if the left pair is the head of a subtree
            l = checkSubTree(p1, sub);

            if (!pq.empty()) {
                p2 = pq.top();
                pq.pop();

                //Check if the right pair is the head of a subtree
                r = checkSubTree(p2, sub);

            } else break;

            //Create the parent of the left and right pair and store it accordingly
            int sum = p1.second + p2.second;
            PAIR par(' ', sum);
            pq.push(par);
            if (huffman && l != huffman && r != huffman)
                sub.push(huffman);

            huffman = new treeNode(par, l, r);
        }
    }
    void makeCode(treeNode* h, string s) {
        if (!h->left&& !h->right) {
            charCodes.emplace(h->data.first, s);
            return;
        }
        makeCode(h->left, s + '0');
        makeCode(h->right, s + '1');
    }

    /*
    Preconditions: input is a string of characters with ascii values 0-127
    Postconditions: Reads the characters of input and constructs a
    huffman tree based on the character frequencies of the file contents
    */
    huffman_tree(const string& input) {
        huffman = N;

        //Convert string into a freqency table
        unordered_map<char, int> frequency;
        for (char c : input) {
            if (frequency.find(c) != frequency.end())
                frequency[c]++;
            else
                frequency.emplace(c, 1);
        }

        //Convert table to priority queue using functor (referenced from stackOverflow, citation above)
        priority_queue<PAIR, vector<PAIR>, comparePair> pq(frequency.begin(), frequency.end());

        //Edge cases
        //Empty string, do nothing
        if (pq.empty()) return;
        else if (pq.size() == 1) { //1 char, both children have the same value. Makes no difference in the end.
            PAIR p(pq.top());
            auto* n = new treeNode(p, N, N);
            huffman = new treeNode(p, n, n);
            pq.pop();
        } else {
            //Create a backup priority queue for huffman subtrees that need to be temporarily stored
            priority_queue<treeNode *, vector<treeNode *>, compareTree> temphuff;
            //Build tree
            buildTree(pq, temphuff);

        }
        //Fill map with character codes
        makeCode(huffman, "");
    }

    /*
    Preconditions: input is a character with ascii value between 0-127
    Postconditions: Returns the Huffman code for character if character is in the tree
    and an empty string otherwise.
    */
    string get_character_code(char character) const {
        if (charCodes.find(character) != charCodes.end())
            return charCodes.at(character);
        return "";
    }


    /*
    Preconditions: input is a string of characters with ascii values 0-127
    Postconditions: Returns the Huffman encoding for the contents of file_name
    if file name exists and an empty string otherwise.
    If the file contains letters not present in the huffman_tree,
    return an empty string
    */

    string encode(const string& input) const {
        string code;
        for (auto c : input) {
            if (charCodes.find(c) == charCodes.end()) return "";
            code += charCodes.at(c);
        }
        return code;
    }


    /*
    Preconditions: string_to_decode is a string containing Huffman-encoded text
    Postconditions: Returns the plaintext represented by the string if the string
    is a valid Huffman encoding and an empty string otherwise
    */

    //Learned through GfG it is impossible to decode without the tree. (Cited above)
    string decode(const string& string_to_decode) const {
        string text;
        treeNode* n = huffman;
        for (auto c : string_to_decode) {
            n = (c == '0') ? n->left : n->right;
            if (n->left == N && n->right == N) {
                text += n->data.first;
                n = huffman;
            }
        }
        return text;
    }
};

int main() {
      const string &test = "abc";
      huffman_tree tree = huffman_tree(test);
      string e = tree.encode(test);
      cout << e << endl;
      cout << tree.decode(e) << endl;
      string t = "e";
      cout << tree.encode(t);
      return 0;
}
