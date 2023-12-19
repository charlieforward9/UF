Built on MacOS Big Sur

To run this file, ensure that these files are in the same directory

- main.cpp
- cache.h
- misc.h
- gcc.trace -- or any other trace file to be tested (must adjust line 20 of main.cpp to test unique files)

With these files in the same directory, open the terminal and navigate to the directory. Once in the directory run this command to create an executable program:
g++-11 -g main.cpp -o main

Next, call the executable by running this command:
./main

Note: A slight change was made to the UI after the video was recorded, the user may now input the parameters into the terminal to be processed in the cache file, there is no need to open the file unless attempting to change the trace file name.