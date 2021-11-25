package unisa.group1.test_scalc;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of modified stack
 * adapted to a scientific calculator
 * with support to Generics.
 * 
 * @author Group 1
 *
 * @param <E>	Generic element inherited from Stack.
 */
public class Advanced_Stack<E> extends Stack<E> {

	
	/** Serilizable version identifier. */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Create an instance of the class
	 * using the underlying constructor.
	 * 
	 */
	public Advanced_Stack() {
		super();
	}
	
	/**
	 * Push an element on the top of the stack.
	 * 
	 * @param e	the element to be pushed.
	 * @return	the element just inserted.
	 */
	public E stack_push(E e) {
		this.push(e);
		return e;
	}
	
	/**
	 * Clear the stack from all his elements.
	 * 
	 */
	public void stack_clear() {
		this.clear();
	}
	
	/**
	 * Removes the element at the top of the stack.
	 * 
	 * @return	the previous top element.
	 * @throws EmptyStackException	if the stack is empty.
	 */
	public E stack_drop() throws EmptyStackException {
		return this.pop();
	}
	
	/**
	 * Duplicates the top element of the stack.
	 * 
	 * @throws EmptyStackException	if the stack is empty.
	 */
	public void stack_dup() throws EmptyStackException {
		E tmp = this.peek();
		this.stack_push(tmp);
	}
	
	/**
	 * Swaps the top two elements of the stack.
	 * 
	 * @throws EmptyStackException	if the stack is empty.
	 */
	public void stack_swap() throws EmptyStackException {
		E t1 = this.stack_drop();
		E t2 = this.stack_drop();
		this.stack_push(t1);
		this.stack_push(t2);
	}
	
	/**
	 * Duplicates the second top element of the stack
	 * and pushes it to the top.
	 * 
	 * @throws EmptyStackException	if the stack is empty.
	 */
	public void stack_over() throws EmptyStackException {
		E t1 = this.stack_drop();
		E t2 = this.peek();
		this.stack_push(t1);
		this.stack_push(t2);
	}
	
	/**
	 * Collects all the elements of the stack into a list.
	 * 
	 * @return	the list of elements.
	 * @throws EmptyStackException	if the stack is empty.
	 */
	public ArrayList<E> stack_collect() throws EmptyStackException {
		if(this.isEmpty())
			throw new EmptyStackException();
		ArrayList<E> list = new ArrayList<>(this);
		return list;
	}
	
}
