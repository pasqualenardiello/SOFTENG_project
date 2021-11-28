package unisa.group1.test_scalc;
import java.util.ArrayList;
import java.util.EmptyStackException;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Group 1
 */
public class Advanced_StackTest {
    
    public Advanced_StackTest() {
    }

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    /**
     * Test of stack_push method, of class Advanced_Stack.
     */
    @org.junit.Test
    public void testStack_push() {
        System.out.println("stack_push");
        Integer e = 1;
        Advanced_Stack<Integer> instance = new Advanced_Stack<>();
        Integer expResult = 1;
        Integer result = instance.stack_push(e);
        Integer result2 = instance.peek();
        assertEquals(expResult, result);
        assertEquals(expResult, result2);
    }

    /**
     * Test of stack_clear method, of class Advanced_Stack.
     */
    @org.junit.Test
    public void testStack_clear() {
        System.out.println("stack_clear");
        Advanced_Stack<Integer> instance = new Advanced_Stack<>();
        instance.stack_push(1);
        instance.stack_push(2);
        instance.stack_clear();
        assertEquals(0, instance.size());
    }

    /**
     * Test of stack_drop method, of class Advanced_Stack.
     */
    @org.junit.Test
    public void testStack_drop() {
        System.out.println("stack_drop");
        Advanced_Stack<Integer> instance = new Advanced_Stack<>();
        instance.stack_push(1);
        int sz = instance.size();
        Integer expResult = 1;
        Integer result = instance.stack_drop();
        assertEquals(expResult, result);
        assertEquals(sz - 1, instance.size());
        assertThrows(EmptyStackException.class, () -> instance.stack_drop());
    }

    /**
     * Test of stack_dup method, of class Advanced_Stack.
     */
    @org.junit.Test
    public void testStack_dup() {
        System.out.println("stack_dup");
        Advanced_Stack<Integer> instance = new Advanced_Stack<>();
        instance.stack_push(1);
        instance.stack_dup();
        assertEquals(instance.stack_drop(), instance.stack_drop());
    }

    /**
     * Test of stack_swap method, of class Advanced_Stack.
     */
    @org.junit.Test
    public void testStack_swap() {
        System.out.println("stack_swap");
        Advanced_Stack<Integer> instance = new Advanced_Stack<>();
        Integer exp1 = instance.stack_push(1);
        Integer exp2 = instance.stack_push(2);
        instance.stack_swap();
        assertEquals(exp1, instance.stack_drop());
        assertEquals(exp2, instance.stack_drop());
    }

    /**
     * Test of stack_over method, of class Advanced_Stack.
     */
    @org.junit.Test
    public void testStack_over() {
        System.out.println("stack_over");
        Advanced_Stack<Integer> instance = new Advanced_Stack<>();
        Integer exp1 = instance.stack_push(1);
        instance.stack_push(2);
        instance.stack_over();
        assertEquals(exp1, instance.stack_drop());
    }

    /**
     * Test of stack_collect method, of class Advanced_Stack.
     */
    @org.junit.Test
    public void testStack_collect() {
        System.out.println("stack_collect");
        Advanced_Stack<Integer> instance = new Advanced_Stack<>();
        instance.stack_push(1);
        instance.stack_push(2);
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(1);
        expResult.add(2);
        ArrayList<Integer> result = instance.stack_collect();
        assertEquals(result.size(), expResult.size());
        for (int i = 0; i < result.size(); i++)
            assertEquals(result.get(i), expResult.get(i));
        instance.stack_clear();
        assertThrows(EmptyStackException.class, () -> instance.stack_collect());
    }
    
}
