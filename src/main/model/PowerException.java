package main.model;

public class PowerException extends Exception {
    
    public PowerException() {
        super("Exponent must be an integer greater or equal to 0.");
    }
}
