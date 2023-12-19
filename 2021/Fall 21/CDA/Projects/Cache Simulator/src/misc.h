//*********** EXTRA FUNCTIONS ***********//
#include <sstream>
#include <bitset>

using namespace std;

//Convert the hex address to binary, referenced from Stack Overflow
//https://stackoverflow.com/questions/18310952/convert-strings-between-hex-format-and-binary-format
string toBin(string num, bool h = true)
{
    stringstream s;
    if (h) //Convert hex to bin
        s << hex << num;
    else //Convert dec to bin
        s << dec << num;
    unsigned n;
    s >> n;
    bitset<32> b(n);
    return b.to_string();
}

//Takes a binary value and converts it to decimal
int toDec(int n)
{
    int dec = 0;
    // Initializing base value to 1, i.e 2^0
    int base = 1;
    int temp = n;
    while (temp)
    {
        int last_digit = temp % 10;
        temp = temp / 10;

        dec += last_digit * base;

        base = base * 2;
    }

    return dec;
}