
import java.math.BigInteger;
import java.util.Random;

public class Utils
{
    private static double minValue = -10;
    private static double maxValue = 10;

    /**
     * Generate a random double value
     *
     * @return
     */
    public static double getRandomDouble()
    {
        return (new Random().nextInt((int) ((maxValue - minValue) * 10 + 1)) + minValue * 10) / 10.0;
    }

    /**
     * Generate a random integer value within a specified range
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandomInt(int min, int max)
    {
        return (new Random().nextInt((max - min) + 1) + min);
    }

    /**
     * Method which calculates fitness value
     *
     * @param value
     * @return
     */
    public static double calculateFitness(double value)
    {
        return (0 - (Math.pow(value, 2) + 2 * (Math.pow(value + 5, 4))));
    }

    /**
     * Convert a binary string into a double
     *
     * @param binary
     * @return
     */
    public static double binaryToDouble(String binary)
    {
        return Double.longBitsToDouble(new BigInteger(binary, 2).longValue());
    }

    /**
     * Replace character in the given position of a string
     *
     * @param str
     * @param position
     * @param character
     * @return
     * @source https://stackoverflow.com/questions/11588916/java-replace-character-at-specific-position-of-string#answer-16318760
     */
    public static String replaceCharAt(String str, int position, char character)
    {
        StringBuffer buf = new StringBuffer(str);
        buf.setCharAt(position, character);
        return buf.toString();
    }

}
