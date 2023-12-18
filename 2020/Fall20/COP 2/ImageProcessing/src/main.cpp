#include <iostream>
#include "project2.h"
using namespace std;

int main() {
    Image image;
    Image image1;
    Image image2;
    Image image3;
    Image result;
    Image tester;

    //Task 1
    image = readFile("./input/layer1.tga");
    image1 = readFile("./input/pattern1.tga");
    result = multiply(image, image1);
    writeFile("./output/part1.tga", result);
    cout << "Task 1 Complete" << endl;

    // tester = readFile("./examples/EXAMPLE_part1.tga");
    // test(result, tester);

    //Task 2
    image = readFile("./input/layer2.tga");
    image1 = readFile("./input/car.tga");
    result = subtract(image, image1);
    writeFile("./output/part2.tga", result);
    cout << "Task 2 Complete" << endl;

    // tester = readFile("./examples/EXAMPLE_part2.tga");
    // test(result, tester);


    //Task 3
    image = readFile("./input/layer1.tga");
    image1 = readFile("./input/pattern2.tga");
    result = multiply(image, image1);
    image2 = readFile("./input/text.tga");
    result = screen(image2, result);
    writeFile("./output/part3.tga", result);
    cout << "Task 3 Complete" << endl;


    // tester = readFile("./examples/EXAMPLE_part3.tga");
    // test(result, tester);
    

    //Task 4
    image = readFile("./input/layer2.tga");
    image1 = readFile("./input/circles.tga");
    result = multiply(image, image1);
    image2 = readFile("./input/pattern2.tga");
    result = subtract(image2, result);
    writeFile("./output/part4.tga", result);
    cout << "Task 4 Complete" << endl;

    // tester = readFile("./examples/EXAMPLE_part4.tga");
    // test(result, tester);

    //Task 5
    image = readFile("./input/layer1.tga");
    image1 = readFile("./input/pattern1.tga");
    result = overlay(image, image1);
    writeFile("./output/part5.tga", result);
    cout << "Task 5 Complete" << endl;

    // tester = readFile("./examples/EXAMPLE_part5.tga");
    // test(result, tester);

    //Task 6
    image = readFile("./input/car.tga");
    //Loop through entire image
    for (i = 0; i < image.imgData.size(); i++) {
        //Add 200 to green channel
        if (image.imgData.at(i).G < 55) {
            image.imgData.at(i).G += 200;
        } else {
            image.imgData.at(i).G = 255;
        }
    }
    writeFile("./output/part6.tga", image);
    cout << "Task 6 Complete" << endl;

    // tester = readFile("./examples/EXAMPLE_part6.tga");
    // test(image, tester);

    //Task 7
    image = readFile("./input/car.tga");
    //Loop through entire image
    for (i = 0; i < image.imgData.size(); i++) {
        //Scale the red channel by 4
        if (image.imgData.at(i).R <= 63) {
            image.imgData.at(i).R *= 4;

        } else {
            image.imgData.at(i).R = 255;
        }

        //Scale blue channel by 0
        image.imgData.at(i).B *= 0;

    }
    writeFile("./output/part7.tga", image);
    cout << "Task 7 Complete" << endl;

    // tester = readFile("./examples/EXAMPLE_part7.tga");
    // test(image, tester);

    //Task 8
    image = readFile("./input/car.tga");
    //Write blue channel
    writeFile("./output/part8_b.tga", image, 0);

    //Write green channel
    writeFile("./output/part8_g.tga", image, 1);

    //Write red channel
    writeFile("./output/part8_r.tga", image, 2);
    cout << "Task 8 Complete" << endl;

    // result = readFile("./output/part8_b.tga");
    // tester = readFile("./examples/EXAMPLE_part8_b.tga");
    // test(result, tester);

    // result = readFile("./output/part8_g.tga");
    // tester = readFile("./examples/EXAMPLE_part8_g.tga");
    // test(result, tester);

    // tester = readFile("./examples/EXAMPLE_part8_r.tga");
    // result = readFile("./output/part8_r.tga");
    // test(result, tester);

    //Task 9
    image = readFile("./input/layer_red.tga");
    image1 = readFile("./input/layer_green.tga");
    image2 = readFile("./input/layer_blue.tga");
    combineImages("./output/part9.tga", image, image1, image2);
    cout << "Task 9 Complete" << endl;
    // result = readFile("./output/part9.tga");
    // tester = readFile("./examples/EXAMPLE_part9.tga");
    // test(result , tester);

    //Task 10
    image = readFile("./input/text2.tga");
    result = flip(image);
    writeFile("./output/part10.tga", result);
    cout << "Task 10 Complete" << endl;
    // tester = readFile("./examples/EXAMPLE_part10.tga");
    // test(result, tester);

    //Extra Credit task
    image = readFile("./input/text.tga");
    image1 = readFile("./input/pattern1.tga");
    image2 = readFile("./input/car.tga");
    image3 = readFile("./input/circles.tga");
    result = appendImages(image,image1,image2,image3);
    cout << "Extra Credit Task Complete" << endl;
    writeFile("./output/extracredit.tga", result);

    // tester = readFile("./examples/EXAMPLE_extracredit.tga");
    // test(result, tester);

}