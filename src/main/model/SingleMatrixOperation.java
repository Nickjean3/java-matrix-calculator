package main.model;

import main.ui.inputsAndOutputs;
import java.util.ArrayList;
import java.util.Scanner;

public interface SingleMatrixOperation {

    // initialise scanner field
    static Scanner scanner = new Scanner(System.in);
    
    // Method to be implemented by subclasses, each with their own method
    double[][] performOperation(double[][] matrix, SingleMatrixOperation operation);

    // REQUIRES: operation must be a non-null SingleMatrixOperation instance.
    // EFFECTS:  Performs a single-matrix operation as specified by the given instance.
    //           Prompts the user to choose whether to load a previous matrix or input a new matrix.
    //           If the user chooses to load a previous matrix, loads the matrix from file.
    //           If the user chooses to input a matrix, prompts the user for dimensions and values.
    //           Performs the specified operation on matrix A and displays the result.
    public static void performSingleOperation(SingleMatrixOperation operation) {
        ArrayList<Integer> mDimensions;

        // Ask user if they want to load the previous matrix
        System.out.println("Do you want to load the previous matrix? (yes/no)");
        String loadOption = scanner.nextLine().trim().toLowerCase();

        double[][] A;

        if (loadOption.equals("yes")) {
            // load the saved matrix into A
            A = inputsAndOutputs.loadMatrixFromFile();
            int rowsA = A.length;
            int columnsA = A[0].length;
            System.out.println("Loaded Matrix is: ");
            inputsAndOutputs.printMatrix(A, rowsA, columnsA);
        } else {
            // get matrix A from getDimensions and getMatrixA
            mDimensions = inputsAndOutputs.getDimensions(2);
            int rowsA = mDimensions.get(0);
            int columnsA = mDimensions.get(1);
            A = inputsAndOutputs.getMatrixA(rowsA, columnsA, 2);
        }

       operation.performOperation(A, operation);
    }
}


