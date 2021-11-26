package unisa.group1.test_scalc;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            return "(" + format.format(this) + ")";
        }
        
    }
	
	
	/** The stack in which values are stored. */
	private Advanced_Stack<OPT_Complex> stack;

	
	/**
	 * Create an instance of Calculator
	 * setting an empty stack.
	 * 
	 */
	public Calculator() {
		super();
		this.stack = new Advanced_Stack<>();
	}
	
	/**
	 * Inserts an operand in the stack.
	 * 
	 * @param c	the operand.
	 */
	public void push_num(OPT_Complex c) {
		stack.stack_push(c);
	}
	
	/**
	 * Sums all the elements in the stack and updates it.
	 * 
	 */
	public void sum() {
		ArrayList<OPT_Complex> list = stack.stack_collect();
		OPT_Complex tmp = list.get(0);
		for(int i = 1; i < list.size(); i++)
			tmp = tmp.add(list.get(i));
                
		stack.stack_clear();
		stack.stack_push(tmp);
	}
	
	/**
	 * Subtracts all the elements in the stack and updates it.
	 * 
	 */
	public void subtract() {
		ArrayList<OPT_Complex> list = stack.stack_collect();
		OPT_Complex tmp = list.get(0);
		for(int i = 1; i < list.size(); i++)
			tmp = tmp.subtract(list.get(i));
		stack.stack_clear();
		stack.stack_push(tmp);
	}
	
	/**
	 * Multiplies all the elements in the stack and updates it.
	 * 
	 */
	public void multiply() {
		ArrayList<OPT_Complex> list = stack.stack_collect();
		OPT_Complex tmp = list.get(0);
		for(int i = 1; i < list.size(); i++)
			tmp = tmp.multiply(list.get(i));
		stack.stack_clear();
		stack.stack_push(tmp);
	}
	
	/**
	 * Divides all the elements in the stack and updates it.
	 * 
	 */
	public void divide() {
		ArrayList<OPT_Complex> list = stack.stack_collect();
		OPT_Complex tmp = list.get(0);
		for(int i = 1; i < list.size(); i++)
			tmp = tmp.divide(list.get(i));
		stack.stack_clear();
		stack.stack_push(tmp);
	}
	
	/**
	 * Elevates to the next power sequentially and
	 * updates the stack.
	 * 
	 */
	public void power() {
		ArrayList<OPT_Complex> list = stack.stack_collect();
		OPT_Complex tmp = list.get(0);
		for(int i = 1; i < list.size(); i++)
			tmp = tmp.pow(list.get(i));
		stack.stack_clear();
		stack.stack_push(tmp);
	}
	
	/**
	 * Calculates the square root for all the elements in the stack.
	 * 
	 */
	public void sqrt() {
		ArrayList<OPT_Complex> list = stack.stack_collect();
		stack.stack_clear();
		for(OPT_Complex t : list) {
			stack.stack_push(t.sqrt());
		}
	}
	
	/**
	 * Negates the sign of all the elements in the stack.
	 * 
	 */
	public void negate() {
		ArrayList<OPT_Complex> list = stack.stack_collect();
		stack.stack_clear();
		for(OPT_Complex t : list) {
			stack.stack_push(t.negate());
		}
	}
	
	/**
	 * Clears the stack.
	 * 
	 */
	public void clear() {
		stack.stack_clear();
	}
	
	/**
	 * Removes the top element of the stack.
	 * 
	 */
	public void drop() {
		stack.stack_drop();
	}
	
	/**
	 * Duplicates the top element of the stack.
	 * 
	 */
	public void dup() {
		stack.stack_dup();
	}
	
	/**
	 * Swaps the top two elements of the stack.
	 * 
	 */
	public void swap() {
		stack.stack_swap();
	}
	
	/**
	 * Duplicates the second top element of the stack
	 * and pushes it to the top of it.
	 * 
	 */
	public void over() {
		stack.stack_over();
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
         * @throws ParseException when not correctly parsed.
         */
        public void parse_num(String s) throws ParseException {
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
        
}
