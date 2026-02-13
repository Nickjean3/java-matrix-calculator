package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import main.model.PowerOperation;
import main.model.SingleMatrixOperation;

public class PowerOperationTest {
    @Test
    public void testPass() {
        try {
            double[][] A = {{1, 2}, {3, 4}};
            int Exp = 2;
            SingleMatrixOperation a = new PowerOperation();
            double[][] result = ((PowerOperation) a).performPowerOperation(A, Exp, a);
            assertNotNull(result, "Resulting matrix is null");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
    @Test
    public void testPassLarge() {
        try {
            double[][] A = {{1, 2}, {3, 4}};
            int Exp = 1000;
            SingleMatrixOperation a = new PowerOperation();
            double[][] result = ((PowerOperation) a).performPowerOperation(A, Exp, a);
            assertNotNull(result, "Resulting matrix is null");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testFailSquare() {
            double[][] A = {{1, 2, 3}, {3, 4, 5}};
            int Exp = 2;
            SingleMatrixOperation a = new PowerOperation();
            // Redirect System.out to capture printed messages
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));
    
            try {
                ((PowerOperation) a).performPowerOperation(A, Exp, a);
            } catch (Exception e) {

                String consoleOutput = outputStream.toString();
                assertTrue(consoleOutput.contains("Matrix Operation Exception: Operation is not possible. Matrix must be square."), 
                   "Expected message not found in console output");
            }
            System.setOut(originalOut);
    }
    @Test
    public void testFailSquare2() {
            double[][] A = {{1, 2}, {3, 4}, {5, 6}};
            int Exp = 2;
            SingleMatrixOperation a = new PowerOperation();
            // Redirect System.out to capture printed messages
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));
    
            try {
                ((PowerOperation) a).performPowerOperation(A, Exp, a);
            } catch (Exception e) {

                String consoleOutput = outputStream.toString();
                assertTrue(consoleOutput.contains("Matrix Operation Exception: Operation is not possible. Matrix must be square."), 
                   "Expected message not found in console output");
            }
            System.setOut(originalOut);
    }
    @Test
    public void testFailPower() {
   
            double[][] A = {{1, 1}, {1, 1}};
            int Exp = -1;
            SingleMatrixOperation a = new PowerOperation();
            // Redirect System.out to capture printed messages
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));
    
            try {
                ((PowerOperation) a).performPowerOperation(A, Exp, a);
            } catch (Exception e) {

                String consoleOutput = outputStream.toString();
                assertTrue(consoleOutput.contains("Matrix Operation Exception: Exponent must be an integer greater or equal to 0."), 
                   "Expected message not found in console output");
            }
            System.setOut(originalOut);
        }
    
    @Test
    public void testPassEdgeCase() {
        try {
            double[][] A = {{1, 1}, {1, 1}};
            int Exp = 0;
            SingleMatrixOperation a = new PowerOperation();
            double[][] result = ((PowerOperation) a).performPowerOperation(A, Exp, a);
            assertNotNull(result, "Resulting matrix is null");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
