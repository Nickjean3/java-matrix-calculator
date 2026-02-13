package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import main.model.ScalarDivisionOperation;
import main.model.ScalarMatrixOperation;

public class ScalarDivisionOperationTest {
    @Test
    public void testPass() {
        try {
            double[][] A = {{1, 2}, {3, 4}};
            double s = 2.0;
            ScalarMatrixOperation a = new ScalarDivisionOperation();
            double[][] result = a.performOperation(A, s, a);
            assertNotNull(result, "Resulting matrix is null");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testPass2() {
        try {
            double[][] A = {{1, 2}, {3, 4}};
            double s = -2.0;
            ScalarMatrixOperation a = new ScalarDivisionOperation();
            double[][] result = a.performOperation(A, s, a);
            assertNotNull(result, "Resulting matrix is null");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
