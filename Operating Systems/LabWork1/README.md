Laboratory Work 1 task description:
Title: "Process Creation"

Task: Develop a console program, Main, and two utility tools (service programs) - Creator and Reporter - that perform the following actions:

The Creator utility generates a binary file whose name and number of records are obtained through the command line. The file's records are entered via the console and follow this structure:
```c
struct employee {
    int num; // employee identification number
    char name[10]; // employee name
    double hours; // number of hours worked
};
```

The Reporter utility creates a text file containing an ordered list of employees with their respective earned wages. The report is generated based on the binary file created by the Creator program.

1. The utility receives the following data via the command line:
   1.1. Name of the source binary file.
   1.2. Report file name.
   1.3. Hourly wage rate.

2. The report contains the following lines:
   2.1. Header: Report for the file "name of the source binary file".
   2.2. Line: Employee number, employee name, hours worked, salary.
   2.3. A list of employees per line. Each line contains information from the corresponding record of the binary file, with the last column of the line representing the earned wage = number of hours worked * hourly wage rate.

The Main program performs the following actions:
1. Requests the name of the binary file and the number of records in this file from the console.
2. Launches the Creator utility, passing the name of the created binary file and the number of records via the command line.
3. Awaits the completion of the Creator utility's work.
4. Displays the contents of the created binary file on the console.
5. Requests from the console:
   a. Report file name.
   b. Hourly wage rate.
6. Launches the Reporter utility, passing the following via the command line:
   a. Name of the source binary file.
   b. Report file name.
   c. Hourly wage rate.
7. Awaits the completion of the Reporter utility's work.
8. Displays the report on the console.
9. Terminates its execution.