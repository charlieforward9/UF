#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

//Globals
int i = 0;
const float RANGE = 255.0;
const float ROUNDER = 0.5f;
const int NRANGE = 1;

//File Format
struct Image {
    struct Pixel {      //Pixel format
        unsigned char R = 0;
        unsigned char G = 0;
        unsigned char B = 0;
    };
    char idLen = 0;        //Size of Image ID field
    char colorMap = 0;     //Is a color map included?
    char imgType = 0;      //Compressed? True Color? Greyscale?
    short colorMapOrigin = 0;//0 in our case
    short colorMapLength = 0;// ^
    char colorMapDepth = 0;
    short xOrigin = 0;
    short yOrigin = 0;
    short imgW = 0;
    short imgH = 0;
    char pixelDepth = 0; //8,16,24,32
    char imgDescriptor = 0;
    vector<Pixel> imgData;
};

Image readFile(const string &filename) {
    ifstream inFile;
    inFile.open(filename, ios_base::binary);

    //Create a new Image object
    Image imgObj;

    //Read in the header data to the header object (18 Bytes Total)
    inFile.read((char*)&imgObj.idLen, sizeof(imgObj.idLen));
    inFile.read((char*)&imgObj.colorMap, sizeof(imgObj.colorMap));
    inFile.read((char*)&imgObj.imgType, sizeof(imgObj.imgType));
    inFile.read((char*)&imgObj.colorMapOrigin, sizeof(imgObj.colorMapOrigin));
    inFile.read((char*)&imgObj.colorMapLength, sizeof(imgObj.colorMapLength));
    inFile.read((char*)&imgObj.colorMapDepth, sizeof(imgObj.colorMapDepth));
    inFile.read((char*)&imgObj.xOrigin, sizeof(imgObj.xOrigin));
    inFile.read((char*)&imgObj.yOrigin, sizeof(imgObj.yOrigin));
    inFile.read((char*)&imgObj.imgW, sizeof(imgObj.imgW));
    inFile.read((char*)&imgObj.imgH, sizeof(imgObj.imgH));
    inFile.read((char*)&imgObj.pixelDepth, sizeof(imgObj.pixelDepth));
    inFile.read((char*)&imgObj.imgDescriptor, sizeof(imgObj.imgDescriptor));

    //Read the image data
    for (i = 0; i < (imgObj.imgW * imgObj.imgH); i++) {
        //Create a pixel object
        Image::Pixel pixO;
        //Add pixel data to pixel object
        inFile.read((char*)&pixO.B, sizeof(pixO.B));
        inFile.read((char*)&pixO.G, sizeof(pixO.G));
        inFile.read((char*)&pixO.R, sizeof(pixO.R));
        //Push each pixel object to the vector
        imgObj.imgData.push_back(pixO);
    }
    //Close the file
    inFile.close();
    //Return the image object
    return imgObj;
}                   //Read the data into memory
void writeFile(const string &filename, Image &imgObj) {
    //Create a new file
    ofstream outFile;
    outFile.open(filename, ios_base::binary);

    //Write the header data to the file
    outFile.write((char*)&imgObj.idLen, sizeof(imgObj.idLen));
    outFile.write((char*)&imgObj.colorMap, sizeof(imgObj.colorMap));
    outFile.write((char*)&imgObj.imgType, sizeof(imgObj.imgType));
    outFile.write((char*)&imgObj.colorMapOrigin, sizeof(imgObj.colorMapOrigin));
    outFile.write((char*)&imgObj.colorMapLength, sizeof(imgObj.colorMapLength));
    outFile.write((char*)&imgObj.colorMapDepth, sizeof(imgObj.colorMapDepth));
    outFile.write((char*)&imgObj.xOrigin, sizeof(imgObj.xOrigin));
    outFile.write((char*)&imgObj.yOrigin, sizeof(imgObj.yOrigin));
    outFile.write((char*)&imgObj.imgW, sizeof(imgObj.imgW));
    outFile.write((char*)&imgObj.imgH, sizeof(imgObj.imgH));
    outFile.write((char*)&imgObj.pixelDepth, sizeof(imgObj.pixelDepth));
    outFile.write((char*)&imgObj.imgDescriptor, sizeof(imgObj.imgDescriptor));

    //Write the image data to the file
    for (i = 0; i < (imgObj.imgW * imgObj.imgH); i++) {
        outFile.write((char*)&imgObj.imgData[i].B, sizeof(imgObj.imgData[i].B));
        outFile.write((char*)&imgObj.imgData[i].G, sizeof(imgObj.imgData[i].G));
        outFile.write((char*)&imgObj.imgData[i].R, sizeof(imgObj.imgData[i].R));
    }
    //Close the file
    outFile.close();
}

Image multiply(Image &top, Image &bottom) {
    //Loop through entire image
    for (i = 0; i < top.imgData.size(); i++) {

        //fixed: cannot normalize only one value because some of the patterns have color
        //Normalize all the values from the bottom image 
        float bBval = bottom.imgData.at(i).B / RANGE;
        float bGval = bottom.imgData.at(i).G / RANGE;
        float bRval = bottom.imgData.at(i).R / RANGE;

        //top pixels * bottom normalized pixels, assigned to floats to multiply then converted back to unsigned chars
        top.imgData.at(i).B = (unsigned char)((float)top.imgData.at(i).B * bBval + ROUNDER);
        top.imgData.at(i).G = (unsigned char)((float)top.imgData.at(i).G * bGval + ROUNDER);
        top.imgData.at(i).R = (unsigned char)((float)top.imgData.at(i).R * bRval + ROUNDER);
    }
    return top;
}

Image subtract(Image &top, Image &bottom) {
    //Loop through entire image
    for (i = 0; i < top.imgData.size(); i++) {
        //Subtract B values
        int bVal = (int)bottom.imgData.at(i).B - (int)top.imgData.at(i).B;
        if (bVal < 0) {
            bVal = 0;
        }
        bottom.imgData.at(i).B = (unsigned char)bVal;

        //Subtract G values
        int gVal = (int)bottom.imgData.at(i).G - (int)top.imgData.at(i).G;
        if (gVal < 0) {
            gVal = 0;
        }
        bottom.imgData.at(i).G = (unsigned char)gVal;

        //Subtract R values
        int rVal = (int)bottom.imgData.at(i).R - (int)top.imgData.at(i).R;
        if (rVal < 0) {
            rVal = 0;
        }
        bottom.imgData.at(i).R = (unsigned char)rVal;
    }
    return bottom;
}

Image screen(Image &top, Image &bottom) {
    //Loop through entire image
    for (i = 0; i < top.imgData.size(); i++) {

        //Normalize all values
        float aBval = top.imgData.at(i).B / RANGE;
        float aGval = top.imgData.at(i).G / RANGE;
        float aRval = top.imgData.at(i).R / RANGE;

        float bBval = bottom.imgData.at(i).B / RANGE;
        float bGval = bottom.imgData.at(i).G / RANGE;
        float bRval = bottom.imgData.at(i).R / RANGE;


        bottom.imgData.at(i).B = (unsigned char) RANGE * (NRANGE - (NRANGE - aBval) * (NRANGE - bBval)) + ROUNDER;
        bottom.imgData.at(i).G = (unsigned char) RANGE * (NRANGE - (NRANGE - aGval) * (NRANGE - bGval)) + ROUNDER;
        bottom.imgData.at(i).R = (unsigned char) RANGE * (NRANGE - (NRANGE - aRval) * (NRANGE - bRval)) + ROUNDER;
    }
    return bottom;
}

Image overlay(Image &top, Image &bottom) {
    //Loop through entire image
    for (i = 0; i < top.imgData.size(); i++) {

        //Normalize
        float aBval = top.imgData.at(i).B / RANGE;
        float aGval = top.imgData.at(i).G / RANGE;
        float aRval = top.imgData.at(i).R / RANGE;

        float bBval = bottom.imgData.at(i).B / RANGE;
        float bGval = bottom.imgData.at(i).G / RANGE;
        float bRval = bottom.imgData.at(i).R / RANGE;

        //If lighter, screen blend
        if (bBval > .5) {
            top.imgData.at(i).B = (unsigned char) RANGE * (NRANGE - 2 * (NRANGE - aBval) * (NRANGE - bBval)) + ROUNDER;
            top.imgData.at(i).G = (unsigned char) RANGE * (NRANGE - 2 * (NRANGE - aGval) * (NRANGE - bGval)) + ROUNDER;
            top.imgData.at(i).R = (unsigned char) RANGE * (NRANGE - 2 * (NRANGE - aRval) * (NRANGE - bRval)) + ROUNDER;
        } else if (bBval <= .5)  {//If darker, multiply blend

            top.imgData.at(i).B = (unsigned char) RANGE * (2 * aBval * bBval) + ROUNDER;
            top.imgData.at(i).G = (unsigned char) RANGE * (2 * aGval * bGval) + ROUNDER;
            top.imgData.at(i).R = (unsigned char) RANGE * (2 * aRval * bRval) + ROUNDER;
        }
    }
    return top;
}
void writeFile(const string &filename, Image &imgObj, int color) {
    //Create a new file
    ofstream outFile;
    outFile.open(filename, ios_base::binary);

    //Write the header data to the file
    outFile.write((char*)&imgObj.idLen, sizeof(imgObj.idLen));
    outFile.write((char*)&imgObj.colorMap, sizeof(imgObj.colorMap));
    outFile.write((char*)&imgObj.imgType, sizeof(imgObj.imgType));
    outFile.write((char*)&imgObj.colorMapOrigin, sizeof(imgObj.colorMapOrigin));
    outFile.write((char*)&imgObj.colorMapLength, sizeof(imgObj.colorMapLength));
    outFile.write((char*)&imgObj.colorMapDepth, sizeof(imgObj.colorMapDepth));
    outFile.write((char*)&imgObj.xOrigin, sizeof(imgObj.xOrigin));
    outFile.write((char*)&imgObj.yOrigin, sizeof(imgObj.yOrigin));
    outFile.write((char*)&imgObj.imgW, sizeof(imgObj.imgW));
    outFile.write((char*)&imgObj.imgH, sizeof(imgObj.imgH));
    outFile.write((char*)&imgObj.pixelDepth, sizeof(imgObj.pixelDepth));
    outFile.write((char*)&imgObj.imgDescriptor, sizeof(imgObj.imgDescriptor));

    //0 = B
    //1 = G
    //2 = R
    //Write the image data to the file
    for (i = 0; i < (imgObj.imgW * imgObj.imgH); i++) {
        if (color == 0) {
            outFile.write((char*) &imgObj.imgData[i].B, sizeof(imgObj.imgData[i].B));
            outFile.write((char*) &imgObj.imgData[i].B, sizeof(imgObj.imgData[i].B));
            outFile.write((char*) &imgObj.imgData[i].B, sizeof(imgObj.imgData[i].B));
        }
        if (color == 1) {
            outFile.write((char*) &imgObj.imgData[i].G, sizeof(imgObj.imgData[i].G));
            outFile.write((char*) &imgObj.imgData[i].G, sizeof(imgObj.imgData[i].G));
            outFile.write((char*) &imgObj.imgData[i].G, sizeof(imgObj.imgData[i].G));
        }
        if (color == 2) {
            outFile.write((char*) &imgObj.imgData[i].R, sizeof(imgObj.imgData[i].R));
            outFile.write((char*) &imgObj.imgData[i].R, sizeof(imgObj.imgData[i].R));
            outFile.write((char*) &imgObj.imgData[i].R, sizeof(imgObj.imgData[i].R));
        }
    }
    //Close the file
    outFile.close();
}   

void combineImages(const string &filename, Image &red, Image &green, Image &blue) {
    //Create a new file
    ofstream outFile;
    outFile.open(filename, ios_base::binary);

    //Write the header data to the file
    outFile.write((char*)&red.idLen, sizeof(red.idLen));
    outFile.write((char*)&red.colorMap, sizeof(red.colorMap));
    outFile.write((char*)&red.imgType, sizeof(red.imgType));
    outFile.write((char*)&red.colorMapOrigin, sizeof(red.colorMapOrigin));
    outFile.write((char*)&red.colorMapLength, sizeof(red.colorMapLength));
    outFile.write((char*)&red.colorMapDepth, sizeof(red.colorMapDepth));
    outFile.write((char*)&red.xOrigin, sizeof(red.xOrigin));
    outFile.write((char*)&red.yOrigin, sizeof(red.yOrigin));
    outFile.write((char*)&red.imgW, sizeof(red.imgW));
    outFile.write((char*)&red.imgH, sizeof(red.imgH));
    outFile.write((char*)&red.pixelDepth, sizeof(red.pixelDepth));
    outFile.write((char*)&red.imgDescriptor, sizeof(red.imgDescriptor));

    //0 = B
    //1 = G
    //2 = G
    //Write the image data to the file
    for (i = 0; i < (red.imgW * red.imgH); i++) {
        outFile.write((char *) &blue.imgData[i].B, sizeof(blue.imgData[i].B));
        outFile.write((char *) &green.imgData[i].B, sizeof(green.imgData[i].G));
        outFile.write((char *) &red.imgData[i].B, sizeof(red.imgData[i].R));
    }
    //Close the file
    outFile.close();
}
Image flip(Image &image) {
    vector<Image::Pixel> tempList;
    int j = 0;
    for(i = 0; i < image.imgData.size(); i++) {
        tempList.push_back(image.imgData.at(i));
    }
    for(i = (int)tempList.size() - 1; i >= 0; i--) {
        image.imgData.at(j) = tempList.at(i);
        j++;
    }
    return image;
}

Image appendImages(Image &i0, Image &i1, Image &i2, Image &i3) {
    //Create image
    Image i;

    //Make some adjustments to header code
    i.imgW = i0.imgW + i1.imgW;
    i.imgH = i0.imgH + i2.imgH;
    i.imgType = 2;
    i.pixelDepth = 24;

    int index;
    //Copy columns of both images
    for (int h = 0; h < i0.imgH; h++) {
        //Copy rows of first image into larger file
        for (int w = 0; w < i0.imgW; w++) { 
            index = w + i0.imgW * h;
            Image::Pixel temp;
            temp.B = i0.imgData[index].B;
            temp.G = i0.imgData[index].G;
            temp.R = i0.imgData[index].R;
            i.imgData.push_back(temp);
        }
        //Copy rows of second image into larger file
        for (int w = 0; w < i1.imgW; w++) {
            index = w + i1.imgW * h;
            Image::Pixel temp;
            temp.B = i1.imgData[index].B;
            temp.G = i1.imgData[index].G;
            temp.R = i1.imgData[index].R;
            i.imgData.push_back(temp);
        }
    }

    for (int h = 0; h < i2.imgH; h++) {
        //Copy rows of first image into larger file
        for (int w = 0; w < i2.imgW; w++) { 
            index = w + i2.imgW * h;
            Image::Pixel temp;
            temp.B = i2.imgData[index].B;
            temp.G = i2.imgData[index].G;
            temp.R = i2.imgData[index].R;
            i.imgData.push_back(temp);
        }
        //Copy rows of second image into larger file
        for (int w = 0; w < i3.imgW; w++) {
            index = w + i3.imgW * h;
            Image::Pixel temp;
            temp.B = i3.imgData[index].B;
            temp.G = i3.imgData[index].G;
            temp.R = i3.imgData[index].R;
            i.imgData.push_back(temp);
        }
    }
    return i;
}


bool test(Image &result, Image &objective) {
   bool test = true;

   for (i = 0; i < result.imgData.size(); i++) {
       if (result.imgData.at(i).B != objective.imgData.at(i).B) {

           cout << "Failed: " << result.imgData.at(i).B << " " << objective.imgData.at(i).B << endl;
           return !test;
       }
       if (result.imgData.at(i).G != objective.imgData.at(i).G) {
           cout << "Failed: " << result.imgData.at(i).G << " " << objective.imgData.at(i).G << endl;
           return !test;
       }
       if (result.imgData.at(i).R != objective.imgData.at(i).R) {
           cout << "Failed: " << result.imgData.at(i).R << " " << objective.imgData.at(i).R << endl;
           return !test;
       }
   }
   cout << "Passed" << endl;
   return test;
}
