import java.util.concurrent.ThreadLocalRandom;
public class Die {
    private final int numSides;
    private int value;

    public Die(int numSides) {
        this.numSides = numSides;
        this.value = 1;
    }

    public int getNumSides() {return this.numSides;}
    public int getValue() {return this.value;}

    public void roll() {
        this.value = ThreadLocalRandom.current().nextInt(1, getNumSides() + 1);
    }

    @Override
    public String toString() {
        return ("Die:" + "\n    Sides: " + numSides + "\n   Value: " + value);
    }
}
