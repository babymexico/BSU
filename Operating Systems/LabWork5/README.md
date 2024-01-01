Laboratory Work 5 task description:
Topic: "Data Exchange via Named Pipes".
Task: Write a program to manage the access of parallel processes to a file using named pipes.

Problem statement:
1. Develop a server process that performs the following actions:
1.1. Creates a binary file, where the records have the following structure:
    ```c
    struct employee {
        int num; // employee identification number
        char name[10]; // employee name
        double hours; // number of hours worked
    };
    ```
    The filename and student data are entered via the console.
1.2. Displays the created file on the console.
1.3. Launches client processes, named "Client," that access the file via a named pipe. The number of client processes is entered via the console.
1.4. Services client process requests as follows:
   - If a client process requests modification of a file record, access to that file record is blocked for other client processes until the modification is completed by the client.
   - If a client process requests reading a file record, access to that record for other client processes:
     - Is not blocked if they also request read access to the data.
     - Is blocked if they request write access to the data.
1.5. After all client processes have finished their work, outputs the modified file to the console.
1.6. Terminates its operation on console command.

2. Develop a client process, named "Client," that performs the following actions:
2.1. Executes a loop:
   2.1.1. Requests from the console to perform one of the operations:
     - Modification of a file record
     - Reading a record
     - Exiting the loop
   2.1.2. After entering the required data, performs the requested operation.
2.2. Access to file records is carried out using a key, which is the employee ID.
2.3. During record modification, the client process performs the following actions:
   2.3.1. Requests the record key.
   2.3.2. Sends a request to the server.
   2.3.3. Outputs the received record from the server to the console.
   2.3.4. Requests new values for the fields.
   2.3.5. Sends the modified record to the server on console command.
   2.3.6. Terminates access to the record on console command.
2.4. During record reading, the client process performs the following actions:
   2.4.1. Requests the record key.
   2.4.2. Sends a request to the server.
   2.4.3. Outputs the received record from the server to the console.
   2.4.4. Terminates access to the record on console command.

Simplified version of the lab assignment: Solve the given task for one Server process and one Client process.