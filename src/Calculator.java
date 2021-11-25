package unisa.group1.test_scalc;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Basic implementation of a stack based
 * scientific programmable calculator.
 * 
 * @author Group 1
 *
 */
public class Calculator {
	
	
	/** The stack in which values are stored. */
	private Advanced_Stack<Complex> stack;

	
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
	public void push_num(Complex c) {
		stack.stack_push(c);
	}
	
	/**
	 * Sums all the elements in the stack and updates it.
	 * 
	 */
	public void sum() {
		ArrayList<Complex> list = stack.stack_collect();
		Complex tmp = list.get(0);
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
		ArrayList<Complex> list = stack.stack_collect();
		Complex tmp = list.get(0);
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
		ArrayList<Complex> list = stack.stack_collect();
		Complex tmp = list.get(0);
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
		ArrayList<Complex> list = stack.stack_collect();
		Complex tmp = list.get(0);
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
		ArrayList<Complex> list = stack.stack_collect();
		Complex tmp = list.get(0);
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
		ArrayList<Complex> list = stack.stack_collect();
		stack.stack_clear();
		for(Complex t : list) {
			stack.stack_push(t.sqrt());
		}
	}
	
	/**
	 * Negates the sign of all the elements in the stack.
	 * 
	 */
	public void negate() {
		ArrayList<Complex> list = stack.stack_collect();
		stack.stack_clear();
		for(Complex t : list) {
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
        public Advanced_Stack<Complex> getStack(){
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
                this.push_num(new Complex(realPart,imgPart));
        }
        
}
