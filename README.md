# Expense Tracker
An Expense Tracker project is a software application designed to help users record, monitor, and manage their daily income and spending.

## Student Details

**Name:** LIKITHA VIJAYAKUMAR

**Registration Number:** 24BAS10103

**Course:** Programming in Java - Evaluated Project

**Slot:** C11 + C12

**Date of Submission:** 18/09/2026


## Project Description

Expense Tracker is a simple command-line application developed using Java to help users record and manage their daily expenses. The application stores expense information in a CSV file and provides options to view, search, and analyze the recorded expenses.

## Features

The application allows users to add new expenses by entering the date, category, description, and amount. It also allows users to view all recorded expenses, calculate total spending, view category-wise expenses, and search for expenses using a category or description.

## Technologies Used

* Java
* CSV file for data storage
* Java standard libraries
* Command Line Interface (CLI)

## Requirements

Java Development Kit (JDK) 8 or above is required to compile and run the project.

No external libraries or additional dependencies are required.

## Project Structure

```text
Expense-Tracker/
│
├── README.md
├── ExpenseTracker.java
├── expenses.csv
├── requirements.txt
└── Project_Report.pdf
```

## Setup

First, make sure Java is installed on your computer. Open a terminal or Command Prompt and check the Java version using:

```bash
java -version
```

Also check that the Java compiler is available:

```bash
javac -version
```

Clone the repository using:

```bash
git clone https://github.com/YOUR-USERNAME/Expense-Tracker.git
```

Move into the project directory:

```bash
cd Expense-Tracker
```

## Compilation

Compile the Java program using:

```bash
javac ExpenseTracker.java
```

If compilation is successful, a `ExpenseTracker.class` file will be created.

## Execution

Run the program using:

```bash
java ExpenseTracker
```

The application will display a menu in the terminal.

```text
========================================
           EXPENSE TRACKER
========================================
1. Add Expense
2. View All Expenses
3. Calculate Total Expenses
4. Category-wise Summary
5. Search Expenses
6. Exit
========================================
```

Enter the number corresponding to the operation you want to perform.

## Data Storage

Expense records are stored in the `expenses.csv` file. Each record contains the date, category, description, and amount of the expense.

If the CSV file does not exist, the program automatically creates it with the required headings.

## Example

A sample expense record is stored in the following format:

```text
Date,Category,Description,Amount
2026-09-10,Food,Lunch,120.00
```

The user can add new records through the command-line menu, and the new information will be saved to the CSV file.

## Conclusion

Expense Tracker provides a simple way to record and understand daily spending through a command-line interface. The project demonstrates basic Java programming concepts such as classes, methods, user input, file handling, collections, exception handling, and CSV data processing.
