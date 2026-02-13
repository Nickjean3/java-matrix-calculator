package test;

import static org.junit.Assert.assertArrayEquals;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.model.calculationFunctions;


public class calculationFunctionsTest {
    
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    //private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    double[][] A = {{1, 2}, {3, 4}};
    double[][] B = {{5, 6},{7, 8}};
    double scalar = 2.0;
    double delta = 1e-10; // Set a small delta 

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testDuplicateMatrix() {
        double[][] A = {{1, 2},{3, 4}};
        double[][] expectedMatrix = {{1, 2}, {3, 4}};
        double[][] resultMatrix = calculationFunctions.duplicateMatrix(A);
        assertArrayEquals(expectedMatrix, resultMatrix);
    }

    @Test
    public void testMultiplication() {
        double[][] A = {{1, 1}, {1, 1}};
        double[][] B = {{1, 1},{1, 1}};
        double[][] expectedResult = {{2, 2},{2, 2}};
        double[][] resultMatrix = calculationFunctions.multiplication(A, B);
        assertArrayEquals(expectedResult, resultMatrix);
    }
    
    @Test
    public void testSubmatrix() {
        double[][] A = {{1, 2, 3}, {4, 5, 6},{7, 8, 9}};
        double[][] expectedResult = {{2, 3},{5, 6}};
        double[][] resultMatrix = calculationFunctions.submatrix(A, 2, 0);
        assertArrayEquals(expectedResult, resultMatrix);
    }

    @Test
    public void testAdditionSmall() {
        double[][] expectedResult = {{6,8},{10,12}};
        double[][] resultMatrix = calculationFunctions.addition(A, B);
        assertArrayEquals(expectedResult, resultMatrix);
    }
    @Test
    public void testSubtractionSmall() {
        double[][] expectedResult = {{-4,-4},{-4,-4}};
        double[][] resultMatrix = calculationFunctions.subtraction(A, B);
        assertArrayEquals(expectedResult, resultMatrix);
    }
    @Test
    public void testInverseSmall() {
        double[][] expectedResult = {{-2,1},{1.5,-0.5}};
        double[][] resultMatrix = calculationFunctions.inverse(A);
        assertArrayEquals(expectedResult, resultMatrix);
    }
    @Test
    public void testDeterminantSmall() {   
        double [][] expectedResult = new double[1][1]; 
        expectedResult[0][0] = -2; 
        double [][] determinant = calculationFunctions.determinant(A);
        assertArrayEquals(expectedResult, determinant); 
    }
    @Test
    public void testTransposeSmall() {
        double[][] expectedResult = {{1,3},{2,4}};
        double[][] resultMatrix = calculationFunctions.transpose(A);
        assertArrayEquals(expectedResult, resultMatrix);
    }
    @Test
    public void testPowerSmall() {
        int Exp = 2;
        double[][] expectedResult = {{7,10},{15,22}};
        double[][] resultMatrix = calculationFunctions.power(A, Exp);
        assertArrayEquals(expectedResult, resultMatrix);
    }
    @Test
    public void testPowerexp0() {
        int Exp = 0;
        double[][] expectedResult = {{1,0},{0,1}};
        double[][] resultMatrix = calculationFunctions.power(A, Exp);
        assertArrayEquals(expectedResult, resultMatrix);
    }
    @Test
    public void testPower1() {
        int Exp = 1;
        double[][] expectedResult = {{1, 2},{3, 4}};
        double[][] resultMatrix = calculationFunctions.power(A, Exp);
        assertArrayEquals(expectedResult, resultMatrix);
    }
    @Test
    public void testIdentityMatrixSmall() {
        double[][] expectedResult = {{1,0},{0,1}};
        double[][] resultMatrix = calculationFunctions.identityMatrix(A);
        assertArrayEquals(expectedResult, resultMatrix);
    }
    @Test
    public void testScalarMultiOperation() {
        double [][] expectedResult = {{2, 4},{6, 8}};
        double [][] resultMatrix = calculationFunctions.scalarOperation(A, scalar, (a, s) -> a * s);
        assertArrayEquals(expectedResult, resultMatrix);
    }
    @Test
    public void testScalarDivOperation() {
        double [][] expectedResult = {{0.5, 1},{1.5, 2}};
        double [][] resultMatrix = calculationFunctions.scalarOperation(A, scalar, (a, s) -> a / s);
        assertArrayEquals(expectedResult, resultMatrix);
    }

    @Test
    public void testElementMultiOperation() {
        double [][] expectedResult = {{5, 12},{21, 32}};
        double [][] resultMatrix = calculationFunctions.elementwiseOperation(A, B, (a, s) -> a * s);
        assertArrayEquals(expectedResult, resultMatrix);
    }
    @Test
    public void testElementDivOperation() {
        double [][] expectedResult = {{5, 3},{2.3333333333333335, 2}};
        double [][] resultMatrix = calculationFunctions.elementwiseOperation(B, A, (a, s) -> a / s);
        assertArrayEquals(expectedResult, resultMatrix);
    }
}

