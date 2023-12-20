Laboratory Work 3 task description:
Title: "Thread Synchronization Using Critical Sections and Events. Deadlock Handling"
Task: Write a program for a console process consisting of the main thread and several instances of the marker thread.

The main thread should perform the following actions:
1. Allocate memory for an array of integers, where the size is input from the console.
2. Initialize the array elements to zeros.
3. Request the number of marker threads to be launched.
4. Launch the specified number of marker thread instances, passing their ordinal numbers in the launch as parameters.
5. Signal the start of work for all marker threads.
6. Perform the following actions in a loop:
   6.1. Wait until all marker threads signal the inability to continue their work.
   6.2. Print the contents of the array to the console.
   6.3. Input the ordinal number of the marker thread to which a signal will be sent to terminate its work.
   6.4. Send a termination signal to the marker thread whose number was obtained in step 6.3.
   6.5. Wait for the termination of the marker thread that received a signal to terminate work in step 6.4.
   6.6. Print the contents of the array to the console.
   6.7. Signal the continuation of work to the remaining marker threads.
7. Terminate its execution after all marker threads have finished their work.

The marker thread should perform the following actions:
1. Start working upon receiving a signal from the main thread.
2. Initialize the generation of a sequence of random numbers using the srand function, passing its ordinal number as an argument.
3. Work cyclically, performing the following actions on each cycle:
   3.1. Generate a random number using the rand function.
   3.2. Divide this number by the size of the array using the modulus operation.
   3.3. If the array element at the index equal to the result is zero, then perform the following actions:
       3.3.1. Sleep for 5 milliseconds.
       3.3.2. Assign its ordinal number to the element at the calculated index.
       3.3.3. Sleep for 5 milliseconds.
       3.3.4. Continue executing cycle 3.
   3.4. Otherwise:
       3.4.1. Print the following information to the console:
              - Its ordinal number;
              - The count of marked elements;
              - The index of the array element that cannot be marked.
       3.4.2. Signal the main thread of the inability to continue its work.
       3.4.3. Wait for a response signal to continue or terminate work from the main thread.
4. If a signal to terminate work is received, perform the following actions:
   4.1. Fill all elements in the array that it marked with zeros.
   4.2. Terminate its execution.
5. If a signal to continue work is received, continue executing cycle 3.