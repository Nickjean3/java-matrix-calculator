package main.model;

import java.util.HashMap;
import java.util.Map;

public class OperationTracker {
    //declaring fields
    private static Map<MultiMatrixOperation, Integer> multioperationCounts = new HashMap<>();
    private static Map<SingleMatrixOperation, Integer> singleOperationCounts = new HashMap<>();
    private static Map<ScalarMatrixOperation, Integer> scalarOperationCounts = new HashMap<>();

    // REQUIRES: operation must be a non-null MultiMatrixOperation instance.
    // MODIFIES: multioperationCounts map.
    // EFFECTS:  Tracks the number of times the specified MultiMatrixOperation instance has been performed.
    //           Increments the count for the specified operation in the multioperationCounts map.
    public static void trackMultiOperation(MultiMatrixOperation operation) {
        multioperationCounts.put(operation, multioperationCounts.getOrDefault(operation, 0) + 1);
    }

    // REQUIRES: operation must be a non-null SingleMatrixOperation instance.
    // MODIFIES: singleOperationCounts map.
    // EFFECTS:  Tracks the number of times the specified SingleMatrixOperation instance has been performed.
    //           Increments the count for the specified operation in the singleOperationCounts map.
    public static void trackSingleOperation(SingleMatrixOperation operation) {
        singleOperationCounts.put(operation, singleOperationCounts.getOrDefault(operation, 0) + 1);
    }

    // REQUIRES: operation must be a non-null ScalarMatrixOperation instance.
    // MODIFIES: scalarOperationCounts map.
    // EFFECTS:  Tracks the number of times the specified ScalarMatrixOperation instance has been performed.
    //           Increments the count for the specified operation in the scalarOperationCounts map.
    public static void trackScalarOperation(ScalarMatrixOperation operation) {
        scalarOperationCounts.put(operation, scalarOperationCounts.getOrDefault(operation, 0) + 1);
    }

    // EFFECTS:  Returns a map containing counts of multi-matrix operations performed during the session.
    //           The keys are operation names, and the values are corresponding operation counts.
    public static Map<String, Integer> getMultiOperationCounts() {
        return convertToStringIntegerMap(multioperationCounts);
    }

    // EFFECTS:  Returns a map containing counts of single-matrix operations performed during the session.
    //           The keys are operation names, and the values are corresponding operation counts.
    public static Map<String, Integer> getSingleOperationCounts() {
        return convertToStringIntegerMap(singleOperationCounts);
    }

    // EFFECTS:  Returns a map containing counts of scalar-matrix operations performed during the session.
    //           The keys are operation names, and the values are corresponding operation counts.
    public static Map<String, Integer> getScalarOperationCounts() {
        return convertToStringIntegerMap(scalarOperationCounts);
    }
    
    // REQUIRES: The input map must not be null.
    // EFFECTS:  Converts the input map, which has keys of type T and Integer values,
    //           into a new map where the keys are the simple class names of the keys
    //           in the original map and the values are the same as in the original map.
    //           Returns the converted map.
    private static <T> Map<String, Integer> convertToStringIntegerMap(Map<T, Integer> map) {
        Map<String, Integer> convertedMap = new HashMap<>();
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            convertedMap.put(entry.getKey().getClass().getSimpleName(), entry.getValue());
        }
        return convertedMap;
    }

    // REQUIRES: The input operation must not be null.
    // MODIFIES: The multioperationCounts map.
    // EFFECTS:  If the input operation exists in the multioperationCounts map, decrements
    //           its count by 1 if the count is greater than 1. Otherwise, removes the
    //           operation from the map if the count is 1.
    public static void removeOperation(MultiMatrixOperation operation) {
        if (multioperationCounts.containsKey(operation)) {
            int count = multioperationCounts.get(operation);
            if (count > 1) {
                // Decrement the count
                multioperationCounts.put(operation, count - 1); 
            } else {
                // Remove the operation if count is 1
                multioperationCounts.remove(operation); 
            }
        }
    }

    // REQUIRES: The input operation must not be null.
    // MODIFIES: The singleOperationCounts map.
    // EFFECTS:  If the input operation exists in the singleOperationCounts map, decrements
    //           its count by 1 if the count is greater than 1. Otherwise, removes the
    //           operation from the map if the count is 1.
    public static void removeSingleOperation(SingleMatrixOperation operation) {
        if (singleOperationCounts.containsKey(operation)) {
            int count = singleOperationCounts.get(operation);
            if (count > 1) {
                // Decrement the count
                singleOperationCounts.put(operation, count - 1); 
            } else {
                // Remove the operation if count is 1
                singleOperationCounts.remove(operation); 
            }
        }
    }

    // REQUIRES: The input operation must not be null.
    // MODIFIES: The scalarOperationCounts map.
    // EFFECTS:  If the input operation exists in the scalarOperationCounts map, decrements
    //           its count by 1 if the count is greater than 1. Otherwise, removes the
    //           operation from the map if the count is 1.
    public static void removeScalarOperation(ScalarMatrixOperation operation) {
        if (scalarOperationCounts.containsKey(operation)) {
            int count = scalarOperationCounts.get(operation);
            if (count > 1) {
                // Decrement the count
                scalarOperationCounts.put(operation, count - 1); 
            } else {
                // Remove the operation if count is 1
                scalarOperationCounts.remove(operation); 
            }
        }
    }

    // NOTE: this method is only for console based program
    // EFFECTS:  Prints the multi-matrix operations performed along with their counts
    //           in the format "OperationName: Count" to the console.
    public static void displayMultiOperations() {
        System.out.println("Multi-Matrix Operations performed:");
        for (MultiMatrixOperation operation : multioperationCounts.keySet()) {
            int count = multioperationCounts.get(operation);
            System.out.println(operation.getClass().getSimpleName() + ": " + count);
        }
    }

    // NOTE: this method is only for console based program
    // EFFECTS:  Prints the single-matrix operations performed along with their counts
    //           in the format "OperationName: Count" to the console.
    public static void displaySingleOperations() {
        System.out.println("Single-Matrix Operations performed:");
        for (SingleMatrixOperation operation : singleOperationCounts.keySet()) {
            int count = singleOperationCounts.get(operation);
            System.out.println(operation.getClass().getSimpleName() + ": " + count);
        }
    }

    // NOTE: this method is only for console based program
    // EFFECTS:  Prints the scalar-matrix operations performed along with their counts
    //           in the format "OperationName: Count" to the console.
    public static void displayScalarOperations() {
        System.out.println("Scalar-Matrix Operations performed:");
        for (ScalarMatrixOperation operation : scalarOperationCounts.keySet()) {
            int count = scalarOperationCounts.get(operation);
            System.out.println(operation.getClass().getSimpleName() + ": " + count);
        }
    }

    // method that calls all submethods to save space
    public static void displayOperations() {
        displayMultiOperations();
        displaySingleOperations();
        displayScalarOperations();
    }
}