Course work

Industrial Programming

2 semester

**Objective:**
The goal of this project is to develop a console application that serves as a comprehensive learning exercise covering Java Core, Unit Testing, fundamental Design Patterns, Refactoring with a focus on transitioning between procedural and Object-Oriented Programming (OOP) solutions, and additional relevant topics such as file formats, regular expressions, and a touch of UI if energy permits.

**Project Overview:**
Implement a console application that performs the following tasks:
1. Read data from an input file.
2. Process the received information.
3. Write data to an output file.

**File Formats:**
Both input and output files can be in plain text, XML, or JSON formats. Additionally, these files may be archived and encrypted using various engines (archived only, encrypted only, first archived then encrypted, and vice versa).

**Console Parameters:**
The "type" of the input and output files will be determined by console parameters.

**Implementation:**
Develop the application in two ways: without using Design Patterns and using Design Patterns (specifically, Decorator and Builder – the Builder pattern can be implemented as a Singleton). Compare the implementations.

**Information Processing:**
At the initial stage, identify all arithmetic operations in the input file and replace them with the results in the output file. Implement filtering in two ways: without using regular expressions and with regular expressions (as well as a third option: find a library that handles parsing and calculation for you). Conduct a comparative analysis of the two implementation variants.

**Next Steps:**
1. Add UI:
   a. Console-based.
   b. Utilize any Java graphical library of your choice.
   c. Compare CLI and UI-based implementations.
   d. Discuss with peers and compare different Java graphical libraries.

2. Implement logic as a Web Service:
   a. REST, using any Java engine.
   b. SOAP, using any Java engine.
   c. Compare REST and SOAP implementations.
   d. Discuss with peers and compare different REST/Soap Java engines.

3. Integrate UI and Web Service.

**Guidance:**
Please carefully review the formulations and study the book "Extreme Programming." Additionally, delve into one of the suggested Java books. During class, break down the task into minimal sub-tasks and implement at least one. Send me the Git link with compilable code for code review. Thank you!

**Atomic Subtasks Examples:**
- Read/write text file.
- Read/write XML file (using a specialized XML API and line-by-line reading).
- Read/write JSON file (using a specialized JSON API and line-by-line reading).
- Archive/de-archive file using Zip or Rar implementations.
- Encrypt/de-encrypt file using any encryption library.
- Cover all atomic tasks with unit tests.

Each atomic task should be straightforward, requiring only a few lines of code, and solutions can be found online even without knowing the language syntax.