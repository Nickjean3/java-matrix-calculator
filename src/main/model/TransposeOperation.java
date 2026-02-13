package main.model;

import java.util.Objects;

import main.ui.inputsAndOutputs;

public class TransposeOperation implements SingleMatrixOperation {
    
    // NOTE: console based methods have been commented out, but are in the places where they should be.
    // REQUIRES: A must be a non-null 2D array representing a matrix.
    //           The SingleMatrixOperation parameter must not be null.
    // MODIFIES: Modifies the OperationTracker by tracking the performed operation.
    // EFFECTS:  Calculates the transpose of matrix A.
    //           Tracks the performed operation.
    //           Prints the transpose of matrix A to the console.
    //           Returns the transposed matrix.
    @Override
    public double[][] performOperation(double[][] A, SingleMatrixOperation operation) {
        //track operation
        OperationTracker.trackSingleOperation(operation);
        
        //calculate and display result
        System.out.println("Calculating [A]^T ...");
        double[][] Result = calculationFunctions.transpose(A); 
        inputsAndOutputs.printMatrix(Result, A[0].length, A.length);
        
        //inputsAndOutputs.askAndSaveMatrix(Result);
        //inputsAndOutputs.askUserToQuitOrRunAgain();

        return Result;
    }

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
