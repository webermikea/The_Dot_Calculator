package info.eruditesoftware.TheDotCalculator.Players;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 9/25/12
 * Time: 7:45 PM
 * <p/>
 * This is an interface - it just defines what a player must have and must be implemented by a class
 * java defaults variables to protected; which is what we want
 */
public interface PlayerTraits {
    public int get_sociability();

    public int get_aggressiveness();

    public int get_passivity();

    public int get_introvertedness();

    public void set_sociability(int _soci);

    public void set_aggressiveness(int _aggr);

    public void set_passivity(int _pass);

    public void set_introvertedness(int _intr);
}
