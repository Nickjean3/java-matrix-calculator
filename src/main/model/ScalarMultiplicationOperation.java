package main.model;

import java.util.Objects;

import main.ui.inputsAndOutputs;

public class ScalarMultiplicationOperation implements ScalarMatrixOperation {

    // NOTE: console based methods have been commented out, but are in the places where they should be.
    // REQUIRES: A must be a non-null 2D array representing a matrix.
    //           The scalar must be a valid double value.
    //           The ScalarMatrixOperation parameter must not be null.
    // MODIFIES: Modifies the OperationTracker by tracking the performed operation.
    // EFFECTS:  Calculates the scalar operation on matrix A with the given scalar.
    //           Tracks the performed operation.
    //           Prints the result of the scalar operation to the console.
    //           Returns the resulting matrix.
    @Override
    public double[][] performOperation(double[][] A, double scalar, ScalarMatrixOperation operation) {
        //track operation
        OperationTracker.trackScalarOperation(operation);
        
        //calculate and display result
        System.out.println("Calculating " + scalar + " * [A] ...");
        double[][] Result = calculationFunctions.scalarOperation(A, scalar,(a, s) -> a * s);
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
