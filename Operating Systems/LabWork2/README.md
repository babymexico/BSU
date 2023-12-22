Laboratory Work 2 task description:
Title: "Creating Threads"
Task: Write a program for a console process that consists of three threads: main, min_max, and average.

The main thread should perform the following actions:
1. Create an array of integers, where the size and elements are inputted from the console.
2. Create threads min_max and average.
3. Wait for the min_max and average threads to complete.
4. Replace the maximum and minimum elements of the array with the average value of the array's elements. Output the resulting array to the console.
5. Terminate the program.

The min_max thread should perform the following actions:
1. Find the minimum and maximum elements of the array and output them to the console. After each comparison of elements, "sleep" for 7 milliseconds.
2. Terminate its execution.

The average thread should perform the following actions:
1. Calculate the arithmetic mean value of the array's elements and output it to the console. After each operation of summing the elements, "sleep" for 12 milliseconds.
2. Terminate its execution.

Notes:
1. To wait for the min_max and average threads to finish, use the function:
```c
DWORD WaitForSingleObject(
    HANDLE hHandle,        // object handle
    DWORD dwMilliseconds   // waiting time interval in milliseconds
);
```
Set the second parameter to INFINITE, for example:
```c
WaitForSingleObject(hAverage, INFINITE); // wait for the thread to finish
```
Here, hAverage is the handle of the average thread.

2. For sleeping, use the function:
```c
VOID Sleep(
    DWORD dwMilliseconds   // milliseconds
);
```
For example:
```c
Sleep(12); // sleep for 12 milliseconds
```