package unisa.group1.test_scalc;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class.
 *
 * @author Group 1
 * 
 */
public class Calc_javafxController implements Initializable {
    
    /** Stack display area. */
    @FXML
    private TextArea stack_text;
    /** Number input area. */
    @FXML
    private TextField insert_text;
    /** Sum button. */
    @FXML
    private Button add_id;
    /** Subtract button. */
    @FXML
    private Button subtract_id;
    /** Multiply button. */
    @FXML
    private Button multiply_id;
    /** Divide button. */
    @FXML
    private Button divide_id;
    /** Power button. */
    @FXML
    private Button pow_id;
    /** Square root button. */
    @FXML
    private Button sqrt_id;
    /** Sign flip button. */
    @FXML
    private Button invert_id;
    /** Clear button. */
    @FXML
    private Button clear_id;
    /** Drop button. */
    @FXML
    private Button drop_id;
    /** Swap button. */
    @FXML
    private Button swap_id;
    /** Duplicate button. */
    @FXML
    private Button dup_id;
    /** Over button. */
    @FXML
    private Button over_id;
    /** Internal instance of calculator. */
    private Calculator calc;
    

    /**
     * Initializes the controller.
     * 
     * @param url   the url.
     * @param rb    the resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        calc = new Calculator();
        stack_text.setEditable(false);
    }

    /**
     * Parses the number in the area and creates a complex number.
     * 
     * @param event the Enter key press event.
     * @throws ParseException   when the pattern is not recognized.
     */
    @FXML
    private void insert_operand(ActionEvent event) throws ParseException {
        String s = insert_text.getText();
        calc.parse_num(s);
        stack_text.setText(calc.getStack().toString());
    }

    /**
     * Add function.
     * 
     * @param event the event.
     */
    @FXML
    private void add_button(ActionEvent event) {
        calc.sum();
        stack_text.setText(calc.getStack().toString());
    }
    
    /**
     * Subtract function.
     * 
     * @param event the event.
     */
    @FXML
    private void subtract_button(ActionEvent event) {
        calc.subtract();
        stack_text.setText(calc.getStack().toString());
    }

    /**
     * Multiply function.
     * 
     * @param event the event.
     */
    @FXML
    private void multiply_button(ActionEvent event) {
        calc.multiply();
        stack_text.setText(calc.getStack().toString());
    }

    /**
     * Divide function.
     * 
     * @param event the event.
     */
    @FXML
    private void divide_button(ActionEvent event) {
        calc.divide();
        stack_text.setText(calc.getStack().toString());
    }

    /**
     * Power function.
     * 
     * @param event the event.
     */
    @FXML
    private void pow_button(ActionEvent event) {
        calc.power();
        stack_text.setText(calc.getStack().toString());
    }

    /**
     * Square root function.
     * 
     * @param event the event.
     */
    @FXML
    private void sqrt_button(ActionEvent event) {
        calc.sqrt();
        stack_text.setText(calc.getStack().toString());
    }

    /**
     * Negate signs function.
     * 
     * @param event the event.
     */
    @FXML
    private void invert_button(ActionEvent event) {
        calc.negate();
        stack_text.setText(calc.getStack().toString());
    }

    /**
     * Clear function.
     * 
     * @param event the event.
     */
    @FXML
    private void clear_button(ActionEvent event) {
        calc.clear();
        stack_text.setText(calc.getStack().toString());
    }

    /**
     * Drop function.
     * 
     * @param event the event.
     */
    @FXML
    private void drop_button(ActionEvent event) {
        calc.drop();
        stack_text.setText(calc.getStack().toString());
    }

    /**
     * Swap function.
     * 
     * @param event the event.
     */
    @FXML
    private void swap_button(ActionEvent event) {
        calc.swap();
        stack_text.setText(calc.getStack().toString());
    }

    /**
     * Duplicate function.
     * 
     * @param event the event.
     */
    @FXML
    private void dup_button(ActionEvent event) {
        calc.dup();
        stack_text.setText(calc.getStack().toString());
    }

    /**
     * Over function.
     * 
     * @param event the event.
     */
    @FXML
    private void over_button(ActionEvent event) {
        calc.over();
        stack_text.setText(calc.getStack().toString());
    }
    
}
