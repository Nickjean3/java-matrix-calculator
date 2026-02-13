package main.model;

import java.util.Objects;
import javax.swing.JOptionPane;

import main.ui.inputsAndOutputs;


public class InverseOperation implements SingleMatrixOperation {
    // NOTE: console based methods have been commented out, but are in the places where they should be.
    // REQUIRES: A must be a non-null square matrix represented by a 2D array.
    //           The SingleMatrixOperation parameter must not be null.
    // MODIFIES: Modifies the OperationTracker by tracking the performed operation.
    // EFFECTS:  Attempts to calculate the inverse of matrix A.
    //           If the matrix is not square, throws a NonSquareMatrixException.
    //           If the matrix is singular (determinant close to zero), throws a SingularMatrixException.
    //           If the operation is successful, returns the resulting inverse matrix.
    //           If an exception occurs during the operation, removes the operation 
    //           from the OperationTracker and displays an error message, returns null
    @Override
    public double[][] performOperation(double[][] A, SingleMatrixOperation operation) {
        try { 
            //track operation
            OperationTracker.trackSingleOperation(operation);
  
            //check if the matrix is square
            if (A.length != A[0].length) {
                throw new NonSquareMatrixException();
            }
            // check if the matrix is singular
            double [][] determinant = calculationFunctions.determinant(A);
            if (Math.abs(determinant[0][0]) < 0.000001) {
                throw new SingularMatrixException();
            }
            
            //calculate result
            System.out.println("Calculating [A]^-1... ");
            double[][] result = calculationFunctions.inverse(A);
            inputsAndOutputs.printMatrix(result, A.length, A[0].length);
            //inputsAndOutputs.askAndSaveMatrix(result);
            return result;
        } catch (NonSquareMatrixException | SingularMatrixException e) {
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