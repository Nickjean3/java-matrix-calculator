package test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import org.junit.Test;

import main.model.AdditionOperation;
import main.model.InverseOperation;
import main.model.MultiMatrixOperation;
import main.model.OperationTracker;
import main.model.ScalarDivisionOperation;
import main.model.ScalarMatrixOperation;
import main.model.SingleMatrixOperation;

public class OperationTrackerTest {

    
    // Initialize sample operations
    MultiMatrixOperation multiOperation = new AdditionOperation();
    SingleMatrixOperation singleOperation = new InverseOperation();
    ScalarMatrixOperation scalarOperation = new ScalarDivisionOperation();


    @Test
    public void testTrackMultiOperation() {
        OperationTracker.trackMultiOperation(multiOperation);
        Map<String, Integer> counts = OperationTracker.getMultiOperationCounts();
        assertEquals(1, counts.get("AdditionOperation"));
    }
    
    @Test
    public void testTrackSingleOperation() {
        OperationTracker.trackSingleOperation(singleOperation);
        Map<String, Integer> counts = OperationTracker.getSingleOperationCounts();
        assertEquals(1, counts.get("InverseOperation"));
    }
    
    @Test
    public void testTrackScalarOperation() {
        OperationTracker.trackScalarOperation(scalarOperation);
        Map<String, Integer> counts = OperationTracker.getScalarOperationCounts();
        assertEquals(1, counts.get("ScalarDivisionOperation"));
    }
    
    @Test
    public void testRemoveMultiOperation() {
        OperationTracker.trackMultiOperation(multiOperation);
        OperationTracker.removeOperation(multiOperation);
        Map<String, Integer> counts = OperationTracker.getMultiOperationCounts();
        assertNull(counts.get("MultiMatrixOperation"));
    }
    
    @Test
    public void testRemoveSingleOperation() {
        OperationTracker.trackSingleOperation(singleOperation);
        OperationTracker.removeSingleOperation(singleOperation);
        Map<String, Integer> counts = OperationTracker.getSingleOperationCounts();
        assertNull(counts.get("SingleMatrixOperation"));
    }
    
    @Test
    public void testRemoveScalarOperation() {
        OperationTracker.trackScalarOperation(scalarOperation);
        OperationTracker.removeScalarOperation(scalarOperation);
        Map<String, Integer> counts = OperationTracker.getScalarOperationCounts();
        assertNull(counts.get("ScalarMatrixOperation"));
    }
}
