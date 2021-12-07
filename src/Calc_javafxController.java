package unisa.group1.test_scalc;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;

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
    /** Variable input area. */
    @FXML
    private TextField var_text;
    /** Variables display area. */
    @FXML
    private TextArea vars_display;
    /** Function input area. */
    @FXML
    private TextField run_func_t;
    /** Function definition area. */
    @FXML
    private TextField write_func_t;
    /** Functions display area. */
    @FXML
    private TextArea func_display;
    /** Function delete button. */
    @FXML
    private Button del_button;
    /** Functions saving button. */
    @FXML
    private Button save_func_button;
    /** Functions loading button. */
    @FXML
    private Button import_button;
    /** Variables saving button. */
    @FXML
    private Button save_var_button;
    /** Variables loading button. */
    @FXML
    private Button restore_button;
    /** Cosine button. */
    @FXML
    private Button cos_but;
    /** Sine button. */
    @FXML
    private Button sin_but;
    /** Tangent button. */
    @FXML
    private Button tan_but;
    /** Logarithm button. */
    @FXML
    private Button log_but;
    /** Exponential button. */
    @FXML
    private Button exp_but;
    /** Arc cosine button. */
    @FXML
    private Button acos_but;
    /** Arc sine button. */
    @FXML
    private Button asin_but;
    /** Arc tangent button. */
    @FXML
    private Button atan_but;
    /** Argument button. */
    @FXML
    private Button arg_but;
    /** Stack property. */
    SimpleStringProperty sp;
    /** Variables property. */
    SimpleStringProperty vp;
    /** Functions property. */
    SimpleStringProperty fp;
    /** Context menu. */
    @FXML
    private ContextMenu menu;
    /** Context menu entry. */
    @FXML
    private MenuItem plotitem;
    /** Context menu entry. */
    @FXML
    private MenuItem plotitem3D;
    /** Chronology combobox. */
    @FXML
    private ComboBox<String> combobox;
    /** Chronology list. */
    @FXML
    private ObservableList<String> prevs;
    /** Conjugate button. */
    @FXML
    private Button conj_but;
    /** Module button. */
    @FXML
    private Button mod_but;
    

    /**
     * Initializes the controller.
     * 
     * @param url   the url.
     * @param rb    the resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prevs = FXCollections.observableArrayList();
        combobox.setItems(prevs);
        combobox.setPromptText("Chronology");
        calc = new Calculator();
        stack_text.setEditable(false);
        vars_display.setEditable(false);
        func_display.setEditable(false);
        vars_display.setDisable(true);
        vars_display.setOpacity(255);
        func_display.setDisable(true);
        func_display.setOpacity(255);
        sp = calc.stackProperty();
        vp = calc.varsProperty();
        fp = calc.funcsProperty();
        sp.addListener((obs, oldTextValue, newTextValue) -> {
            stack_text.setText("- " + oldTextValue + "\n");
            stack_text.appendText(newTextValue);
            prevs.add(newTextValue);
        });
        vp.addListener((obs, oldTextValue, newTextValue) -> vars_display.setText(newTextValue));
        fp.addListener((obs, oldTextValue, newTextValue) -> func_display.setText(newTextValue));
        combobox.getSelectionModel().selectedItemProperty().addListener((o, ol, nw) -> {
            String[] s = nw.split(",");
            calc.clear_parse_seq(s);
        });
        menu = new ContextMenu();
        plotitem = new MenuItem("Scatter Plot");
        plotitem3D = new MenuItem("3D Plot");
        plotitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                menu.hide();
                Plotter p = new Plotter(calc.getStack().stack_collect());
                p.plot();
            }    
        });
        plotitem3D.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                menu.hide();
                Plotter p = new Plotter(calc.getStack().stack_collect());
                p.setup3D(calc.getStack().stack_collect());
            }    
        });
        menu.getItems().add(plotitem);
        menu.getItems().add(plotitem3D);
        stack_text.setContextMenu(menu);
        Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread th, Throwable ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Exception Dialog");
                alert.setHeaderText("An Exception has just accurred.");
                alert.setContentText("The operation you just performed has caused errors.");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                String exceptionText = sw.toString();
                Label label = new Label("The exception stacktrace was:");
                TextArea textArea = new TextArea(exceptionText);
                textArea.setEditable(false);
                textArea.setWrapText(true);
                textArea.setMaxWidth(Double.MAX_VALUE);
                textArea.setMaxHeight(Double.MAX_VALUE);
                GridPane.setVgrow(textArea, Priority.ALWAYS);
                GridPane.setHgrow(textArea, Priority.ALWAYS);
                GridPane expContent = new GridPane();
                expContent.setMaxWidth(Double.MAX_VALUE);
                expContent.add(label, 0, 0);
                expContent.add(textArea, 0, 1);
                alert.getDialogPane().setExpandableContent(expContent);
                alert.getDialogPane().setStyle("-fx-font-family: 'serif'");
                alert.showAndWait();
            }
        };
        Thread.setDefaultUncaughtExceptionHandler(h);
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
    }

    /**
     * Add function.
     * 
     * @param event the event.
     */
    @FXML
    private void add_button(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY || event.isControlDown())
            calc.all_sum();
        else
            calc.sum();
    }
    
    /**
     * Subtract function.
     * 
     * @param event the event.
     */
    @FXML
    private void subtract_button(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY || event.isControlDown())
            calc.all_subtract();
        else
            calc.subtract();
    }

    /**
     * Multiply function.
     * 
     * @param event the event.
     */
    @FXML
    private void multiply_button(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY || event.isControlDown())
            calc.all_multiply();
        else
            calc.multiply();
    }

    /**
     * Divide function.
     * 
     * @param event the event.
     */
    @FXML
    private void divide_button(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY || event.isControlDown())
            calc.all_divide();
        else
            calc.divide();
    }

    /**
     * Power function.
     * 
     * @param event the event.
     */
    @FXML
    private void pow_button(ActionEvent event) {
        calc.power();
    }

    /**
     * Square root function.
     * 
     * @param event the event.
     */
    @FXML
    private void sqrt_button(ActionEvent event) {
        calc.sqrt();
    }

    /**
     * Negate signs function.
     * 
     * @param event the event.
     */
    @FXML
    private void invert_button(ActionEvent event) {
        calc.negate();
    }

    /**
     * Clear function.
     * 
     * @param event the event.
     */
    @FXML
    private void clear_button(ActionEvent event) {
        calc.clear();
    }

    /**
     * Drop function.
     * 
     * @param event the event.
     */
    @FXML
    private void drop_button(ActionEvent event) {
        calc.drop();
    }

    /**
     * Swap function.
     * 
     * @param event the event.
     */
    @FXML
    private void swap_button(ActionEvent event) {
        calc.swap();
    }

    /**
     * Duplicate function.
     * 
     * @param event the event.
     */
    @FXML
    private void dup_button(ActionEvent event) {
        calc.dup();
    }

    /**
     * Over function.
     * 
     * @param event the event.
     */
    @FXML
    private void over_button(ActionEvent event) {
        calc.over();
    }

    /**
     * Parses the variable operation in the area.
     * 
     * @param event the Enter key press event.
     */
    @FXML
    private void insert_var(ActionEvent event) {
        String s = var_text.getText();
        calc.parse_var(s);
    }
    
    /**
     * Executes the function specified.
     * 
     * @param event the event.
     * @throws IOException  if there is an IO Error.
     * @throws ClassNotFoundException   if the cast Class is not in the Scope.
     * @throws ParseException   if the function is parsed incorrectly.
     */
    @FXML
    private void run_func(ActionEvent event) throws ClassNotFoundException, ParseException, IOException {
        calc.parse_func(calc.get_func(run_func_t.getText()));
    }
    
    /**
     * Defines and stores the function specified.
     * 
     * @param event the event.
     */
    @FXML
    private void write_func(ActionEvent event) {
        String[] args = write_func_t.getText().split(":");
        calc.add_func(args[0], args[1]);
    }
    
    /**
     * Deletes the function specified.
     * 
     * @param event the event.
     */
    @FXML
    private void delete(ActionEvent event) {
        calc.rem_func(run_func_t.getText());
    }
    
    /**
     * Saves all the functions locally.
     * 
     * @param event the event.
     * @throws IOException  if there is an IO Error.
     */
    @FXML
    private void save_func(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.SECONDARY || event.isControlDown()){
            FileChooser f_chooser = new FileChooser();
            File f = f_chooser.showSaveDialog(null);
            calc.save_func(f);
        }
        else
            calc.save_func();
    }
    
    /**
     * Loads the functions from local file.
     * 
     * @param event the event.
     * @throws IOException  if there is an IO Error.
     * @throws ClassNotFoundException   if the cast Class is not in the Scope.
     */
    @FXML
    private void import_func(MouseEvent event) throws IOException, ClassNotFoundException {
        if (event.getButton() == MouseButton.SECONDARY || event.isControlDown()){
            FileChooser f_chooser = new FileChooser();
            File f = f_chooser.showOpenDialog(null);
            calc.load_func(f);
        }
        else
            calc.load_func();
    }
    
    /**
     * Saves all the variables locally.
     * 
     * @param event the event.
     * @throws IOException  if there is an IO Error..
     */
    @FXML
    private void save_var(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.SECONDARY || event.isControlDown()){
            FileChooser f_chooser = new FileChooser();
            File f = f_chooser.showSaveDialog(null);
            calc.save_var(f);
        }
        else
            calc.save_var();
    }
    
    /**
     * Loads the variables from local file.
     * 
     * @param event the event.
     * @throws IOException  if there is an IO Error.
     * @throws ClassNotFoundException   if the cast Class is not in the Scope.
     */
    @FXML
    private void restore(MouseEvent event) throws IOException, ClassNotFoundException {
        if (event.getButton() == MouseButton.SECONDARY || event.isControlDown()){
            FileChooser f_chooser = new FileChooser();
            File f = f_chooser.showOpenDialog(null);
            calc.load_var(f);
        }
        else
            calc.load_var();
    }
    
    /**
     * Cosine function.
     * 
     * @param event the event.
     */
    @FXML
    private void do_cos(ActionEvent event) {
        calc.cosine();
    }
    
    /**
     * Sine function.
     * 
     * @param event the event.
     */
    @FXML
    private void do_sin(ActionEvent event) {
        calc.sine();
    }
    
    /**
     * Tangent function.
     * 
     * @param event the event.
     */
    @FXML
    private void do_tan(ActionEvent event) {
        calc.tangent();
    }
    
    /**
     * Arc cosine function.
     * 
     * @param event the event.
     */
    @FXML
    private void do_acos(ActionEvent event) {
        calc.arc_cosine();
    }
    
    /**
     * Arc sine function.
     * 
     * @param event the event.
     */
    @FXML
    private void do_asin(ActionEvent event) {
        calc.arc_sine();
    }
    
    /**
     * Arc tangent function.
     * 
     * @param event the event.
     */
    @FXML
    private void do_atan(ActionEvent event) {
        calc.arc_tangent();
    }
    
    /**
     * Logarithm function.
     * 
     * @param event the event.
     */
    @FXML
    private void do_log(ActionEvent event) {
        calc.logarithm();
    }
    
    /**
     * Exponential function.
     * 
     * @param event the event.
     */
    @FXML
    private void do_exp(ActionEvent event) {
        calc.exponential();
    }
    
    /**
     * Argument function.
     * 
     * @param event the event.
     */
    @FXML
    private void do_arg(ActionEvent event) {
        calc.argument();
    }
    
    /**
     * Conjugate function.
     * 
     * @param event the event.
     */
    @FXML
    private void do_conjugate(ActionEvent event) {
        calc.conjugate();
    }
    
    /**
     * Module function.
     * 
     * @param event the event.
     */
    @FXML
    private void do_mod(ActionEvent event) {
        calc.module();
    }
    
}
