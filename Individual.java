
public class Individual
{

    private double value;

    /**
     * @constructor
     */
    public Individual()
    {
    }

    /**
     * @param value
     * @constructor
     */
    public Individual(double value)
    {
        this.value = value;
    }

    /**
     * @param binaryValue
     * @constructor
     */
    public Individual(String binaryValue)
    {
        this.value = Utils.binaryToDouble(binaryValue);
    }

    /**
     * Returns individual's value as a Binary string
     *
     * @return
     */
    public String getChromosome()
    {
        long number = Double.doubleToLongBits(value);
        String binary = Long.toBinaryString(number);
        while (binary.length() < 64) binary = "0" + binary;
        return binary;
    }

    /**
     * Returns individual's value
     *
     * @return
     */
    public double getValue()
    {
        return value;
    }

    /**
     * Returns individual's fitness value
     *
     * @return
     */
    public double getFitness()
    {
        return Utils.calculateFitness(value);
    }

    /**
     * Set individual's value
     *
     * @param value
     */
    public void setValue(double value)
    {
        this.value = value;
    }

}
