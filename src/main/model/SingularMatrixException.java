package main.model;

public class SingularMatrixException extends Exception {
    
    public SingularMatrixException() {
        super("Operation not possible. Matrix to be inverted must have a nonzero determinant.");
    }
}
