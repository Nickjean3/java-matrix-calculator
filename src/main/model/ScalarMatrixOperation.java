package main.model;

import java.util.ArrayList;
import java.util.Scanner;

import main.ui.inputsAndOutputs;

public interface ScalarMatrixOperation {
    
    //initialise scanner field
    static Scanner scanner = new Scanner(System.in);
    
    // Method to be implemented by subclasses, each with their own method
    public double[][] performOperation(double[][] matrix, double Scalar, 
        ScalarMatrixOperation operation);

    // REQUIRES: operation must be a non-null ScalarMatrixOperation instance.
    // EFFECTS:  Performs a scalar-matrix operation specified by the ScalarMatrixOperation instance.
    //           Prompts the user to choose whether to load a previous matrix or input a new matrix.
    //           If the user chooses to load a previous matrix, loads the matrix from file.
    //           If the user chooses to input a new matrix, prompts the user for dimensions and values.
    //           Prompts the user to input a scalar value.
    //           Performs the specified scalar operation on matrix A 
    //           with the given scalar value and displays the result.
    public static void performScalarOperation(ScalarMatrixOperation operation) {
        
        ArrayList<Integer> mDimensions;

        // Ask user if they want to load the previous matrix
        System.out.println("Do you want to load the previous matrix? (yes/no)");
        String loadOption = scanner.nextLine().trim().toLowerCase();

        double[][] A;

        if (loadOption.equals("yes")) {
            A = inputsAndOutputs.loadMatrixFromFile();
            int rowsA = A.length;
            int columnsA = A[0].length;
            System.out.println("Loaded Matrix is: ");
            inputsAndOutputs.printMatrix(A, rowsA, columnsA);
        } else {
            // Implement the common part of the multi-matrix operations here
            mDimensions = inputsAndOutputs.getDimensions(2);
            int rowsA = mDimensions.get(0);
            int columnsA = mDimensions.get(1);

            // Create matrix A
            A = inputsAndOutputs.getMatrixA(rowsA, columnsA, 2);
        }
        double scalar = inputsAndOutputs.getScalar();

       operation.performOperation(A, scalar, operation);
    }
}

