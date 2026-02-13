package main.model;

import main.ui.inputsAndOutputs;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public abstract class MultiMatrixOperation {
    
    // initialise scanner field
    static Scanner scanner = new Scanner(System.in);
    
    // Abstract method to be implemented by subclasses, each with their own method
    public abstract double[][] performOperation(double[][] A, double[][] B, 
        MultiMatrixOperation operation);

    // REQUIRES: operation must be a non-null MultiMatrixOperation instance.
    // EFFECTS:  Performs a multi-matrix operation as specified by the given instance.
    //           Prompts the user to choose whether to load a previous matrix or input new matrices.
    //           If the user chooses to load a previous matrix, loads the matrix from file.
    //           If the user chooses to input matrices, prompts the user for dimensions and values
    //           Performs the specified operation on matrices A and B and displays the result.
    public static void performMultiOperation(MultiMatrixOperation operation) {
        
        ArrayList<Integer> mDimensions;

        // Ask user if they want to load the previous matrix
        System.out.println("Do you want to load the previous matrix? (yes/no)");
        String loadOption = scanner.nextLine().trim().toLowerCase();

        double[][] A;

        if (loadOption.equals("yes")) {
            //load the matrix saved in the file into A
            A = inputsAndOutputs.loadMatrixFromFile();
            int rowsA = A.length;
            int columnsA = A[0].length;
            System.out.println("Loaded Matrix is: ");
            inputsAndOutputs.printMatrix(A, rowsA, columnsA);
        } else {
            //get the matrix from the user using getDimensions and getMatrixA
            mDimensions = inputsAndOutputs.getDimensions(2);
            int rowsA = mDimensions.get(0);
            int columnsA = mDimensions.get(1);
            A = inputsAndOutputs.getMatrixA(rowsA, columnsA, 2);
        }

        // using getDimensions and getMatrixB, to initialise the second matrix
        mDimensions = inputsAndOutputs.getDimensions(2);
        int rowsB = mDimensions.get(0);
        int columnsB = mDimensions.get(1);
        double[][] B = inputsAndOutputs.getMatrixB(rowsB, columnsB);
        
        // Use the provided MatrixOperation instance
        operation.performOperation(A, B, operation);
    }

    // Override hashCode() and equals()
    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return true;
    }
}
