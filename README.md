# Name: Nicholas Jean
# Project Introduction

My project will be a matrix calculator that will be able to do a variety of desired operations on one or two matrices. 

## Features

It will have capabilities of operations using **two matrices:**
* Addition
* Subtraction
* Multiplication
* Division

It will also be able to do operations on a **single martrix:** 
* Inverse
* Transpose 
* Determinant
* Exponential
* Scalar Multiplication

## Use

The Matrix Calculator project is designed to be used by a diverse audience ranging from students and educators to professionals in fields such as mathematics, physics, computer science, and engineering. Students at various academic levels can benefit from the tool for learning and practicing matrix operations, while educators can integrate it into their teaching materials.


## Motivation

I am a fourth year mechanical/mechatronics engineering student. I have found that a lot of my classes this year involve matrtix operations, such as _Finite Element Analysis_ and _Modern Control_. Some of the problems involve calculations of matrices that are larger than 3x3, which my engineering standard calculator cannot do. I have used a few matrix calculators online, and I took inspiration from them to make a calculator with a variety of features for matrices.


# User Stories

_User stories_ are statements that describe how someone can use your application to produce a specific outcome. I will design the code to make each _user story_ a reality.

* As a user, I want to choose what operation I want to calculate.
* As a user, I want to specify the dimensions of my matrix or matrices.
* As a user, I want to input values of the matrix or matrices to be used in the calculation.
* As a user, I want the program to tell me if the operation is possible based on the matrix dimensions.
* As a user, I want the program to display the result of the calculation.
* As a user, I want to have the option to quit the program or continue calculating.
* As a user, I want to be able to save my result matrix to a file.
* As a user, I want to be able to be able to load my result matrix from a file. 
* As a user, I want to be able to see the history of the operations performed.


# Domain Analysis

## 1. Constant Information
* Matrix Dimensions: Matrices will have a fixed number or rows and columns
* Mathematical Operations: Addition, Subtraction, Multiplication, Inversion...

## 2. Changing Information
* Matrix Elements: Values within matrices can be modified depending on the operation
* Calculation Results: Results of operations can be changed based on user input

# Types of Data

## 1. Inputs and Outputs Class
* Attributes:
    * Rows and Columns: Dimensions of the matrix.
    * Elements: 2D array representing matrix values.
    * Elements: Integer variable for exponent
* Methods:
    * GetDimensions method to get dimensions from user input
    * GetMatrix methods for getting matrices from user input
    * GetExponent method to get exponent from user input
    * GetScalar method to get scalar value for multiplication from user.
    * PrintMatrix method to display matrices in a readable format
    * LoadMatrix method to load a matrix from a text file
    * SaveMatrix method to save a matrix form a text file
    * askUserToQuitOrRunAgain method to quit the program or run again.

## 2. Calculation Functions Class
* Attributes:
    * None explicitly stored within the class.
* Methods:
    * Addition method, performs matrix addition.
    * Subtraction method, performs matrix subtraction. 
    * Multiplication method, performs matrix multiplication.
    * Inverse method, performs matrix inverse.
    * Determinant method, performs matrix determinant.
    * Submatrix method, returns a submatrix by removing a specified row and column.
    * Transpose method, calculates the transpose of a matrix.
    * Power method, raises a matrix to a specified power.
    * DuplicateMatrix method, creates a duplicate of a matrix.
    * IndentityMatrix method, creates an identity matrix of the same dimensions as the input matrix.

## 3. Single Matrix Operation Class
* Attributes:
    * None explicitly stored within the class.
* Methods:
    * Methods for each single matrix operation that checks if the operation is possible
    based on the matrix and its dimensions.

## 4. Multiple Matrix Operation Class
* Attributes:
    * None explicitly stored within the class.
* Methods:
    * Methods for each multi-matrix operation that checks if the operation is possible
    based on the matrices and their dimensions.

# Instructions for Running

## 1. Choose your desired operation:
Using the first drop down at the top of the GUI, pick the operation type you would like to perform, then using the second drop down, pick the specific operation.

## 2. Inputting your desired dimensions:
Once the desired operation type is selected, the correct number of text boxes labeled with dimensions will be showing. Fill in these boxes with the desired dimensions of your one of two matrices, then press the "create matrix" button.

## 2. ALTERNATE: Loading matrix:
If you have already saved a matrix to the file and want to use that in your next calculations, click the load button of either matrix A or B, whichever one you need, and the dimensions and the matrix values will be inputted into the correct spots after pressing the button. A window will pop up notifying you that the matrix was loaded successfully.

## 3. Inputting your matrix data:
Once you have pressed the create matrix button, a grid of text boxes will appear with the proper dimensions. Fill these boxes in with the desired matrix data.

## 4. Perform operation:
Once the matrix data is inputted, you can press the perform operation button below. If the operation is possible, the result will be displayed underneath. If an exception was thrown during the process and the result is not able to be calculated, a text window will pop up, where the specific exception is named, allowing you to change what is needed for the operation to be performed successfully.

## 5. Saving the result:
Once an operation has been performed successfully, a button will appear under the result with a save prompt. If the save button is pressed, the previsouly calculated result matrix will be saved to a file, which can be then loaded using the load [A] or [B] buttons mentioned above. A window will pop up saying that the matrix was saved successfully.

## 6. Quitting the program:
When you have finished doing all of your operations, you can press the quit button at the bottom of the GUI window. When you press this button, a window will pop up which displays all of the operations you performed during that session.

# Assignment 3 Task 3

If I had more time to work on the project, I would refactor my classes to have a larger heirarchy, with a Matrix Operations superclass, and Single and Multi matrix operations subclasses, in which each of the specific operations extend their proper class. This would have allowed me to structure specific methods with more ease, and certain functionalities would have been easier to implement. 

