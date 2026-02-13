package main.ui;

import main.model.AdditionOperation;
import main.model.DeterminantOperation;
import main.model.DivisionOperation;
import main.model.ElementDivisionOperation;
import main.model.ElementMultiplicationOperation;
import main.model.InverseOperation;
import main.model.MultiMatrixOperation;
import main.model.MultiplicationOperation;
import main.model.OperationTracker;
import main.model.PowerOperation;
import main.model.ScalarDivisionOperation;
import main.model.ScalarMatrixOperation;
import main.model.ScalarMultiplicationOperation;
import main.model.SingleMatrixOperation;
import main.model.SubtractionOperation;
import main.model.TransposeOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class MatrixCalculatorGUI extends JFrame {
    // initialising all the GUI fields
    private JComboBox<String> operationTypeComboBox;
    private JComboBox<String> operationComboBox;
    private JLabel rowsLabel1;
    private JTextField rowsTextField1;
    private JLabel columnsLabel1;
    private JTextField columnsTextField1;
    private JLabel rowsLabel2;
    private JTextField rowsTextField2;
    private JLabel columnsLabel2;
    private JTextField columnsTextField2;
    private JPanel matrixAPanel;
    private JPanel matrixBPanel;
    private JTextField scalarTextField;
    private JButton createMatrixButton;
    private JButton performOperationButton;
    private JPanel resultingMatrixPanel; 
    private JPanel savePromptPanel; 
    private JPanel loadPromptPanel;
    private JTextField exponentTextField; 
    private JLabel exponentLabel;
    private JLabel scalarLabel;
    private JLabel loadAtext;
    private JLabel loadBtext;
    private JButton LoadBButton;
    private JButton quitButton;
    private JPanel quitPanel;
   
    public static void main(String[] args) {
        new MatrixCalculatorGUI();
    }

    // MatrixCalculatorGUI constructor, initialises the GUI and all the fields
    public MatrixCalculatorGUI() {

        //initialising the GUI
        setTitle("Matrix Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 1000);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10, 1));
        
        //setting up the drop downs for all the possible operations
        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(new FlowLayout());
        JLabel operationTypeLabel = new JLabel("Select operation type:");
        operationTypeComboBox = new JComboBox<>(new String[]{"Single Matrix Operations", 
            "Multi Matrix Operations", "Element by Element Operations", "Scalar Operations"});
        JLabel operationLabel = new JLabel("Select operation:");
        operationComboBox = new JComboBox<>();
        operationPanel.add(operationTypeLabel);
        operationPanel.add(operationTypeComboBox);
        operationPanel.add(operationLabel);
        operationPanel.add(operationComboBox);

        mainPanel.add(operationPanel);

        //create the panel for the inputs of rows and columns of both matrices
        JPanel matrixDimensionsPanel = new JPanel();
        matrixDimensionsPanel.setLayout(new FlowLayout());
        rowsLabel1 = new JLabel("[A] Rows:");
        rowsTextField1 = new JTextField(5);
        columnsLabel1 = new JLabel("[A] Columns:");
        columnsTextField1 = new JTextField(5);
        rowsLabel2 = new JLabel("[B] Rows:");
        rowsTextField2 = new JTextField(5);
        columnsLabel2 = new JLabel("[B] Columns:");
        columnsTextField2 = new JTextField(5);
        matrixDimensionsPanel.add(rowsLabel1);
        matrixDimensionsPanel.add(rowsTextField1);
        matrixDimensionsPanel.add(columnsLabel1);
        matrixDimensionsPanel.add(columnsTextField1);
        matrixDimensionsPanel.add(rowsLabel2);
        matrixDimensionsPanel.add(rowsTextField2);
        matrixDimensionsPanel.add(columnsLabel2);
        matrixDimensionsPanel.add(columnsTextField2);
        mainPanel.add(matrixDimensionsPanel);

        // create the create matrix button, which generates the correct size of matrix text boxes
        // using the inputs in the dimensions text boxes
        createMatrixButton = new JButton("Create Matrix");
        createMatrixButton.setPreferredSize(new Dimension(120, 5));
        createMatrixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMatrixPanels();
            }
        });
        mainPanel.add(createMatrixButton);

        //create the button for loading the stored matrix into matrix A
        loadPromptPanel = new JPanel(new FlowLayout());
        JButton LoadAButton = new JButton("Load [A]");
        LoadAButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            double[][] loadedMatrix = inputsAndOutputs.loadMatrixFromFile();
            if (loadedMatrix != null) {
                // Fill in rows A, columns A text boxes with the dimensions of the loaded matrix
                rowsTextField1.setText(String.valueOf(loadedMatrix.length));
                columnsTextField1.setText(String.valueOf(loadedMatrix[0].length));
            
                // Fill in matrix A panel with the loaded matrix values
                fillMatrixAPanel(loadedMatrix);
                matrixAPanel.setVisible(true);
                JOptionPane.showMessageDialog(MatrixCalculatorGUI.this, "Matrix loaded successfully.");
            } else {
                JOptionPane.showMessageDialog(MatrixCalculatorGUI.this, "Failed to load matrix.");
            }
        }
        }); 

        //create the button for loading the stored matrix into matrix B
        LoadBButton = new JButton("Load [B]");
        LoadBButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        double[][] loadedMatrix = inputsAndOutputs.loadMatrixFromFile();
        if (loadedMatrix != null) {
            // Fill in rows A, columns A text boxes with the dimensions of the loaded matrix
            rowsTextField2.setText(String.valueOf(loadedMatrix.length));
            columnsTextField2.setText(String.valueOf(loadedMatrix[0].length));
            
            // Fill in matrix A panel with the loaded matrix values
            fillMatrixBPanel(loadedMatrix);
            matrixBPanel.setVisible(true);
            JOptionPane.showMessageDialog(MatrixCalculatorGUI.this, "Matrix loaded successfully.");
        } else {
            JOptionPane.showMessageDialog(MatrixCalculatorGUI.this, "Failed to load matrix.");
        }
    }
    }); 
        loadAtext = new JLabel("Load saved matrix into [A]?");
        loadPromptPanel.add(loadAtext);
        loadPromptPanel.add(LoadAButton);
        loadBtext = new JLabel("Load saved matrix into [B]?");
        loadPromptPanel.add(loadBtext);
        loadPromptPanel.add(LoadBButton);
        mainPanel.add(loadPromptPanel); 
        loadPromptPanel.setVisible(true);

        //create the panels to input the [A] and [B] matrices
        JPanel matricesPanel = new JPanel(new GridLayout(1, 2));

        matrixAPanel = new JPanel();
        matrixAPanel.setBorder(BorderFactory.createTitledBorder("Matrix A"));
        matricesPanel.add(matrixAPanel);

        matrixBPanel = new JPanel();
        matrixBPanel.setBorder(BorderFactory.createTitledBorder("Matrix B"));
        matricesPanel.add(matrixBPanel);

        mainPanel.add(matricesPanel);
        matrixAPanel.setVisible(false);
        matrixBPanel.setVisible(false);

        //create the panel for the exponent and scalar text boxes
        JPanel singlevalues = new JPanel();
        singlevalues.setLayout(new FlowLayout());
        scalarLabel = new JLabel("Scalar:");
        scalarTextField = new JTextField(5);
        exponentLabel = new JLabel("Exponent:");
        exponentTextField = new JTextField(5);
        singlevalues.add(scalarLabel);
        singlevalues.add(scalarTextField);
        singlevalues.add(exponentLabel);
        singlevalues.add(exponentTextField);
        mainPanel.add(singlevalues);
       
        updateMatrixDimensionsVisibility();

        // Update visibility when operation selection changes for 1st drop down
        operationTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOperationComboBox();
                updateMatrixDimensionsVisibility();
            }
        });

        // Update visibility when operation selection changes for second drop down
        operationComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMatrixDimensionsVisibility(); 
            }
        });

        // Branches for Perform Operation Button for all cases in the drop downs
        performOperationButton = new JButton("Perform Operation");
        performOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOperationType = (String) operationTypeComboBox.getSelectedItem();
                String selectedOperation = (String) operationComboBox.getSelectedItem();
        
                switch (selectedOperationType) {
                    case "Single Matrix Operations":
                        if (selectedOperation.equals("Inverse")) {
                            SingleMatrixOperation inverseOperation = new InverseOperation();
                            double[][] A = getMatrixA();
                            double[][] R = inverseOperation.performOperation(A, inverseOperation);
                            displayResultingMatrix(R);
                        } else if (selectedOperation.equals("Determinant")) {
                            SingleMatrixOperation determinantOperation = new DeterminantOperation();
                            double[][] A = getMatrixA();
                            double[][] R = determinantOperation.performOperation(A, determinantOperation);
                            displayResultingMatrix(R);
                        } else if (selectedOperation.equals("Transpose")) {
                            SingleMatrixOperation transposeOperation = new TransposeOperation();
                            double[][] A = getMatrixA();
                            double[][] R = transposeOperation.performOperation(A, transposeOperation);
                            displayResultingMatrix(R);
                        } else if (selectedOperation.equals("Power")) {
                            SingleMatrixOperation powerOperation = new PowerOperation();
                            double[][] A = getMatrixA();
                            int Exp = getExponent();
                            double[][] R = ((PowerOperation) powerOperation).performPowerOperation(A, Exp, powerOperation);
                            displayResultingMatrix(R);
                        } else {
                            JOptionPane.showMessageDialog(MatrixCalculatorGUI.this, "Operation not implemented yet.");
                        }
                        break;
                    case "Multi Matrix Operations":
                        if (selectedOperation.equals("Addition")) {
                            MultiMatrixOperation additionOperation = new AdditionOperation();
                            double[][] A = getMatrixA();
                            double[][] B = getMatrixB();
                            double[][] R = additionOperation.performOperation(A, B, additionOperation);
                            displayResultingMatrix(R);               
                        } else if (selectedOperation.equals("Subtraction")) {
                            MultiMatrixOperation subtractionOperation = new SubtractionOperation();
                            double[][] A = getMatrixA();
                            double[][] B = getMatrixB();
                            double[][] R = subtractionOperation.performOperation(A, B, subtractionOperation);
                            displayResultingMatrix(R);
                        } else if (selectedOperation.equals("Multiplication")) {
                            MultiMatrixOperation multiplicationOperation = new MultiplicationOperation();
                            double[][] A = getMatrixA();
                            double[][] B = getMatrixB();
                            double[][] R = multiplicationOperation.performOperation(A, B, multiplicationOperation);
                            displayResultingMatrix(R);
                        } else if (selectedOperation.equals("Division")) {
                            MultiMatrixOperation divisionOperation = new DivisionOperation();
                            double[][] A = getMatrixA();
                            double[][] B = getMatrixB();
                            double[][] R = divisionOperation.performOperation(A, B, divisionOperation);
                            displayResultingMatrix(R);
                        } else {
                            JOptionPane.showMessageDialog(MatrixCalculatorGUI.this, "Operation not implemented yet.");
                        }
                        break;
                    case "Element by Element Operations":
                        if (selectedOperation.equals("Multiplication")) {
                            MultiMatrixOperation elementMultiplicationOperation = new ElementMultiplicationOperation();
                            double[][] A = getMatrixA();
                            double[][] B = getMatrixB();
                            double[][] R = elementMultiplicationOperation.performOperation(A, B, elementMultiplicationOperation);
                            displayResultingMatrix(R);
                        } else if (selectedOperation.equals("Division")) {
                            MultiMatrixOperation elementDivisionOperation = new ElementDivisionOperation();
                            double[][] A = getMatrixA();
                            double[][] B = getMatrixB();
                            double[][] R = elementDivisionOperation.performOperation(A, B, elementDivisionOperation);
                            displayResultingMatrix(R);
                        } else {
                            JOptionPane.showMessageDialog(MatrixCalculatorGUI.this, "Operation not implemented yet.");
                        }
                        break;
                    case "Scalar Operations":
                        if (selectedOperation.equals("Scalar Multiplication")) {
                            ScalarMatrixOperation scalarMultiplicationOperation = new ScalarMultiplicationOperation();
                            double[][] A = getMatrixA();
                            double scalar = getScalarValue();
                            double[][] R = scalarMultiplicationOperation.performOperation(A, scalar, scalarMultiplicationOperation);
                            displayResultingMatrix(R);
                        } else if (selectedOperation.equals("Scalar Division")) {
                            ScalarMatrixOperation scalarDivisionOperation = new ScalarDivisionOperation();
                            double[][] A = getMatrixA();
                            double scalar = getScalarValue();
                            double[][] R = scalarDivisionOperation.performOperation(A, scalar, scalarDivisionOperation);
                            displayResultingMatrix(R);
                        } else {
                            JOptionPane.showMessageDialog(MatrixCalculatorGUI.this, "Operation not implemented yet.");
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(MatrixCalculatorGUI.this, "Invalid operation type.");
                }
            }
        });
        
        mainPanel.add(performOperationButton);

         // Create resulting matrix panel
         resultingMatrixPanel = new JPanel();
         resultingMatrixPanel.setBorder(BorderFactory.createTitledBorder("Resulting Matrix"));
         mainPanel.add(resultingMatrixPanel); // Add below performOperationButton
         resultingMatrixPanel.setVisible(false); // Initially invisible
 
        // Create Save prompt and button
         savePromptPanel = new JPanel(new FlowLayout());
         JButton yesButton = new JButton("Save");
         yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double[][] resultMatrix = getResultMatrix();
                if (resultMatrix != null) {
                    inputsAndOutputs.saveMatrixToFile(resultMatrix);
                    JOptionPane.showMessageDialog(MatrixCalculatorGUI.this, "Matrix saved!");
                } else {
                    JOptionPane.showMessageDialog(MatrixCalculatorGUI.this, "No result matrix to save.");
                }
            }
        });

        savePromptPanel.add(new JLabel("Save Result to file?"));
        savePromptPanel.add(yesButton);
        mainPanel.add(savePromptPanel); 
        savePromptPanel.setVisible(false);
        add(mainPanel);
        setVisible(true);

        //create quit button for quitting the program, and call the display operation method
        quitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel quitLabel = new JLabel("Quit the program");
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySessionOperations();
                System.exit(0);
            }
        });
        quitPanel.add(quitLabel);
        quitPanel.add(quitButton);
        mainPanel.add(quitPanel);
    }


    // MODIFIES: most of the GUI fields
    // EFFECTS: updates the visibility of certain buttons and text fields when
    // certain operations are selected
    private void updateMatrixDimensionsVisibility() {
        String selectedOperationType = (String) operationTypeComboBox.getSelectedItem();
        String selectedOperation = (String) operationComboBox.getSelectedItem();
    
        if (selectedOperationType != null && selectedOperation != null) {
            switch (selectedOperationType) {
                case "Single Matrix Operations":
                    // Show matrix A dimensions, hide matrix B dimensions
                    rowsLabel1.setVisible(true);
                    rowsTextField1.setVisible(true);
                    columnsLabel1.setVisible(true);
                    columnsTextField1.setVisible(true);
                    rowsLabel2.setVisible(false);
                    rowsTextField2.setVisible(false);
                    columnsLabel2.setVisible(false);
                    columnsTextField2.setVisible(false);
                    matrixBPanel.setVisible(false); // Hide matrix B panel
                    scalarLabel.setVisible(false); // Hide scalar label
                    scalarTextField.setVisible(false); // Hide scalar text field
                    if (selectedOperation.equals("Power")) {
                        exponentLabel.setVisible(true);
                        exponentTextField.setVisible(true);
                    } else {
                        exponentLabel.setVisible(false);
                        exponentTextField.setVisible(false);
                    }
                    LoadBButton.setVisible(false);
                    loadBtext.setVisible(false);
                    break;
                case "Multi Matrix Operations":
                case "Element by Element Operations":
                    // Show both matrix A and B dimensions
                    rowsLabel1.setVisible(true);
                    rowsTextField1.setVisible(true);
                    columnsLabel1.setVisible(true);
                    columnsTextField1.setVisible(true);
                    rowsLabel2.setVisible(true);
                    rowsTextField2.setVisible(true);
                    columnsLabel2.setVisible(true);
                    columnsTextField2.setVisible(true);
                    matrixBPanel.setVisible(true); // Show matrix B panel
                    scalarLabel.setVisible(false); // Hide scalar label
                    scalarTextField.setVisible(false); // Hide scalar text field
                    exponentLabel.setVisible(false);
                    exponentTextField.setVisible(false);
                    LoadBButton.setVisible(true);
                    loadBtext.setVisible(true);
                    break;
                case "Scalar Operations":
                    // Show only matrix A dimensions, hide matrix B dimensions
                    rowsLabel1.setVisible(true);
                    rowsTextField1.setVisible(true);
                    columnsLabel1.setVisible(true);
                    columnsTextField1.setVisible(true);
                    rowsLabel2.setVisible(false);
                    rowsTextField2.setVisible(false);
                    columnsLabel2.setVisible(false);
                    columnsTextField2.setVisible(false);
                    matrixBPanel.setVisible(false); // Hide matrix B panel
                    scalarLabel.setVisible(true); // Show scalar label
                    scalarTextField.setVisible(true); // Show scalar text field
                    exponentLabel.setVisible(false);
                    exponentTextField.setVisible(false);
                    LoadBButton.setVisible(false); // Hide Load B Button
                    loadBtext.setVisible(false); // Hide loadBtext
                    System.out.println("updated");
                    break;
            }
        }
    }
    
    
    // REQUIRES: The operationTypeComboBox must not be null.
    // MODIFIES: operationComboBox
    // EFFECTS: changes the model of the second combo box to show the correct operations for the
    // selected operation type in the first combo box
    private void updateOperationComboBox() {
        String selectedOperationType = (String) operationTypeComboBox.getSelectedItem();
        if (selectedOperationType != null) {
            switch (selectedOperationType) {
                case "Single Matrix Operations":
                    operationComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Inverse", "Determinant", "Transpose", "Power"}));
                    break;
                case "Multi Matrix Operations":
                    operationComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Addition", "Subtraction", "Multiplication", "Division"}));
                    break;
                case "Element by Element Operations":
                    operationComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Multiplication", "Division"}));
                    break;
                case "Scalar Operations":
                    operationComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Scalar Multiplication", "Scalar Division"}));
                    break;
            }
        }
    }

    //REQUIRES: rowsTextField1 and columnsTextField1 
    //must contain valid integers representing the dimensions of Matrix A.
    //rowsTextField2 and columnsTextField2 must contain valid integers representing the dimensions of Matrix B
    //if "Multi Matrix Operations" or "Element by Element Operations" are selected. 
    //MODIFIES: matrixAPanel, matrixBPanel, scalarTextField
    //EFFECTS:  Clears the matrix panels and creates text fields for Matrix A and, 
    // if applicable, Matrix B based on the specified dimensions.
    private void createMatrixPanels() {
        int rowsA = Integer.parseInt(rowsTextField1.getText());
        int columnsA = Integer.parseInt(columnsTextField1.getText());

        matrixAPanel.removeAll();
        matrixAPanel.setLayout(new GridLayout(rowsA, columnsA));
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsA; j++) {
                matrixAPanel.add(new JTextField(5));
            }
        }
        matrixAPanel.revalidate();
        matrixAPanel.repaint();

        String selectedOperationType = (String) operationTypeComboBox.getSelectedItem();
        if (selectedOperationType.equals("Multi Matrix Operations") || selectedOperationType.equals("Element by Element Operations")) {
            int rowsB = Integer.parseInt(rowsTextField2.getText());
            int columnsB = Integer.parseInt(columnsTextField2.getText());

            matrixBPanel.removeAll();
            matrixBPanel.setLayout(new GridLayout(rowsB, columnsB));
            for (int i = 0; i < rowsB; i++) {
                for (int j = 0; j < columnsB; j++) {
                    matrixBPanel.add(new JTextField(5));
                }
            }
            matrixBPanel.revalidate();
            matrixBPanel.repaint();
        } else {
            matrixBPanel.removeAll();
            matrixBPanel.revalidate();
            matrixBPanel.repaint();
        }

        matrixAPanel.setVisible(true);
        matrixBPanel.setVisible(selectedOperationType.equals("Multi Matrix Operations") || selectedOperationType.equals("Element by Element Operations"));

        scalarTextField.setVisible(selectedOperationType.equals("Scalar Operations"));
    }

    // REQUIRES: rowsTextField1 and columnsTextField1 must not be null and 
    // must contain valid integer values representing the dimensions of matrix A.
    // matrixAPanel must not be null and must contain JTextField components representing the elements of matrix A.
    // The number of feilds in matrixAPanel must match the product of the dimensions of matrix A.
    // The text in each JTextField in matrixAPanel must be a double.
    // EFFECTS:  Returns a 2D double array representing matrix A filled with the values in matrixAPanel.
    public double[][] getMatrixA() {
        int rowsA = Integer.parseInt(rowsTextField1.getText());
        int columnsA = Integer.parseInt(columnsTextField1.getText());
        double[][] A = new double[rowsA][columnsA];

        Component[] components = matrixAPanel.getComponents();
        ArrayList<JTextField> textFieldList = new ArrayList<>();

        for (Component component : components) {
            if (component instanceof JTextField) {
                textFieldList.add((JTextField) component);
            }
        }

        int index = 0;
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsA; j++) {
                JTextField textField = textFieldList.get(index);
                String text = textField.getText();
                A[i][j] = Double.parseDouble(text);
                index++;
            }
        }
        return A;
    }

    // REQUIRES: rowsTextField2 and columnsTextField2 must not be null and 
    // must contain valid integer values representing the dimensions of matrix B.
    // matrixBPanel must not be null and must contain JTextField components representing the elements of matrix B.
    // The number of feilds in matrixBPanel must match the product of the dimensions of matrix B.
    // The text in each JTextField in matrixBPanel must be a double.
    // EFFECTS:  Returns a 2D double array representing matrix B filled with the values in matrixBPanel.
    public double[][] getMatrixB() {
        int rowsB = Integer.parseInt(rowsTextField2.getText());
        int columnsB = Integer.parseInt(columnsTextField2.getText());
        double[][] B = new double[rowsB][columnsB];

        Component[] components = matrixBPanel.getComponents();
        ArrayList<JTextField> textFieldList = new ArrayList<>();

        for (Component component : components) {
            if (component instanceof JTextField) {
                textFieldList.add((JTextField) component);
            }
        }

        int index = 0;
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < columnsB; j++) {
                JTextField textField = textFieldList.get(index);
                String text = textField.getText();
                B[i][j] = Double.parseDouble(text);
                index++;
            }
        }
        return B;
    }

    // REQUIRES: R must not be null
    // MODIFIES: resultingMatrixPanel, savePromptPanel
    // EFFECTS:  Clears the resultingMatrixPanel and fills it with JTextFields 
    // representing the elements of matrix R.
    // Sets the visibility of resultingMatrixPanel and savePromptPanel to true.
    // Refreshes the GUI to reflect the changes.
    public void displayResultingMatrix(double[][] R) {
        //initialise
        int rowsR = R.length;
        int columnsR = R[0].length;
        resultingMatrixPanel.removeAll();
        resultingMatrixPanel.setLayout(new GridLayout(rowsR, columnsR));

        //for loops to display the matri in the GUI panel
        for (int i = 0; i < rowsR; i++) {
            for (int j = 0; j < columnsR; j++) {
                JTextField textField = new JTextField(String.valueOf(R[i][j]));
                textField.setEditable(false); // Make the text field read-only
                resultingMatrixPanel.add(textField);
            }
        }
        // Make panels visible
        resultingMatrixPanel.setVisible(true);
        savePromptPanel.setVisible(true);

        // Refresh GUI
        revalidate();
        repaint();
    }

    //EFFECTS: takes the text in scalarTextField into a double value and returns that value...
    // If the text cannot be put into a double, return 0.0.
    private double getScalarValue() {
        String scalarText = scalarTextField.getText();
        try {
            return Double.parseDouble(scalarText);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    //EFFECTS: takes the text in exponentTextField into a int value and returns that value...
    // If the text cannot be put into a double, return -1 so the exponent exception will be triggered.
    public int getExponent() {
        String exponentText = exponentTextField.getText();
        try {
            int exponent = Integer.parseInt(exponentText);
            return exponent;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // REQUIRES: The input matrix must not be null.
    // MODIFIES: This method modifies the matrixAPanel by filling it 
    // with JTextFields representing the values of the input matrix.
    // EFFECTS: Fills the matrixAPanel with JTextFields representing the values 
    // of the input matrix and updating its layout.
    private void fillMatrixAPanel(double[][] matrix) {
        int rowsA = matrix.length;
        int columnsA = matrix[0].length;
    
        matrixAPanel.removeAll();
        matrixAPanel.setLayout(new GridLayout(rowsA, columnsA));
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsA; j++) {
                JTextField textField = new JTextField(String.valueOf(matrix[i][j]));
                matrixAPanel.add(textField);
            }
        }
        matrixAPanel.revalidate();
        matrixAPanel.repaint();
    }

    // REQUIRES: The input matrix must not be null.
    // MODIFIES: This method modifies the matrixBPanel by filling it 
    // with JTextFields representing the values of the input matrix.
    // EFFECTS: Fills the matrixBPanel with JTextFields representing the values 
    // of the input matrix and updating its layout.
    private void fillMatrixBPanel(double[][] matrix) {
        int rowsB = matrix.length;
        int columnsB = matrix[0].length;
    
        matrixBPanel.removeAll();
        matrixBPanel.setLayout(new GridLayout(rowsB, columnsB));
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < columnsB; j++) {
                JTextField textField = new JTextField(String.valueOf(matrix[i][j]));
                matrixBPanel.add(textField);
            }
        }
        matrixBPanel.revalidate();
        matrixBPanel.repaint();
    }

    // REQUIRES: The resultingMatrixPanel must not be null. The columnsTextField1 
    // must contain a valid integer representing the number of columns in the matrix.
    // EFFECTS: Retrieves the matrix values from the resultingMatrixPanel and constructs a 
    // 2D double array representing the result matrix. Returns null if any parsing fails.
    public double[][] getResultMatrix() {
        Component[] components = resultingMatrixPanel.getComponents();
        ArrayList<JTextField> textFieldList = new ArrayList<>();
    
        // Collecting the text fields containing matrix values
        for (Component component : components) {
            if (component instanceof JTextField) {
                textFieldList.add((JTextField) component);
            }
        }
    
        // Determining the dimensions of the matrix
        int rows = textFieldList.size() / Integer.parseInt(columnsTextField1.getText());
        int columns = Integer.parseInt(columnsTextField1.getText());
    
        double[][] resultMatrix = new double[rows][columns];
    
        // Extracting values from text fields
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                JTextField textField = textFieldList.get(index);
                String text = textField.getText();
                try {
                    resultMatrix[i][j] = Double.parseDouble(text);
                } catch (NumberFormatException e) { // Handle non-numeric input
                    return null; // Return null if parsing fails
                }
                index++;
            }
        }
        return resultMatrix;
    }
    
    // EFFECTS: Constructs a message containing the operations performed during the session...
    // Displays the constructed message in a popup dialog box.
    private void displaySessionOperations() {
        StringBuilder message = new StringBuilder("Operations performed during this session:\n\n");

        // Append all operations
        message.append("Multi-Matrix Operations:\n");
        appendOperationCounts(OperationTracker.getMultiOperationCounts(), message);
        message.append("\nSingle-Matrix Operations:\n");
        appendOperationCounts(OperationTracker.getSingleOperationCounts(), message);
        message.append("\nScalar-Matrix Operations:\n");
        appendOperationCounts(OperationTracker.getScalarOperationCounts(), message);

        // Display message in a popup dialog
        JOptionPane.showMessageDialog(this, message.toString(), "Session Operations", JOptionPane.INFORMATION_MESSAGE);
    }

    // EFFECTS: Appends the operation counts to the provided StringBuilder message. 
    // If the operationCounts map is empty, it appends "No operations performed." 
    // Otherwise, it iterates over the map entries, appending each operation along with its count.
    private void appendOperationCounts(Map<String, Integer> operationCounts, StringBuilder message) {
        if (operationCounts.isEmpty()) {
            message.append("No operations performed.");
        } else {
            for (String operation : operationCounts.keySet()) {
                int count = operationCounts.get(operation);
                message.append(operation).append(": ").append(count).append("\n");
            }
        }
    }
} 