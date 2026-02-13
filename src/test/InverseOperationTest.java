package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

import main.model.InverseOperation;
import main.model.SingleMatrixOperation;

public class InverseOperationTest {
    @Test
    public void testPass() {
        try {
            double[][] A = {{1, 2}, {3, 4}};
            SingleMatrixOperation a = new InverseOperation();
            double[][] result = a.performOperation(A, a);
            // Assert that the result matrix is not null
            assertNotNull(result, "Resulting matrix is null");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
    @Test
    public void testLargePass() {
        try {
            double[][] A = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 1, 2, 0, 0, 1, 2, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 2, 3, 4, 5, 6},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 2, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
            };
            
            SingleMatrixOperation a = new InverseOperation();
            double[][] result = a.performOperation(A, a);
            // Assert that the result matrix is not null
            assertNotNull(result, "Resulting matrix is null");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testSquare1Fail() {
        double[][] A = {{1, 2, 3}, {3, 4, 5}};
        SingleMatrixOperation a = new InverseOperation();
        // Redirect System.out to capture printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    
        try {
            a.performOperation(A, a);
        } catch (Exception e) {

            String consoleOutput = outputStream.toString();
            assertTrue(consoleOutput.contains("Matrix Operation Exception: Operation is not possible. Matrix must be square."), 
                   "Expected message not found in console output");
        }
        System.setOut(originalOut);
    }
    @Test
    public void testSquare2Fail() {
        double[][] A = {{1, 2}, {3, 4},{5, 6}};
        SingleMatrixOperation a = new InverseOperation();
        // Redirect System.out to capture printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    
        try {
            a.performOperation(A, a);
        } catch (Exception e) {

            String consoleOutput = outputStream.toString();
            assertTrue(consoleOutput.contains("Matrix Operation Exception: Operation is not possible. Matrix must be square."), 
                   "Expected message not found in console output");
        }
        System.setOut(originalOut);
    }
    @Test
    public void testSingularFail() {
        double[][] B = {{1, 1}, {1, 1}};
        SingleMatrixOperation a = new InverseOperation();
        // Redirect System.out to capture printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    
        try {
            a.performOperation(B, a);
        } catch (Exception e) {

            String consoleOutput = outputStream.toString();
            assertTrue(consoleOutput.contains("Matrix Operation Exception: Operation not possible. Matrix to be inverted must have a nonzero determinant."), 
                   "Expected message not found in console output");
        }
        System.setOut(originalOut);
    }
    @Test
    public void testSingularEdgeCaseFail() {
        double[][] B = {{1, 1.000001}, {1, 1}};
        SingleMatrixOperation a = new InverseOperation();
        // Redirect System.out to capture printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    
        try {
            a.performOperation(B, a);
        } catch (Exception e) {

            String consoleOutput = outputStream.toString();
            assertTrue(consoleOutput.contains("Matrix Operation Exception: Operation not possible. Matrix to be inverted must have a nonzero determinant."), 
                   "Expected message not found in console output");
        }
        System.setOut(originalOut);
    }
    @Test
    public void testSingularEdgeCasePass() {
        try {
            double[][] B = {{1, 1}, {1, 1.00001}};
            SingleMatrixOperation a = new InverseOperation();
            double[][] result = a.performOperation(B, a);
            // Assert that the result matrix is not null
            assertNotNull(result, "Resulting matrix is null");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
