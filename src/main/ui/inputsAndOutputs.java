package main.ui;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import main.model.OperationTracker;

public class inputsAndOutputs {
    
    private static Scanner scanner = new Scanner(System.in);
    
    //TERMINAL BASED METHOD...    
    //REQUIRES: scanner object must not be null, integer numofMatrices must be 1 or 2
    //MODIFIES: scanner object is used to read the inputs, and mDimensions is modified by the users input
    //EFFECTS: prompts the user to enter the dimensions of one of two matrices and returns an array list...
    //         containing these dimensions
    public static ArrayList<Integer> getDimensions(int Case) {
    
        //make arrayList for the dimensions
        ArrayList<Integer> mDimensions;
        mDimensions = new ArrayList<>();
        if (Case == 1) {
            //this case is for single matrix operations
            //get the dimensions for the first matrix and add them to the arrayList
            System.out.println("Enter the dimensions of the matrix: ");
            int rowsA = scanner.nextInt();
            int columnsA = scanner.nextInt();
            mDimensions.add(rowsA);
            mDimensions.add(columnsA);
            }
        else if (Case == 2) {
            //this case is for multi matrix operations with no loaded matrix
            //get the dimensions for the first matrix and add them to the arrayList
            System.out.println("Enter the dimensions of the first matrix: ");
            int rowsA = scanner.nextInt();
            int columnsA = scanner.nextInt();
            mDimensions.add(rowsA);
            mDimensions.add(columnsA);

        }
        else {
            //this case is if the fisrt matrix has been loaded form elsewhere
            //get the dimensions for the second matrix and add them to the arrayList
            System.out.println("Enter the dimensions of the second matrix: ");
            int rowsB = scanner.nextInt();
            int columnsB = scanner.nextInt();
            mDimensions.add(rowsB);
            mDimensions.add(columnsB);
        }
        
        return mDimensions;
    }

    // TERMINAL BASED METHOD...    
    //REQUIRES: scanner object must not be null, integer numofMatrices must be 1 or 2, dimensions must be nonzero
    //MODIFIES: scanner object is used to read the inputs, and MatrixA is modified by the users input
    //EFFECTS: prompts the user to enter the values of the matrix A based on the dimensions given...
    //         and returns the matrix
   public static double[][] getMatrixA(int rowsA, int columnsA, int numOfMatrices) {
        
        //get the values in the first matrix
        if (numOfMatrices == 1) {
            //print statement to make sure dimensions read properly
            System.out.println("Your matrix has dimensions " + rowsA + " by "+ columnsA);
            System.out.println("Enter the " + (rowsA * columnsA) + " values of the matrix: ");
        }
        else {
            System.out.println("Your first matrix has dimensions " + rowsA + " by "+ columnsA);
            System.out.println("Enter the " + (rowsA * columnsA) + " values of the first matrix: ");
        }

        double A[][] = new double[rowsA][columnsA];
        
        //for loop for gathering the matrix
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsA; j++) {
                A[i][j] = scanner.nextDouble();
            }
        }
        
        //printing out the matrix to make sure it was read in correctly
        if (numOfMatrices == 1) {
            System.out.println("Your matrix is: ");
        }
        else {
            System.out.println("Your first matrix is: ");
        }
        printMatrix(A, rowsA, columnsA);
        
        return A;
    }

    // TERMINAL BASED METHOD...    
    //REQUIRES: scanner object must not be null, integer numofMatrices must be 1 or 2, dimensions must be nonzero
    //MODIFIES: scanner object is used to read the inputs, and MatrixB is modified by the users input
    //EFFECTS: prompts the user to enter the values of the matrix B based on the dimensions given...
    //         and returns the matrix
    public static double[][] getMatrixB(int rowsB, int columnsB) {

        //print statements to prompt user
        System.out.println("Your second matrix has dimensions " + rowsB + " by " + columnsB);
        System.out.println("Enter the " + (rowsB * columnsB) + " values of the second matrix: ");
        
        double B[][] = new double[rowsB][columnsB];
        
        //for loop for gathering the matrix
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < columnsB; j++) {
                B[i][j] = scanner.nextInt();
            }
        }
        //printing out the matrix to make sure it was read in correctly
        System.out.println("Your second matrix is: ");
        printMatrix(B, rowsB, columnsB);
        
        return B;
    }

    // TERMINAL BASED METHOD...    
    //REQUIRES: matrix r, rows, columns
    //EFFECTS: prints out the inputted matrix to the user
    public static void printMatrix(double Result[][], int rowsR, int columnsR) {
        
        for (int i = 0; i < rowsR; i++) {
            for (int j = 0; j < columnsR; j++) {
                System.out.print( Result[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    // TERMINAL BASED METHOD...    
    // EFFECTS: Prompts the user whether they want to save the provided matrix to a file. 
    // It takes user input, validates it, and either saves the matrix to a file if the 
    // user chooses to or prints a message that the matrix was not saved.
    public static void askAndSaveMatrix(double[][] matrix) {

        System.out.println("Do you want to save this matrix to a file? (y/n)");

        String userInput = scanner.nextLine().trim().toLowerCase();

        while (!userInput.equals("y") && !userInput.equals("n")) {
            System.out.print("Invalid input. Please enter 'y' to quit or 'n' to run again: ");
            userInput = scanner.nextLine().trim().toLowerCase();
        }

        if (userInput.equals("y")) {
            saveMatrixToFile(matrix);
        } else {
            System.out.println("Matrix not saved.");
        }
        
    }

    // REQUIRES: The input matrix must be a non-null 2D array.
    // MODIFIES: Creates or modifies a file on the disk.
    // EFFECTS: Saves the input matrix to a file "data/matrixCalculatorSave.txt".
    //          Prints a success message if the matrix is saved successfully.
    //          Prints an error message and stack trace if an IOException occurs.
    public static void saveMatrixToFile(double[][] matrix) {
        try {
            
            String fileName = "data/matrixCalculatorSave.txt";

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Save the matrix to the file
            objectOutputStream.writeObject(matrix);

            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Matrix saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving matrix to file.");
            e.printStackTrace();
        }
    }

    // REQUIRES: The file "data/matrixCalculatorSave.txt" must exist and 
    // contain a serialized double[][] object.
    // EFFECTS: Loads the object from the file "data/matrixCalculatorSave.txt" and returns it.
    // Returns null if an IOException or ClassNotFoundException occurs during loading.
    public static double[][] loadMatrixFromFile() {
        try {
            
            String fileName = "data/matrixCalculatorSave.txt";

            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // Load the matrix from the file
            double[][] loadedMatrix = (double[][]) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            return loadedMatrix;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // TERMINAL BASED METHOD... 
    // EFFECTS: Prompts the user to enter an integer greater than or equal to zero.
    //          Keeps prompting until a valid integer greater than one is entered. Returns the entered exponent.
    public static int getExponent() {
        int Exp;
        System.out.println("Please enter an integer greater than or equal to zero for exponent: ");
        while (true) {
            if (scanner.hasNextInt()) {
                Exp = scanner.nextInt();
                if (Exp >= 1) {
                    break;
                } else {
                    System.out.println("Wrong input. Please enter an integer >= zero.");
                }
            } 
        else {
            System.out.println("Wrong input. Please enter a number.");
            scanner.next(); // consume the invalid input
        }
    }
        return Exp;
    }

    // TERMINAL BASED METHOD...    
    // EFFECTS: Prompts the user to enter 'y' to quit the program or 'n' to run it again.
    //          Keeps prompting until a valid input is entered. If the user chooses to quit, displays 
    //          the session operations and exits the program.
    //          If the user chooses to run again, creates a new instance of MatrixCalculatorGUI.
    public static void askUserToQuitOrRunAgain() {

        System.out.print("Do you want to quit the program? (y/n): ");
        String userInput = scanner.nextLine().trim().toLowerCase();

        while (!userInput.equals("y") && !userInput.equals("n")) {
            System.out.print("Invalid input. Please enter 'y' to quit or 'n' to run again: ");
            userInput = scanner.nextLine().trim().toLowerCase();
        }

        if (userInput.equals("y")) {
            OperationTracker.displayOperations();           
            System.out.println("Exiting the program.");
            return;
        } else {
            System.out.println("Running the program again...");
            new MatrixCalculatorGUI();
        }
    }
    
    // TERMINAL BASED METHOD...    
    // EFFECTS: Prompts the user to enter a scalar value.
    //          Keeps prompting until a valid double value is entered.
    //          Returns the entered scalar value.
    public static double getScalar() {
        double scalar;

        while (true) {
            System.out.println("Please enter a scalar for the desired operation: ");
            
            if (scanner.hasNextDouble()) {
                scalar = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character
                
                return scalar;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}




