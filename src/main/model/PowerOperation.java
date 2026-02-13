package main.model;

import java.util.Objects;

import javax.swing.JOptionPane;

import main.ui.inputsAndOutputs;

public class PowerOperation implements SingleMatrixOperation {
    
    // NOTE: console based methods have been commented out, but are in the places where they should be.
    // REQUIRES: A must be a non-null square matrix represented by a 2D array.
    //           Exp must be a non-null integer
    //           The SingleMatrixOperation parameter must not be null.
    // MODIFIES: Modifies the OperationTracker by tracking the performed operation.
    // EFFECTS:  Attempts to calculate matrix A to the power of Exp.
    //           If the matrix is not square, throws a NonSquareMatrixException.
    //           If Exp is less than zero, throws a PowerException.
    //           If the operation is successful, returns the resulting matrix.
    //           If an exception occurs during the operation, removes the operation 
    //           from the OperationTracker and displays an error message, returns null
    public double[][] performPowerOperation(double[][] A, int Exp, SingleMatrixOperation operation) {
        try {
            //track operation
            OperationTracker.trackSingleOperation(operation);
            
            //check if the matrix is square
            if (A.length != A[0].length) {
                throw new NonSquareMatrixException();
            }
            // check if the exponent will work in the calculation function
            if (Exp < 0) {
                throw new PowerException();
            }

            //calculate result
            System.out.println("Calculating [A]^"+ Exp);
            double[][] Result = calculationFunctions.power(A, Exp);
            inputsAndOutputs.printMatrix(Result, A[0].length, A.length);
            //inputsAndOutputs.askAndSaveMatrix(Result);
            return Result;
        } catch (NonSquareMatrixException | PowerException e ) {
            OperationTracker.removeSingleOperation(operation);
            System.out.println("Matrix Operation Exception: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Matrix Operation Exception: " 
            + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            //inputsAndOutputs.askUserToQuitOrRunAgain();
        }
        return null;
    }

    // this method is just here to satisfy the interface
    public double [][] performOperation(double[][] A, SingleMatrixOperation operation) {
        return A;
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
