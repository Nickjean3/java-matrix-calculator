package main.model;

public class NonSquareMatrixException extends Exception {
    
    public NonSquareMatrixException() {
        super("Operation is not possible. Matrix must be square.");
    }
}
