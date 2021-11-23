/**
 * Representation of complex numbers
 * with basic built-in functionality.
 *
 * @author Group 1
 *
 */
public class Complex extends Number  {

	
	/** The imaginary constant. */
    public static final Complex I = new Complex(0.0, 1.0);
    /** Representation of 1. */
    public static final Complex ONE = new Complex(1.0, 0.0);
    /** Representation of 0. */
    public static final Complex ZERO = new Complex(0.0, 0.0);

    /** Serializable version identifier. */
    private static final long serialVersionUID = -6147364516699246680L;

    /** The imaginary part. */
    private final double imaginary;
    /** The real part. */
    private final double real;


    /**
     * Creates a complex number from a real one,
     * giving it an imaginary part of zero.
     *
     * @param real	the real number to be converted.
     */
    public Complex(double real) {
        this(real, 0.0);
    }

    /**
     * Creates a complex number when given
     * both the real and imaginary part.
     *
     * @param real	the real part.
     * @param imaginary	the imaginary part.
     */
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Adds a complex number to the calling number.
     *
     * @param addend	the addend.
     * @return	the sum.
     */
    public Complex add(Complex addend) {
        return createComplex(real + addend.getReal(),
                imaginary + addend.getImaginary());
    }

    /**
     * Adds a real number to the calling number.
     *
     * @param addend	the addend.
     * @return	the sum.
     */
    public Complex add(double addend) {
        return createComplex(real + addend, imaginary);
    }

    /**
     * Calculates the complex with an imaginary part
     * of opposite sign to the calling number.
     *
     * @return	the conjugate number.
     */
    public Complex conjugate() {
        return createComplex(real, -imaginary);
    }

    /**
     * Divides the calling number by the given complex divisor.
     *
     * @param divisor	the divisor.
     * @return	the quotient.
     */
    public Complex divide(Complex divisor) {
        final double c = divisor.getReal();
        final double d = divisor.getImaginary();
        if (c == 0.0 && d == 0.0) {
            return null;
        }
        if (Math.abs(c) < Math.abs(d)) {
            double q = c / d;
            double denominator = c * q + d;
            return createComplex((real * q + imaginary) / denominator,
                    (imaginary * q - real) / denominator);
        } else {
            double q = d / c;
            double denominator = d * q + c;
            return createComplex((imaginary * q + real) / denominator,
                    (imaginary - real * q) / denominator);
        }
    }

    /**
     * Divides the calling number by the given real divisor.
     *
     * @param divisor	the divisor.
     * @return	the quotient.
     */
    public Complex divide(double divisor) {
        if (divisor == 0d) {
            return null;
        }
        return createComplex(real / divisor,
                imaginary  / divisor);
    }

    /**
     * Checks the equality conditions.
     *
     * @return	the boolean equality flag.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Complex){
            Complex c = (Complex) other;
            Double r = real;
            Double or = c.real;
            Double i = imaginary;
            Double oi = c.imaginary;
            return r.equals(or) && i.equals(oi);
        }
        return false;
    }

    /**
     * Calculates the hash value of a given double.
     *
     * @param value	the value to be hashed.
     * @return	the hashcode.
     */
    private static int hashDouble(double value) {
        return new Double(value).hashCode();
    }

    /**
     * Calculates the hash value from both
     * the imaginary and real parts.
     *
     * @return	the hashcode.
     */
    @Override
    public int hashCode() {
        return 37 * (17 * hashDouble(imaginary) +
                hashDouble(real));
    }

    /**
     * Provides the imaginary part as a double.
     *
     * @return	the imaginary part.
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * Provides the real part as a double.
     *
     * @return	the real part.
     */
    public double getReal() {
        return real;
    }

    /**
     * Multiplies the calling number for
     * the given complex factor.
     *
     * @param factor	the factor.
     * @return	the product.
     */
    public Complex multiply(Complex factor) {
        return createComplex(real * factor.real - imaginary * factor.imaginary,
                real * factor.imaginary + imaginary * factor.real);
    }

    /**
     * Multiplies the calling number for
     * the given integer factor.
     *
     * @param factor	the factor.
     * @return	the product.
     */
    public Complex multiply(final int factor) {
        return createComplex(real * factor, imaginary * factor);
    }

    /**
     * Multiplies the calling number for
     * the given double factor.
     *
     * @param factor	the factor.
     * @return	the product.
     */
    public Complex multiply(double factor) {
        return createComplex(real * factor, imaginary * factor);
    }

    /**
     * Changes the sign of the calling number to the opposite one.
     *
     * @return	the negated number.
     */
    public Complex negate() {
        return createComplex(-real, -imaginary);
    }
    
    /**
     * Subtracts the given complex subtrahend
     * to the calling number.
     * 
     * @param subtrahend	the subtrahend.
     * @return	the difference.
     */
    public Complex subtract(Complex subtrahend) {
        return createComplex(real - subtrahend.getReal(),
                imaginary - subtrahend.getImaginary());
    }

    /**
     * Subtracts the given double subtrahend
     * to the calling number.
     * 
     * @param subtrahend	the subtrahend.
     * @return	the difference.
     */
    public Complex subtract(double subtrahend) {
        return createComplex(real - subtrahend, imaginary);
    }

    /**
     * Calculates the exponential in base e.
     *
     * @return	the exponential of e.
     */
    public Complex exp() {
        double expReal = Math.exp(real);
        return createComplex(expReal *  Math.cos(imaginary),
                expReal * Math.sin(imaginary));
    }
    
    /**
     * Calculates the logarithm in base e.
     *
     * @return    the logarithm in base e.
     */
        public Complex log() {
            return createComplex(Math.log(abs()),
                    Math.atan2(imaginary, real));
        }

    /**
     * Calculates the power of the calling number
     * at the given complex exponent.
     *
     * @param x	the exponent.
     * @return	the power.
     */
    public Complex pow(Complex x) {
        return this.log().multiply(x).exp();
    }

    /**
     * Calculates the power of the calling number
     * at the given double exponent.
     *
     * @param x	the exponent.
     * @return	the power.
     */
    public Complex pow(double x) {
        return this.log().multiply(x).exp();
    }

    /**
     * Calculates the square root of the calling number.
     *
     * @return	the square root.
     */
    public Complex sqrt() {
        if (real == 0.0 && imaginary == 0.0) {
            return createComplex(0.0, 0.0);
        }
        double t = Math.sqrt((Math.abs(real) + abs()) / 2.0);
        if (real >= 0.0) {
            return createComplex(t, imaginary / (2.0 * t));
        } else {
            return createComplex(Math.abs(imaginary) / (2.0 * t),
                    Math.copySign(1d, imaginary) * t);
        }
    }

    /**
     * String representation of complex number.
     *
     * @return	the string representation.
     */
    @Override
    public String toString() {
        return "(" + real + ", " + imaginary + "i)";
    }
    
    /**
     * Protected method for creation of complex numbers.
     *
     * @param realPart    the real part.
     * @param imaginaryPart    the imaginary part.
     * @return    the complex number created.
     */
    protected Complex createComplex(double realPart,
                                    double imaginaryPart) {
        return new Complex(realPart, imaginaryPart);
    }

    /**
     * Static method for creation of complex numbers.
     *
     * @param realPart    the real part.
     * @param imaginaryPart    the imaginary part.
     * @return    the complex number created.
     */
    public static Complex valueOf(double realPart,
                                  double imaginaryPart) {
        return new Complex(realPart, imaginaryPart);
    }
    
    /**
     * Calculates the absolute value of the calling number.
     * 
     * @return	the absolute value of the number.
     */
    public double abs() {
        if (Math.abs(real) < Math.abs(imaginary)) {
            if (real == 0.0) {
                return Math.abs(imaginary);
            }
            double q = real / imaginary;
            return Math.abs(imaginary) * Math.sqrt(1 + q * q);
        } else {
            if (imaginary == 0.0) {
                return Math.abs(real);
            }
            double q = imaginary / real;
            return Math.abs(real) * Math.sqrt(1 + q * q);
        }
    }

    /**
     * The absolute value as a integer.
     * 
     * @return the integer absolute value.
     */
    @Override
    public int intValue() {
        return (int)abs();
    }

    /**
     * The absolute value as a long int.
     * 
     * @return the long int absolute value.
     */
    @Override
    public long longValue() {
        return (long)abs();
    }

    /**
     * The absolute value as a float.
     * 
     * @return the float absolute value.
     */
    @Override
    public float floatValue() {
        return (float)abs();
    }

    /**
     * The absolute value as a double.
     * 
     * @return the double absolute value.
     */
    @Override
    public double doubleValue() {
        return abs();
    }

}
