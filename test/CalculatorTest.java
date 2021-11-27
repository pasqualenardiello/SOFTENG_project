package unisa.group1.test_scalc;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import unisa.group1.test_scalc.Calculator.OPT_Complex;

/**
 *
 * @author Group 1
 */
public class CalculatorTest {
    
    Calculator instance;
    Calculator.OPT_Complex c;
    Calculator.OPT_Complex c2;
    
    public CalculatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Calculator();
        c = instance.new OPT_Complex(2.0, 9.0);
        c2 = instance.new OPT_Complex(1.0, 8.0);
        instance.push_num(c);
        instance.push_num(c2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of push_num method, of class Calculator.
     */
    @Test
    public void testPush_num() {
        System.out.println("push_num");
        assertEquals(c2, instance.getStack().peek());
    }

    /**
     * Test of sum method, of class Calculator.
     */
    @Test
    public void testSum() {
        System.out.println("sum");
        instance.sum();
        assertEquals(c.add(c2), instance.getStack().peek());
    }

    /**
     * Test of subtract method, of class Calculator.
     */
    @Test
    public void testSubtract() {
        System.out.println("subtract");
        instance.subtract();
        assertEquals(c.subtract(c2), instance.getStack().peek());
    }

    /**
     * Test of multiply method, of class Calculator.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        instance.multiply();
        assertEquals(c.multiply(c2), instance.getStack().peek());
    }

    /**
     * Test of divide method, of class Calculator.
     */
    @Test
    public void testDivide() {
        System.out.println("divide");
        instance.divide();
        assertEquals(c.divide(c2), instance.getStack().peek());
    }

    /**
     * Test of power method, of class Calculator.
     */
    @Test
    public void testPower() {
        System.out.println("power");
        instance.power();
        assertEquals(c.pow(c2), instance.getStack().peek());
    }

    /**
     * Test of sqrt method, of class Calculator.
     */
    @Test
    public void testSqrt() {
        System.out.println("sqrt");
        instance.sqrt();
        assertEquals(c2.sqrt(), instance.getStack().stack_drop());
        assertEquals(c.sqrt(), instance.getStack().stack_drop());
    }

    /**
     * Test of negate method, of class Calculator.
     */
    @Test
    public void testNegate() {
        System.out.println("negate");
        instance.negate();
        assertEquals(c2.negate(), instance.getStack().stack_drop());
        assertEquals(c.negate(), instance.getStack().stack_drop());
    }

    /**
     * Test of clear method, of class Calculator.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        instance.clear();
        assertEquals(0, instance.getStack().size());
    }

    /**
     * Test of drop method, of class Calculator.
     */
    @Test
    public void testDrop() {
        System.out.println("drop");
        instance.drop();
        assertEquals(c, instance.getStack().peek());
    }

    /**
     * Test of dup method, of class Calculator.
     */
    @Test
    public void testDup() {
        System.out.println("dup");
        instance.dup();
        assertEquals(instance.getStack().stack_drop(), instance.getStack().stack_drop());
    }

    /**
     * Test of swap method, of class Calculator.
     */
    @Test
    public void testSwap() {
        System.out.println("swap");
        instance.swap();
        assertEquals(c, instance.getStack().stack_drop());
        assertEquals(c2, instance.getStack().stack_drop());
    }

    /**
     * Test of over method, of class Calculator.
     */
    @Test
    public void testOver() {
        System.out.println("over");
        instance.over();
        assertEquals(c, instance.getStack().stack_drop());
    }

    /**
     * Test of getStack method, of class Calculator.
     */
    @Test
    public void testGetStack() {
        System.out.println("getStack");
        ArrayList<Calculator.OPT_Complex> l = instance.getStack().stack_collect();
        assertEquals(c, l.get(0));
        assertEquals(c2, l.get(1));
    }

    /**
     * Test of parse_num method, of class Calculator.
     */
    @Test
    public void testParse_num() throws Exception {
        System.out.println("parse_num");
        String s = "3+5j";
        instance.parse_num(s);
        Calculator.OPT_Complex t = instance.new OPT_Complex(3.0, 5.0);
        assertEquals(t, instance.getStack().peek());
    }

}
