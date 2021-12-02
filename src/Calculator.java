package unisa.group1.test_scalc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.complex.ComplexFormat;

/**
 * Basic implementation of a stack based
 * scientific programmable calculator.
 * 
 * @author Group 1
 *
 */
public class Calculator {
    
    
    /**
     * Auxiliary internal class for complex numbers.
     * 
     */
    public class OPT_Complex extends Complex implements Serializable {
        
        
        /** The output format. */
        ComplexFormat format;
        /** The numbering format. */
        NumberFormat nf = NumberFormat.getInstance();
        
        
        /**
         * Overridden constructor.
         * 
         * @param real  the ral part.
         * @param imaginary the imaginary part.
         */
        public OPT_Complex(double real, double imaginary) {
            super(real, imaginary);
        }
        
        /**
         * Overridden constructor.
         * 
         * @param c the basic complex number.
         */
        public OPT_Complex(org.apache.commons.math.complex.Complex c) {
            super(c.getReal(), c.getImaginary());
        }

        /**
         * Overridden sqrt.
         * 
         * @return  the square root.
         */
        @Override
        public OPT_Complex sqrt() {
            return new OPT_Complex(super.sqrt());
        }

        /**
         * Overridden power.
         * 
         * @param x the exponent.
         * @return  the power.
         */
        @Override
        public OPT_Complex pow(Complex x) {
            return new OPT_Complex(super.pow(x));
        }

        /**
         * Overridden subtract.
         * 
         * @param rhs   the subtrahend.
         * @return  the difference.
         */
        @Override
        public OPT_Complex subtract(Complex rhs) {
            return new OPT_Complex(super.subtract(rhs));
        }

        /**
         * Overridden negate.
         * 
         * @return  the sign swapped number.
         */
        @Override
        public OPT_Complex negate() {
            return new OPT_Complex(super.negate());
        }

        /**
         * Overridden multiply.
         * 
         * @param rhs   the factor.
         * @return  the product.
         */
        @Override
        public OPT_Complex multiply(Complex rhs) {
            return new OPT_Complex(super.multiply(rhs));
        }

        /**
         * Overridden divide.
         * 
         * @param rhs   the divisor.
         * @return  the quotient.
         */
        @Override
        public OPT_Complex divide(Complex rhs) {
            return new OPT_Complex(super.divide(rhs));
        }

        /**
         * Overridden add.
         * 
         * @param rhs   the addend.
         * @return  the sum.
         */
        @Override
        public OPT_Complex add(Complex rhs) {
            return new OPT_Complex(super.add(rhs));
        }
        
        /**
         * Overridden toString with ComplexFormat support.
         * 
         * @return  the formatted string.
         */
        @Override
        public String toString() {
            nf.setMaximumFractionDigits(3);
            format = new ComplexFormat(nf);
            return "(" + format.format(this).replace(".", "").replace(",", ".") + ")";
        }
        
        /**
         * Refactored arg.
         * 
         * @return  the phase.
         */
        public OPT_Complex arg() {
            return new OPT_Complex(super.getArgument(), 0.0);
        }

        /**
         * Overridden tan.
         * 
         * @return  the tangent.
         */
        @Override
        public OPT_Complex tan() {
            return new OPT_Complex(super.tan());
        }

        /**
         * Overridden sin.
         * 
         * @return  the sine.
         */
        @Override
        public OPT_Complex sin() {
            return new OPT_Complex(super.sin());
        }

        /**
         * Overridden log.
         * 
         * @return  the logarithm.
         */
        @Override
        public OPT_Complex log() {
            return new OPT_Complex(super.log());
        }

        /**
         * Overridden exp.
         * 
         * @return  the exponential.
         */
        @Override
        public OPT_Complex exp() {
            return new OPT_Complex(super.exp());
        }

        /**
         * Overridden cos.
         * 
         * @return  the cosine.
         */
        @Override
        public OPT_Complex cos() {
            return new OPT_Complex(super.cos());
        }

        /**
         * Overridden atan.
         * 
         * @return  the arc tangent.
         */
        @Override
        public OPT_Complex atan() {
            return new OPT_Complex(super.atan());
        }

        /**
         * Overridden asin.
         * 
         * @return  the arc sine.
         */
        @Override
        public OPT_Complex asin() {
            return new OPT_Complex(super.asin());
        }

        /**
         * Overridden acos.
         * 
         * @return  the arc cosine.
         */
        @Override
        public OPT_Complex acos() {
            return new OPT_Complex(super.acos());
        }

        /**
         * Overridden conjugate.
         * 
         * @return  the conjugate.
         */
        @Override
        public OPT_Complex conjugate() {
            return new OPT_Complex(super.conjugate());
        }

        /**
         * Refactored mod.
         * 
         * @return  the module.
         */
        public OPT_Complex mod() {
            return new OPT_Complex(super.abs(), 0.0);
        }
        
    }
	
	
	/** The stack in which values are stored. */
	private Advanced_Stack<OPT_Complex> stack;
        /** The map containing the variables. */
	private TreeMap<String, OPT_Complex> vars;
        /** The map cotaining custom functions. */
        private TreeMap<String, String> funcs;
        /** Stack string property. */
        private SimpleStringProperty stack_p;
        /** Variables string property. */
        private SimpleStringProperty vars_p;
        /** Functions string property. */
        private SimpleStringProperty funcs_p;

	
	/**
	 * Create an instance of Calculator
	 * setting an empty stack.
	 * 
	 */
	public Calculator() {
            super();
            this.stack = new Advanced_Stack<>();
            this.vars = new TreeMap<>();
            this.funcs = new TreeMap<>();
            stack_p = new SimpleStringProperty("");
            vars_p = new SimpleStringProperty("");
            funcs_p = new SimpleStringProperty("");
	}
	
	/**
	 * Inserts an operand in the stack.
	 * 
	 * @param c	the operand.
	 */
	public void push_num(OPT_Complex c) {
            stack.stack_push(c);
            this.updateStackProperty();
	}
	
	/**
	 * Sums the top 2 elements in the stack and updates it.
	 * 
	 */
	public void sum() {
            OPT_Complex t1 = stack.stack_drop();
            Complex t2 = (Complex) stack.stack_drop();
            stack.stack_push(t1.add(t2));
            this.updateStackProperty();
	}
        
        /**
	 * Sums all the elements in the stack and updates it.
	 * 
	 */
	public void all_sum() {
            ArrayList<OPT_Complex> list = stack.stack_collect();
            OPT_Complex tmp = list.get(0);
            for(int i = 1; i < list.size(); i++)
                tmp = tmp.add(list.get(i));
            stack.stack_clear();
            stack.stack_push(tmp);
            this.updateStackProperty();
	}
	
	/**
	 * Subtracts the top 2 elements in the stack and updates it.
	 * 
	 */
	public void subtract() {
            OPT_Complex t1 = stack.stack_drop();
            Complex t2 = (Complex) stack.stack_drop();
            stack.stack_push(t1.subtract(t2));
            this.updateStackProperty();
	}
        
        /**
	 * Subtracts all the elements in the stack and updates it.
	 * 
	 */
	public void all_subtract() {
            ArrayList<OPT_Complex> list = stack.stack_collect();
            OPT_Complex tmp = list.get(list.size() - 1);
            for(int i = list.size() - 2; i >= 0; i--)
                tmp = tmp.subtract(list.get(i));
            stack.stack_clear();
            stack.stack_push(tmp);
            this.updateStackProperty();
	}
	
	/**
	 * Multiplies the top 2 elements in the stack and updates it.
	 * 
	 */
	public void multiply() {
            OPT_Complex t1 = stack.stack_drop();
            Complex t2 = (Complex) stack.stack_drop();
            stack.stack_push(t1.multiply(t2));
            this.updateStackProperty();
	}
        
        /**
	 * Multiplies all the elements in the stack and updates it.
	 * 
	 */
	public void all_multiply() {
            ArrayList<OPT_Complex> list = stack.stack_collect();
            OPT_Complex tmp = list.get(0);
            for(int i = 1; i < list.size(); i++)
		tmp = tmp.multiply(list.get(i));
            stack.stack_clear();
            stack.stack_push(tmp);
            this.updateStackProperty();
	}
	
	/**
	 * Divides the top 2 elements in the stack and updates it.
	 * 
	 */
	public void divide() {
            OPT_Complex t1 = stack.stack_drop();
            Complex t2 = (Complex) stack.stack_drop();
            stack.stack_push(t1.divide(t2));
            this.updateStackProperty();
	}
        
        /**
	 * Divides all the elements in the stack and updates it.
	 * 
	 */
	public void all_divide() {
            ArrayList<OPT_Complex> list = stack.stack_collect();
            OPT_Complex tmp = list.get(list.size() - 1);
            for(int i = list.size() - 2; i >= 0; i--)
                tmp = tmp.divide(list.get(i));
            stack.stack_clear();
            stack.stack_push(tmp);
            this.updateStackProperty();
	}
	
	/**
	 * Elevates to the next power sequentially and
	 * updates the stack.
	 * 
	 */
	public void power() {
            OPT_Complex t1 = stack.stack_drop();
            Complex t2 = (Complex) stack.stack_drop();
            stack.stack_push(t1.pow(t2));
            this.updateStackProperty();
	}
	
	/**
	 * Calculates the square root for the top element in the stack.
	 * 
	 */
	public void sqrt() {
            OPT_Complex t1 = stack.stack_drop();
            stack.stack_push(t1.sqrt());
            this.updateStackProperty();
	}
	
	/**
	 * Negates the sign of the top element in the stack.
	 * 
	 */
	public void negate() {
            OPT_Complex t1 = stack.stack_drop();
            stack.stack_push(t1.negate());
            this.updateStackProperty();
	}
	
	/**
	 * Clears the stack.
	 * 
	 */
	public void clear() {
            stack.stack_clear();
            this.updateStackProperty();
	}
	
	/**
	 * Removes the top element of the stack.
	 * 
	 */
	public void drop() {
            stack.stack_drop();
            this.updateStackProperty();
	}
	
	/**
	 * Duplicates the top element of the stack.
	 * 
	 */
	public void dup() {
            stack.stack_dup();
            this.updateStackProperty();
	}
	
	/**
	 * Swaps the top two elements of the stack.
	 * 
	 */
	public void swap() {
            stack.stack_swap();
            this.updateStackProperty();
	}
	
	/**
	 * Duplicates the second top element of the stack
	 * and pushes it to the top of it.
	 * 
	 */
	public void over() {
            stack.stack_over();
            this.updateStackProperty();
	}
        
        /**
         * Retrieves the internal stack.
         * 
         * @return  the stack.
         */
        public Advanced_Stack<OPT_Complex> getStack(){
                return this.stack;
        }
        
        /**
         * Parses a number to the correct complex form.
         * 
         * @param s the number.
         */
        public void parse_num(String s) throws NumberFormatException {
            s = s.replace(" ", "").replace("\t", "");
            boolean firstPositive = true;
            boolean secondPositive = true;
            if (s.charAt(0) == '-')
                firstPositive = false;
            if (s.substring(1).contains("-"))
                secondPositive = false;
            String[] split = s.split("[+-]");
            int l = split.length;
            if (split[0].equals("")) {
                split[0] = split[1];
                if(split.length > 2)
                    split[1] = split[2];
                else
                    --l;
            }
            double realPart = 0;
            double imgPart = 0;
            boolean t = split[0].contains("i") || split[0].contains("j");
            if (t)
                imgPart = Double.parseDouble((firstPositive ? "+" : "-") + split[0].substring(0,split[0].length() - 1));
            else
                realPart = Double.parseDouble((firstPositive ? "+" : "-") + split[0]);
            if (l > 1) {
                t = split[1].contains("i") || split[1].contains("j");
                if (t)
                    imgPart = Double.parseDouble((secondPositive ? "+" : "-") + split[1].substring(0,split[1].length() - 1));
                else
                    realPart = Double.parseDouble((secondPositive ? "+" : "-") + split[1]);
            }
            this.push_num(new OPT_Complex(realPart,imgPart));
        }
        
        /**
         * Parses a sequence of strings, all inside parenthesis;
         * Stack property is update cumulatively at last.
         * 
         * @param i the array of strings;
         */
        void clear_parse_seq(String[] i) {
            stack.stack_clear();
            for (String s : i){
                s = s.replace("(", "").replace(")", "");
                s = s.replace(" ", "").replace("\t", "");
                boolean firstPositive = true;
                boolean secondPositive = true;
                if (s.charAt(0) == '-')
                    firstPositive = false;
                if (s.substring(1).contains("-"))
                    secondPositive = false;
                String[] split = s.split("[+-]");
                int l = split.length;
                if (split[0].equals("")) {
                    split[0] = split[1];
                    if(split.length > 2)
                        split[1] = split[2];
                    else
                        --l;
                }
                double realPart = 0;
                double imgPart = 0;
                boolean t = split[0].contains("i") || split[0].contains("j");
                if (t)
                    imgPart = Double.parseDouble((firstPositive ? "+" : "-") + split[0].substring(0,split[0].length() - 1));
                else
                    realPart = Double.parseDouble((firstPositive ? "+" : "-") + split[0]);
                if (l > 1) {
                    t = split[1].contains("i") || split[1].contains("j");
                    if (t)
                        imgPart = Double.parseDouble((secondPositive ? "+" : "-") + split[1].substring(0,split[1].length() - 1));
                    else
                        realPart = Double.parseDouble((secondPositive ? "+" : "-") + split[1]);
                }
                stack.stack_push(new OPT_Complex(realPart,imgPart));
            }
            this.updateStackProperty();
        }
        
        /**
	 * Adds the complex number at the stack's top 
	 * to the map at the specified key.
	 * 
	 * @param key	the variable.
	 */
	public void add_var(String key) {
            vars.put(key, stack.stack_drop());
            this.updateStackProperty();
            this.updateVarsProperty();
	}
	
	/**
	 * Adds the complex number stored in the variable
	 * to the stack.
	 * 
	 * @param key	the variable.
	 * @throws NullPointerException	when there is no entry corresponding to the key.
	 */
	public void put_var(String key) throws NullPointerException {
            if (!vars.containsKey(key))
                throw new NullPointerException();
            stack.stack_push(vars.get(key));
            this.updateStackProperty();
            this.updateVarsProperty();
	}
	
	/**
	 * Sums the number at the stack's top to the variable
	 * specified and updates the variable.
	 * 
	 * @param key	the variable.
	 * @throws NullPointerException	when there is no entry corresponding to the key.
	 */
	public void sum_var(String key) throws NullPointerException {
            if (!vars.containsKey(key))
                throw new NullPointerException();
            vars.put(key, (OPT_Complex) vars.get(key).add(stack.stack_drop()));
            this.updateStackProperty();
            this.updateVarsProperty();
	}
	
	/**
	 * Subtracts the number at the stack's top to the variable
	 * specified and updates the variable.
	 * 
	 * @param key	the variable.
	 * @throws NullPointerException	when there is no entry corresponding to the key.
	 */
	public void sub_var(String key) throws NullPointerException {
            if (!vars.containsKey(key))
                throw new NullPointerException();
            vars.put(key, (OPT_Complex) vars.get(key).subtract(stack.stack_drop()));
            this.updateStackProperty();
            this.updateVarsProperty();
	}
        
        /**
         * Retrieves the variables map.
         * 
         * @return  the variables map.
         */
        public TreeMap<String, OPT_Complex> getVars() {
            return this.vars;
        }
        
        /**
         * Parses operations between stack and variables.
         * 
         * @param s the variable command.
         * @return  if the variable has been correctly identified.
         */
        public boolean parse_var(String s) {
            if (s.length() == 2 && Character.isLowerCase(s.charAt(1))
                && (s.charAt(0) == '>' || s.charAt(0) == '<' || s.charAt(0) == '+' || s.charAt(0) == '-')) {
                String v = s.substring(1);
                if (s.charAt(0) == '>')
                    this.add_var(v);
                if (s.charAt(0) == '<')
                    this.put_var(v);
                if (s.charAt(0) == '+')
                    this.sum_var(v);
                if (s.charAt(0) == '-')
                    this.sub_var(v);
                return true;
            }
            else
                return false;
        }
        
        /**
         * Retrieves the map of functions.
         * 
         * @return  the map functions.
         */
        public TreeMap<String, String> getFuncs() {
            return funcs;
        }
        
        /**
         * Adds a function to the map.
         * 
         * @param key   the key.
         * @param val   the function.
         */
        public void add_func(String key, String val) {
            if (!val.contains(key))
                funcs.put(key, val);
            this.updateFuncsProperty();
        }
        
        /**
         * Retrieves a single function from the map.
         * 
         * @param key   the key.
         * @return  the function.
         * @throws NullPointerException if there is no entry corresponding to key.
         */
        public String get_func(String key) throws NullPointerException {
            if (!funcs.containsKey(key))
                throw new NullPointerException();
            return funcs.get(key);
        }
        
        /**
         * Removes a function from the map.
         * 
         * @param key   the key.
         * @throws NullPointerException if there is no entry corresponding to key.
         */
        public void rem_func(String key) throws NullPointerException {
            funcs.remove(key);
            this.updateFuncsProperty();
        }
        
        /**
         * Stores the functions map in a local file.
         * 
         * @throws IOException  if there is an IO Error.
         */
        public void save_func() throws IOException {
            try {
                ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("funcs.bin"));
                o.writeObject(this.funcs);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /**
         * Loads the functions map from a local file.
         * 
         * @throws IOException  if there is an IO Error.
         * @throws ClassNotFoundException   if the cast Class is not in the Scope.
         */
        public void load_func() throws IOException, ClassNotFoundException {
            try {
                ObjectInputStream i = new ObjectInputStream(new FileInputStream("funcs.bin"));
                this.funcs = (TreeMap<String, String>) i.readObject();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.updateFuncsProperty();
        }
        
        /**
         * Parses and executes functions recursively.
         * 
         * @param func  the function.
         * @throws IOException  if there is an IO Error.
         * @throws ClassNotFoundException   if the cast Class is not in the Scope.
         * @throws ParseException   if the function is parsed incorrectly.
         */
        public void parse_func(String func) throws IOException, ClassNotFoundException, ParseException {
            String[] f = func.split(" ");
            for (String s : f) {
                if (s.equals("save"))
                    this.save_var();
                else if (s.equals("restore"))
                    this.load_var();
                else if (s.equals("+"))
                    this.sum();
                else if (s.equals("-"))
                    this.subtract();
                else if (s.equals("*"))
                    this.multiply();
                else if (s.equals("/"))
                    this.divide();
                else if (s.equals("pow"))
                    this.power();
                else if (s.equals("sqrt"))
                    this.sqrt();
                else if (s.equals("+-"))
                    this.negate();
                else if (s.equals("clear"))
                    this.clear();
                else if (s.equals("drop"))
                    this.drop();
                else if (s.equals("dup"))
                    this.dup();
                else if (s.equals("swap"))
                    this.swap();
                else if (s.equals("over"))
                    this.over();
                else if (this.parse_var(s));
                else if (funcs.containsKey(s))
                    this.parse_func(funcs.get(s));
                else
                    this.parse_num(s);
            }
            this.updateStackProperty();
            this.updateVarsProperty();
        }
        
        /**
         * Stores the variables map in a local file.
         * 
         * @throws IOException  if there is an IO Error.
         */
        public void save_var() throws IOException {
            TreeMap<String, ArrayList<Double>> tmp = new TreeMap<>();
            try {
                ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("vars.bin"));
                for (Entry<String, OPT_Complex> e : vars.entrySet()){
                    ArrayList<Double> t = new ArrayList<>();
                    t.add(e.getValue().getReal());
                    t.add(e.getValue().getImaginary());
                    tmp.put(e.getKey(), t);
                }
                o.writeObject(tmp);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /**
         * Loads the variables map from a local file.
         * 
         * @throws IOException  if there is an IO Error.
         * @throws ClassNotFoundException   if the cast Class is not in the scope.
         */
        public void load_var() throws IOException, ClassNotFoundException {
            TreeMap<String, ArrayList<Double>> tmp = new TreeMap<>();
            try {
                ObjectInputStream i = new ObjectInputStream(new FileInputStream("vars.bin"));
                tmp = (TreeMap<String, ArrayList<Double>>) i.readObject();
                vars.clear();
                for (Entry<String, ArrayList<Double>> e : tmp.entrySet())
                    vars.put(e.getKey(), new OPT_Complex(e.getValue().get(0), e.getValue().get(1)));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.updateVarsProperty();
        }
        
        /**
	 * Calculates the cosine for the top element in the stack.
	 * 
	 */
        public void cosine() {
            OPT_Complex t1 = stack.stack_drop();
            stack.stack_push(t1.cos());
            this.updateStackProperty();
        }
        
        /**
	 * Calculates the sine for the top element in the stack.
	 * 
	 */
        public void sine() {
            OPT_Complex t1 = stack.stack_drop();
            stack.stack_push(t1.sin());
            this.updateStackProperty();
        }
        
        /**
	 * Calculates the tangent for the top element in the stack.
	 * 
	 */
        public void tangent() {
            OPT_Complex t1 = stack.stack_drop();
            stack.stack_push(t1.tan());;
            this.updateStackProperty();
        }
        
        /**
	 * Calculates the arc cosine for the top element in the stack.
	 * 
	 */
        public void arc_cosine() {
            OPT_Complex t1 = stack.stack_drop();
            stack.stack_push(t1.acos());
            this.updateStackProperty();
        }
        
        /**
	 * Calculates the arc sine for the top element in the stack.
	 * 
	 */
        public void arc_sine() {
            OPT_Complex t1 = stack.stack_drop();
            stack.stack_push(t1.asin());
            this.updateStackProperty();
        }
        
        /**
	 * Calculates the arc tangent for the top element in the stack.
	 * 
	 */
        public void arc_tangent() {
            OPT_Complex t1 = stack.stack_drop();
            stack.stack_push(t1.atan());
            this.updateStackProperty();
        }
        
        /**
	 * Calculates the logarithm for the top element in the stack.
	 * 
	 */
        public void logarithm() {
            OPT_Complex t1 = stack.stack_drop();
            stack.stack_push(t1.log());
            this.updateStackProperty();
        }
        
        /**
	 * Calculates the exponential for the top element in the stack.
	 * 
	 */
        public void exponential() {
            OPT_Complex t1 = stack.stack_drop();
            stack.stack_push(t1.exp());
            this.updateStackProperty();
        }
        
        /**
	 * Calculates the argument for the top element in the stack.
	 * 
	 */
        public void argument() {
            OPT_Complex t1 = stack.stack_drop();
            stack.stack_push(t1.arg());
            this.updateStackProperty();
        }
        
        /**
         * Calculates the conjugate for the top element in the stack.
         * 
         */
        public void conjugate() {
            OPT_Complex t1 = stack.stack_drop();
            stack.stack_push(t1.conjugate());
            this.updateStackProperty();
        }
        
        /**
         * Calculates the module for the top element in the stack.
         * 
         */
        public void module() {
            OPT_Complex t1 = stack.stack_drop();
            stack.stack_push(t1.mod());
            this.updateStackProperty();
        }

        /**
         * Updates stack properties.
         * 
         */
        private void updateStackProperty() {
            stack_p.set(stack.toString().replace("[", "").replace("]", ""));
        }
        
        /**
         * Updates variables properties.
         * 
         */
        private void updateVarsProperty() {
            String dsp = "";
            for (Entry<String, OPT_Complex> e : vars.entrySet())
                dsp += e.toString() + '\n';
            vars_p.set(dsp);
        }
        
        /**
         * Updates functions properties.
         * 
         */
        private void updateFuncsProperty() {
            String dsp = "";
            for (Entry<String, String> e : funcs.entrySet())
                dsp += e.toString() + '\n';
            funcs_p.set(dsp);
        }
        
        /**
         * Retrieves stack property.
         * 
         * @return  stack property.
         */
        public SimpleStringProperty stackProperty() {
            return stack_p;
        }
        
        /**
         * Retrieves variables property.
         * 
         * @return  variables property.
         */
        public SimpleStringProperty varsProperty() {
            return vars_p;
        }
        
        /**
         * Retrieves functions property.
         * 
         * @return  functions property.
         */
        public SimpleStringProperty funcsProperty() {
            return funcs_p;
        }
        
        /**
         * Stores the variables map in specified file.
         * 
         * @param f the file.
         * @throws IOException  if there is an IO Error.
         */
        public void save_var(File f) throws IOException {
            TreeMap<String, ArrayList<Double>> tmp = new TreeMap<>();
            try {
                ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(f));
                for (Entry<String, OPT_Complex> e : vars.entrySet()){
                    ArrayList<Double> t = new ArrayList<>();
                    t.add(e.getValue().getReal());
                    t.add(e.getValue().getImaginary());
                    tmp.put(e.getKey(), t);
                }
                o.writeObject(tmp);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /**
         * Loads the variables map from specified file.
         * 
         * @param f the file.
         * @throws IOException  if there is an IO Error.
         * @throws ClassNotFoundException   if the cast Class is not in the scope.
         */
        public void load_var(File f) throws IOException, ClassNotFoundException {
            TreeMap<String, ArrayList<Double>> tmp = new TreeMap<>();
            try {
                ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
                tmp = (TreeMap<String, ArrayList<Double>>) i.readObject();
                vars.clear();
                for (Entry<String, ArrayList<Double>> e : tmp.entrySet())
                    vars.put(e.getKey(), new OPT_Complex(e.getValue().get(0), e.getValue().get(1)));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.updateVarsProperty();
        }
        
        /**
         * Stores the functions map in specified file.
         * 
         * @param f the file.
         * @throws IOException  if there is an IO Error.
         */
        public void save_func(File f) throws IOException {
            try {
                ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(f));
                o.writeObject(this.funcs);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /**
         * Loads the functions map from specified file.
         * 
         * @param f the file.
         * @throws IOException  if there is an IO Error.
         * @throws ClassNotFoundException   if the cast Class is not in the Scope.
         */
        public void load_func(File f) throws IOException, ClassNotFoundException {
            try {
                ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
                this.funcs = (TreeMap<String, String>) i.readObject();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.updateFuncsProperty();
        }
        
}
