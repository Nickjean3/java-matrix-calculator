package main.model;

import java.util.function.DoubleBinaryOperator;

public class calculationFunctions {
    
    //REQUIRES: two input matrices A and B that are not null, number of columns of A..
    //          must be equal to the columns of B and rows of A must equal rows of B
    //EFFECTS: returns a new matrix that is the result of the addition of matrix A and B..
    //         with the dimensions of [rows of A] x [columns of A]
    public static double[][] addition(double[][] A, double[][] B) {
        //initialise a 2D array with the correct dimensions
        double Result[][] = new double[A.length][A[0].length];
        
        //for loop to iterate through the matrix, adding the terms
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                Result[i][j] = A[i][j] + B[i][j];
            }
        }
        return Result;
    }
    
    //REQUIRES: two input matrices A and B that are not null, number of columns of A..
    //          must be equal to the columns of B and rows of A must equal rows of B
    //EFFECTS: returns a new matrix that is the result of the subtraction of matrix A and B..
    //         with the dimensions of [rows of A] x [columns of A]
    public static double[][] subtraction(double[][] A, double[][] B) {
        //initialise a 2D array with the correct dimensions
        double Result[][] = new double[A.length][A[0].length];

        //for loop to iterate through the matrix, subtracting the terms
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                Result[i][j] = A[i][j] - B[i][j];
            }
        }
        return Result;
    }

    //REQUIRES: two input matrices A and B that are not null, number of columns of A..
    //          must be equal to the rows of B
    //EFFECTS: returns a new matrix that is the result of the multiplication of matrix A and B..
    //         with the dimensions of [rows of A] x [columns of B]
    public static double[][] multiplication(double[][] A, double[][] B) {
        
        //initialise a result matrix with the dimensions that will result from the multiplication
        double[][] Result = new double[A.length][B[0].length];

        //for loop to iterate through the matrix, multiplying and adding together the terms
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                Result[i][j] = 0;
                for (int k = 0; k < A[0].length; k++) {
                    Result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return Result;
    }

    //REQUIRES: Inputted matrix A must not be null. matrix A must also be square
    //MODIFIES: DuplicateA and I matrices
    //EFFECTS:  If A is singular, prints an error message and returns null.
    //          If A is valid, calculates the inverse matrix using Gaussian elimination on DuplicateA,
    //          modifies DuplicateA and I matrices in the process, and returns the resulting inverse matrix.
    public static double[][] inverse(double[][] A) {
        int rowsA = A.length;
        
        //create duplicate matrix for calculations as the values will be changed...
        //through calculations using the duplicateMatrix method
        double[][] DuplicateA = calculationFunctions.duplicateMatrix(A);

        //create identity matrix of dimensions of A using identityMatrix method
        double[][] I = identityMatrix(A);

        for (int i = 0; i < rowsA; i++) {
            double diagonalElement = DuplicateA[i][i];
    
            if (diagonalElement == 0) {
                // Handle the case of a zero diagonal element.
                System.out.println("Matrix is singular, cannot calculate inverse.");
                return null;
            }
    
            for (int j = 0; j < rowsA; j++) {
                if (i != j) {
                    double elementToSubtract = DuplicateA[j][i];
    
                    for (int k = 0; k < rowsA; k++) {
                        DuplicateA[j][k] -= (elementToSubtract / diagonalElement) * DuplicateA[i][k];
                        I[j][k] -= (elementToSubtract / diagonalElement) * I[i][k];
                    }
                }
            }
    
            // Divide everything by the diagonal element
            for (int j = 0; j < rowsA; j++) {
                DuplicateA[i][j] /= diagonalElement;
                I[i][j] /= diagonalElement;
            }
        } 
        return I;
    }


    // REQUIRES: inputted matrix A must not be null. matrix A must also be square
    // EFFECTS: returns the determinant of the square input matrix A using cofactor expansion
    public static double[][] determinant(double[][] A) {
        int n = A.length;

        // Base case: If the matrix is 1x1, return its only element as a double[][]
        if (n == 1) {
            double[][] result = new double[1][1];
            result[0][0] = A[0][0];
            return result;
        }

        double[][] determinantResult = new double[1][1];
        determinantResult[0][0] = 0;

        // Calculate the determinant using cofactor expansion along the first row
        for (int j = 0; j < n; j++) {
            double[][] submatrixResult = determinant(submatrix(A, 0, j));
            determinantResult[0][0] += A[0][j] * Math.pow(-1, j) * submatrixResult[0][0];
        }
        return determinantResult;
    }

    // REQUIRES: input matrix A must not be null. rowToRemove and colToRemover must be within...
    //           the valid range of the matrix A
    // MODIFIES: no external values
    // EFFECTS: returns a submatrix of a matrix A by removing the row and column 
    //          specified by the input. The dimensions of the returned matrix will be 
    //          [n-1] x [n-1], where n was the initial size of the A matrix
    public static double[][] submatrix(double[][] A, int rowToRemove, int colToRemove) {
        int n = A.length;
        double[][] submatrix = new double[n - 1][n - 1];

        for (int i = 0, newI = 0; i < n; i++) {
            if (i == rowToRemove) {
                continue; // Skip the row to be removed
            }

            for (int j = 0, newJ = 0; j < n; j++) {
                if (j == colToRemove) {
                    continue; // Skip the column to be removed
                }

                submatrix[newI][newJ] = A[i][j];
                newJ++;
            }
            newI++;
        }
        return submatrix;
    }

    // REQUIRES: A must be a non-null 2D array representing a matrix.
    // EFFECTS:  Calculates the transpose of the input matrix A.
    //           Creates a new 2D array to store the transpose.
    //           Iterates over the elements of A and assigns them to the corresponding positions 
    //           in the transpose. Returns the resulting transposed matrix.
    public static double[][] transpose(double[][] A) {
        
        double Result[][] = new double[A[0].length][A.length];
        //calculate the transpose
        for (int i = 0; i < A[0].length; i++) {
            for (int j = 0; j < A.length; j++) {
                    Result[i][j] = A[j][i];
            }
        }
        return Result;
    }

    // REQUIRES: A must be a non-null 2D array representing a matrix.
    //           Exp must be an integer.
    // EFFECTS:  If Exp is 0, returns the identity matrix of the same dimensions as A.
    //           Otherwise, calculates the matrix power of A raised to the Exp-th power.
    //           Returns the resulting matrix.
    public static double[][] power(double[][] A, int Exp) {
        // if exponent is zero, result is an identity matrix the size of A
        if (Exp == 0) {
            double [][] Result = identityMatrix(A);
            return Result; 
        }
        
        double[][] Result = duplicateMatrix(A);
        
        for (int i = 1; i < Exp; i++) {
            Result = multiplication(A, Result);
        }
        return Result;
    }

    //REQUIRES: An input matrix that is not null
    //EFFECTS: Returns a new matrix that is identical to the inputted matrix
    public static double[][] duplicateMatrix(double[][] A) {
        
        int rows = A.length;
        int columns = A[0].length;
        double[][] Duplicate = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Duplicate[i][j] = A[i][j];
            }
        }
        return Duplicate;
    }
    
    //REQUIRES: An input matrix that is not null
    //EFFECTS: Returns an identity matrix of dimensions of the imputted matrix
    public static double[][] identityMatrix(double[][] A) {
        
        //create identity matrix
        double[][] I = new double[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            I[i][i] = 1;
        }
        return I;
    }

    // REQUIRES: A must be a non-null 2D array representing a matrix.
    //           scalar must be a double.
    //           operation must be a non-null DoubleBinaryOperator.
    // EFFECTS:  Performs a scalar operation on matrix A with the given scalar value.
    //           The operation is determined by the provided DoubleBinaryOperator.
    //           Returns a new matrix resulting from the scalar operation.
    public static double[][] scalarOperation(double[][] A, double scalar, DoubleBinaryOperator operation) {
        
        int rows = A.length;
        int columns = A[0].length;
        double[][] Result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Result[i][j] = operation.applyAsDouble(A[i][j], scalar);
            }
        }
        return Result;
    }

    // REQUIRES: A and B must be non-null 2D arrays representing matrices with the same dimensions.
    //           operation must be a non-null DoubleBinaryOperator.
    // EFFECTS:  Performs an element-wise operation on matrices A and B using the given operation.
    //           Returns a new matrix resulting from the element-wise operation.
    public static double[][] elementwiseOperation(double[][] A, double[][] B, DoubleBinaryOperator operation) {
        int rows = A.length;
        int columns = A[0].length;
        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = operation.applyAsDouble(A[i][j], B[i][j]);
            }
        }
        return result;
    }
}

