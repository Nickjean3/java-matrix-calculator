package main.model;

import java.util.Objects;
import javax.swing.JOptionPane;

//import main.ui.inputsAndOutputs;

public class DeterminantOperation implements SingleMatrixOperation {

    // NOTE: console based methods have been commented out, but are in the places where they should be.
    // REQUIRES: A must be a non-null square matrix represented by a 2D array.
    //           The SingleMatrixOperation parameter must not be null.
    // MODIFIES: Modifies the OperationTracker by tracking or removing the performed operation.
    // EFFECTS:  Attempts to calculate the determinant of matrix A.
    //           If the matrix is not square, throws a NonSquareMatrixException.
    //           If the operation is successful, returns the determinant of matrix A.
    //           If an exception occurs during the operation, displays an error message, 
    //           removes the operation from the tracker, and returns null.
    @Override
    public double[][] performOperation(double[][] A, SingleMatrixOperation operation) {
        try {
             //track operation
             OperationTracker.trackSingleOperation(operation);

            //check if the matrix is square
            if (A.length != A[0].length) {
                throw new NonSquareMatrixException();
            }
           
            // Calculate and display the determinant
            double [][] determinant = calculationFunctions.determinant(A);
            System.out.println("Determinant of the matrix is: " + determinant[0][0]);
            return determinant;
        } catch (NonSquareMatrixException e) {
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