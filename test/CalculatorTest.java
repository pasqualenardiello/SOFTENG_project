package unisa.group1.test_scalc;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        instance.add_func("t1", "1 +");
        instance.add_func("t2", "1 + t1");
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
        assertEquals(c2.subtract(c), instance.getStack().peek());
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
        assertEquals(c2.divide(c), instance.getStack().peek());
    }

    /**
     * Test of power method, of class Calculator.
     */
    @Test
    public void testPower() {
        System.out.println("power");
        instance.power();
        assertEquals(c2.pow(c), instance.getStack().peek());
    }

    /**
     * Test of sqrt method, of class Calculator.
     */
    @Test
    public void testSqrt() {
        System.out.println("sqrt");
        instance.sqrt();
        assertEquals(c2.sqrt(), instance.getStack().stack_drop());
    }

    /**
     * Test of negate method, of class Calculator.
     */
    @Test
    public void testNegate() {
        System.out.println("negate");
        instance.negate();
        assertEquals(c2.negate(), instance.getStack().stack_drop());
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
        assertEquals(c2, instance.getStack().stack_drop());
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
        assertEquals(c2, instance.getStack().stack_drop());
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
        String s = "  5 j  +   3 ";
        instance.parse_num(s);
        Calculator.OPT_Complex t = instance.new OPT_Complex(3.0, 5.0);
        assertEquals(t, instance.getStack().peek());       
        //simboli sconosciuti
        assertThrows(NumberFormatException.class, () -> instance.parse_num("+(5)+5j"));
        //simboli inattesi
        assertThrows(NumberFormatException.class, () -> instance.parse_num("++5+5j"));
        assertThrows(NumberFormatException.class, () -> instance.parse_num("+j5+5j"));
    }

    /**
     * Test of add_var method, of class Calculator.
     */
    @Test
    public void testAdd_var() {
        System.out.println("add_var");
        String key = "x";
        instance.add_var(key);
        assertEquals(c2, instance.getVars().get(key));
        assertEquals(c, instance.getStack().peek());
    }

    /**
     * Test of put_var method, of class Calculator.
     */
    @Test
    public void testPut_var() {
        System.out.println("put_var");
        String key = "x";
        instance.add_var(key);
        instance.put_var(key);
        assertEquals(c2, instance.getStack().peek());
    }

    /**
     * Test of sum_var method, of class Calculator.
     */
    @Test
    public void testSum_var() {
        System.out.println("sum_var");
        String key = "x";
        instance.add_var(key);
        instance.sum_var(key);
        assertEquals(c2.add(c), instance.getVars().get(key));
    }

    /**
     * Test of sub_var method, of class Calculator.
     */
    @Test
    public void testSub_var() {
        System.out.println("sum_var");
        String key = "x";
        instance.add_var(key);
        instance.sub_var(key);
        assertEquals(c2.subtract(c), instance.getVars().get(key));
    }

    /**
     * Test of getVars method, of class Calculator.
     */
    @Test
    public void testGetVars() {
        System.out.println("getVars");
        String key = "x";
        instance.add_var(key);
        assertEquals(true, instance.getVars().keySet().contains("x"));
        assertEquals(true, instance.getVars().values().contains(c2));
    }

    /**
     * Test of parse_var method, of class Calculator.
     */
    @Test
    public void testParse_var() {
        System.out.println("parse_var");
        String key = "x";
        instance.parse_var(">x");
        assertEquals(c, instance.getStack().peek());
        assertEquals(c2, instance.getVars().get(key));
        instance.parse_var("<x");
        assertEquals(c2, instance.getStack().peek());
        instance.parse_var("+x");
        assertEquals(c2.add(c2), instance.getVars().get(key));
        instance.parse_var("-x");
        assertEquals(c2.add(c2).subtract(c), instance.getVars().get(key));
    }

    /**
     * Test of getFuncs method, of class Calculator.
     */
    @Test
    public void testGetFuncs() {
        System.out.println("getFuncs");
        TreeMap<String, String> result = instance.getFuncs();
        assertEquals(true, result.containsKey("t1"));
        assertEquals(true, result.containsKey("t2"));
        assertEquals(true, result.containsValue("1 +"));
        assertEquals(true, result.containsValue("1 + t1"));
    }

    /**
     * Test of add_func method, of class Calculator.
     */
    @Test
    public void testAdd_func() {
        System.out.println("add_func");
        String key = "t3";
        String val = "2 + 3 *";
        instance.add_func(key, val);
        TreeMap<String, String> result = instance.getFuncs();
        assertEquals(true, result.containsKey(key));
        assertEquals(true, result.containsValue(val));
    }
    
    /**
     * Test of get_func method, of class Calculator.
     */
    @Test
    public void testGet_func() {
        System.out.println("get_func");
        String key = "t1";
        String result = instance.get_func(key);
        assertEquals("1 +", result);
    }

    /**
     * Test of rem_func method, of class Calculator.
     */
    @Test
    public void testRem_func() {
        System.out.println("rem_func");
        String key = "t1";
        instance.rem_func(key);
        assertEquals(false, instance.getFuncs().containsKey("t1"));
    }

    /**
     * Test of save_func method, of class Calculator.
     */
    @Test
    public void testSave_func() throws Exception {
        System.out.println("save_func");
        instance.save_func();
        File f = new File("funcs.bin");
        assertEquals(true, f.exists());
    }

    /**
     * Test of load_func method, of class Calculator.
     */
    @Test
    public void testLoad_func() throws Exception {
        System.out.println("load_func");
        instance.save_func();
        instance.rem_func("t1");
        assertEquals(false, instance.getFuncs().containsKey("t1"));
        instance.load_func();
        assertEquals(true, instance.getFuncs().containsKey("t1"));
        this.testGet_func();
    }

    /**
     * Test of parse_func method, of class Calculator.
     */
    @Test
    public void testParse_func() throws Exception {
        System.out.println("parse_func");
        instance.parse_func(instance.get_func("t2"));
        assertEquals(c2.add(Calculator.OPT_Complex.ONE).add(Calculator.OPT_Complex.ONE), instance.getStack().peek());
    }

    /**
     * Test of save_var method, of class Calculator.
     */
    @Test
    public void testSave_var() throws Exception {
        System.out.println("save_var");
        instance.save_var();
        File f = new File("vars.bin");
        assertEquals(true, f.exists());
    }

    /**
     * Test of load_var method, of class Calculator.
     */
    @Test
    public void testLoad_var() throws Exception {
        System.out.println("load_var");
        instance.add_var("x"); //c2
        instance.save_var();
        instance.add_var("y"); //c
        assertEquals(true, instance.getVars().containsKey("y"));
        instance.load_var();
        assertThrows(NullPointerException.class, () -> instance.put_var("y"));
        assertEquals(true, instance.getVars().containsKey("x"));
    }

    /**
     * Test of cosine method, of class Calculator.
     */
    @Test
    public void testCosine() {
        System.out.println("cosine");
        instance.cosine();
        assertEquals(c2.cos(), instance.getStack().peek());
    }

    /**
     * Test of sine method, of class Calculator.
     */
    @Test
    public void testSine() {
        System.out.println("sine");
        instance.sine();
        assertEquals(c2.sin(), instance.getStack().peek());
    }

    /**
     * Test of tangent method, of class Calculator.
     */
    @Test
    public void testTangent() {
        System.out.println("tangent");
        instance.tangent();
        assertEquals(c2.tan(), instance.getStack().peek());
    }

    /**
     * Test of arc_cosine method, of class Calculator.
     */
    @Test
    public void testArc_cosine() {
        System.out.println("arc_cosine");
        instance.arc_cosine();
        assertEquals(c2.acos(), instance.getStack().peek());
    }

    /**
     * Test of arc_sine method, of class Calculator.
     */
    @Test
    public void testArc_sine() {
        System.out.println("arc_sine");
        instance.arc_sine();
        assertEquals(c2.asin(), instance.getStack().peek());
    }

    /**
     * Test of arc_tangent method, of class Calculator.
     */
    @Test
    public void testArc_tangent() {
        System.out.println("arc_tangent");
        instance.arc_tangent();
        assertEquals(c2.atan(), instance.getStack().peek());
    }

    /**
     * Test of logarithm method, of class Calculator.
     */
    @Test
    public void testLogarithm() {
        System.out.println("logarithm");
        instance.logarithm();
        assertEquals(c2.log(), instance.getStack().peek());
    }

    /**
     * Test of exponential method, of class Calculator.
     */
    @Test
    public void testExponential() {
        System.out.println("exponential");
        instance.exponential();
        assertEquals(c2.exp(), instance.getStack().peek());
    }

    /**
     * Test of argument method, of class Calculator.
     */
    @Test
    public void testArgument() {
        System.out.println("argument");
        instance.argument();
        assertEquals(c2.arg(), instance.getStack().peek());
    }

    /**
     * Test of all_sum method, of class Calculator.
     */
    @Test
    public void testAll_sum() {
        System.out.println("all_sum");
        OPT_Complex c3 = instance.new OPT_Complex(3.0,-1.0);
        instance.push_num(c3);
        instance.all_sum();
        assertEquals(c3.add(c2).add(c), instance.getStack().peek());
    }

    /**
     * Test of all_subtract method, of class Calculator.
     */
    @Test
    public void testAll_subtract() {
        System.out.println("all_subtract");
        OPT_Complex c3 = instance.new OPT_Complex(3.0,-1.0);
        instance.push_num(c3);
        instance.all_subtract();
        assertEquals(c3.subtract(c2).subtract(c), instance.getStack().peek());
    }

    /**
     * Test of all_multiply method, of class Calculator.
     */
    @Test
    public void testAll_multiply() {
        System.out.println("all_multiply");
        OPT_Complex c3 = instance.new OPT_Complex(3.0,-1.0);
        instance.push_num(c3);
        instance.all_multiply();
        assertEquals(c3.multiply(c2).multiply(c), instance.getStack().peek());
    }

    /**
     * Test of all_divide method, of class Calculator.
     */
    @Test
    public void testAll_divide() {
        System.out.println("all_divide");
        OPT_Complex c3 = instance.new OPT_Complex(3.0,-1.0);
        instance.push_num(c3);
        instance.all_divide();
        assertEquals(c3.divide(c2).divide(c), instance.getStack().peek());
    }

    /**
     * Test of clear_parse_seq method, of class Calculator.
     */
    @Test
    public void testClear_parse_seq() {
        System.out.println("clear_parse_seq");
        String[] tmp = {"(2+ 3j)","(3)"};
        instance.clear_parse_seq(tmp);
        assertEquals(instance.new OPT_Complex(3.0,0.0),instance.getStack().stack_drop());
        assertEquals(instance.new OPT_Complex(2.0,3.0),instance.getStack().stack_drop());
        assertEquals(0,instance.getStack().size());
    }

    /**
     * Test of conjugate method, of class Calculator.
     */
    @Test
    public void testConjugate() {
        System.out.println("conjugate");
        instance.conjugate();
        assertEquals(1.0,instance.getStack().peek().getReal());
        assertEquals(-8.0,instance.getStack().peek().getImaginary());
    }

    /**
     * Test of module method, of class Calculator.
     */
    @Test
    public void testModule() {
        System.out.println("module");
        instance.module();
        assertEquals(new Double(Math.sqrt(1.0*1.0 + 8.0*8.0)).toString().substring(0, 5), instance.getStack().peek().toString().substring(1, 6));
    }
    
}
