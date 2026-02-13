package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

import main.model.ElementDivisionOperation;
import main.model.MultiMatrixOperation;

public class ElementDivisionOperationTest {
    
    @Test
    public void testPass() {
        try {
            double[][] A = {{1, 2}, {3, 4}};
            double[][] B = {{1, 2}, {3, 4}};
            MultiMatrixOperation a = new ElementDivisionOperation();
            double[][] result = a.performOperation(A, B, a);
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
                {11, 12, 13, 14, 15, 16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25, 26, 27, 28, 29, 30},
                {31, 32, 33, 34, 35, 36, 37, 38, 39, 40},
                {41, 42, 43, 44, 45, 46, 47, 48, 49, 50},
                {51, 52, 53, 54, 55, 56, 57, 58, 59, 60},
                {61, 62, 63, 64, 65, 66, 67, 68, 69, 70},
                {71, 72, 73, 74, 75, 76, 77, 78, 79, 80},
                {81, 82, 83, 84, 85, 86, 87, 88, 89, 90},
                {91, 92, 93, 94, 95, 96, 97, 98, 99, 100}
            };
            double[][] B = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15, 16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25, 26, 27, 28, 29, 30},
                {31, 32, 33, 34, 35, 36, 37, 38, 39, 40},
                {41, 42, 43, 44, 45, 46, 47, 48, 49, 50},
                {51, 52, 53, 54, 55, 56, 57, 58, 59, 60},
                {61, 62, 63, 64, 65, 66, 67, 68, 69, 70},
                {71, 72, 73, 74, 75, 76, 77, 78, 79, 80},
                {81, 82, 83, 84, 85, 86, 87, 88, 89, 90},
                {91, 92, 93, 94, 95, 96, 97, 98, 99, 100}
            };
            MultiMatrixOperation a = new ElementDivisionOperation();
            double[][] result = a.performOperation(A, B, a);
            // Assert that the result matrix is not null
            assertNotNull(result, "Resulting matrix is null");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testDimensionsFail() {
        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{1}, {3}};
        MultiMatrixOperation a = new ElementDivisionOperation();
        // Redirect System.out to capture printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    
        try {
            a.performOperation(A, B, a);
        } catch (Exception e) {

            String consoleOutput = outputStream.toString();
            assertTrue(consoleOutput.contains("Matrix Operation Exception: Incorrect dimensions for operation: dimensions of both matrices must be the same"), 
                   "Expected message not found in console output");
        }
        System.setOut(originalOut);
    }
}
