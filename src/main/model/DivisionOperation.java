package main.model;

import javax.swing.JOptionPane;

import main.ui.inputsAndOutputs;

public class DivisionOperation extends MultiMatrixOperation {
    
    // NOTE: console based methods have been commented out, but are in the places where they should be.
    // REQUIRES: A and B must be non-null 2D arrays representing matrices. 
    //           The operation parameter must not be null.
    // MODIFIES: Modifies the OperationTracker by tracking the performed operation.
    // EFFECTS:  Attempts to perform division ([A]x[B]^-1) between matrices A and B.
    //           If the division is successful, returns the resulting matrix.
    //           If the dimensions of B are not square, throws NonSquareMatrixException
    //           If B is singular, throws SingularMatrixException
    //           If the dimensions of A and B are incompatible for multiplication, 
    //           throws an IncorrectMatrixDimensionsException.
    //           If an exception occurs during the operation, removes the operation 
    //           from the OperationTracker and displays an error message.
    //           Displays a success message if the operation is performed successfully.
    //           Returns null if the operation fails.
    @Override
    public double[][] performOperation(double[][] A, double[][] B, MultiMatrixOperation operation) {
        try {
            //track operation
            OperationTracker.trackMultiOperation(operation);

            // check if divisor matrix is square
            if (B.length != B[0].length) {
                throw new NonSquareMatrixException();
            }
            // check if multiplication is possible, as division of matrices is [A]*[B]^-1
            else if (A[0].length != B.length) {
                throw new IncorrectMatrixDimensionsException(IncorrectMatrixDimensionsException.COLUMN_ROW_MISMATCH);
            }
            // check if determinant of the matrix to be inversed is nonzero
            double [][] determinant = calculationFunctions.determinant(B);
            if (Math.abs(determinant[0][0]) < 0.000001) {
                throw new SingularMatrixException();
            }

            //calculate result
            System.out.println("Calculating [A] * [B]^-1... ");
            double[][] invB = calculationFunctions.inverse(B);
            double[][] result = calculationFunctions.multiplication(A, invB);
            inputsAndOutputs.printMatrix(result, A.length, B[0].length);
            //inputsAndOutputs.askAndSaveMatrix(result);
            return result;
        } catch (IncorrectMatrixDimensionsException | NonSquareMatrixException 
            | SingularMatrixException e) {
            OperationTracker.removeOperation(operation);
            System.out.println("Matrix Operation Exception: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Matrix Operation Exception: " 
            + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            //inputsAndOutputs.askUserToQuitOrRunAgain();
        }
        return null;
    }
}
