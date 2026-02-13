package main.model;

public class IncorrectMatrixDimensionsException extends Exception {
        
    // Define constants for different cases of incorrect dimensions
    public static final int SAME_DIMENSIONS = 1;
    public static final int COLUMN_ROW_MISMATCH = 2;
    
    public IncorrectMatrixDimensionsException(int caseType) {
        super(getErrorMessage(caseType));
    }
    
    // Define a method to generate error message based on caseType
    private static String getErrorMessage(int caseType) {
        switch (caseType) {
            case SAME_DIMENSIONS:
                return "Incorrect dimensions for operation: dimensions of both matrices must be the same";
            case COLUMN_ROW_MISMATCH:
                return "Incorrect dimensions for operation: Columns of [A] must be equal to the rows of [B]";
            default:
                return "Incorrect dimensions for operation";
        }
    }
}
