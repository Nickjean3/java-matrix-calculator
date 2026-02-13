package main.model;

import javax.swing.JOptionPane;

import main.ui.inputsAndOutputs;

public class ElementMultiplicationOperation extends MultiMatrixOperation {
    
    // NOTE: console based methods have been commented out, but are in the places where they should be.
    // REQUIRES: A and B must be non-null 2D arrays representing matrices. 
    //           The operation parameter must not be null.
    // MODIFIES: Modifies the OperationTracker by tracking the performed operation.
    // EFFECTS:  Attempts to perform element-wise matrix multiplication between matrices A and B.
    //           If the multiplication is successful, returns the resulting matrix.
    //           If the dimensions of A and B are not equal, throws an IncorrectMatrixDimensionsException.
    //           If an exception occurs during the operation, removes the operation 
    //           from the OperationTracker and displays an error message.
    //           Displays a success message if the operation is performed successfully.
    //           Returns null if the operation fails.
    @Override
    public double[][] performOperation(double[][] A, double[][] B, MultiMatrixOperation operation) {
        try {
            //track operation
            OperationTracker.trackMultiOperation(operation);
            
            // check if multiplication is possible
            if (A.length != B.length || A[0].length != B[0].length) {
                throw new IncorrectMatrixDimensionsException(IncorrectMatrixDimensionsException.SAME_DIMENSIONS);
            }

            //calculate result
            System.out.println("Calculating [A] .* [B]... ");
            double[][] result = calculationFunctions.elementwiseOperation(A, B, (a, b) -> a * b);
            inputsAndOutputs.printMatrix(result, A.length, B[0].length);
            //inputsAndOutputs.askAndSaveMatrix(result);
            return result;
        } catch (IncorrectMatrixDimensionsException e) {
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
