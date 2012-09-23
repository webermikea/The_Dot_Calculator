package info.eruditesoftware.TheDotCalculator.Players;

import info.eruditesoftware.TheDotCalculator.Support.MyLog;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 9/25/12
 * Time: 7:46 PM
 * <p/>
 * This is a generic player class; it's input method is an undefined method that must be
 * overriden by implementing classes
 */
public class Player implements PlayerTraits {
    //Constants
    public static final int TRAIT_MIN = 0;
    public static final int TRAIT_MAX = 99;

    //Static Variables

    //Instance Variables
    //Traits
    int _sociability;
    int _aggressiveness;
    int _passivity;
    int _introvertedness;

    //representations
    private int _color; //see Paint conversion to int
    private int _radius; //in pixels
    private int _rotation; //in degrees-per-minute
    private int _halo_color; //see Paint conversion


    public int get_color() {
        return _color;
    }

    public int get_radius() {
        return _radius;
    }

    public int get_rotation() {
        return _rotation;
    }

    public int get_halo_color() {
        return _halo_color;
    }

    //Property mutators are private to prevent manually changing them; all changes must come through the Traits
    private void set_color(int _color) {
        //TODO - define
        this._color = _color;
    }

    private void set_radius(int _radius) {
        //TODO - define
        this._radius = _radius;
    }

    private void set_rotation(int _rotation) {
        //TODO - define
        this._rotation = _rotation;
    }

    private void set_halo_color(int _halo_color) {
        //TODO - define
        this._halo_color = _halo_color;
    }

    @Override
    public int get_sociability() {
        return _sociability;
    }

    @Override
    public int get_aggressiveness() {
        return _aggressiveness;
    }

    @Override
    public int get_passivity() {
        return _passivity;
    }

    @Override
    public int get_introvertedness() {
        return _introvertedness;
    }

    @Override
    public void set_sociability(int soci) {
        if (traitIsValid(soci)) {
            _sociability = soci;
        } else {
            MyLog.i("Failed to set sociability");
        }
    }

    @Override
    public void set_aggressiveness(int aggr) {
        if (traitIsValid(aggr)) {
            _aggressiveness = aggr;
        } else {
            MyLog.i("Failed to set aggressiveness");
        }
    }

    @Override
    public void set_passivity(int pass) {
        if (traitIsValid(pass)) {
            _passivity = pass;
        } else {
            MyLog.i("Failed to set passivity");
        }
    }

    @Override
    public void set_introvertedness(int intr) {
        if (traitIsValid(intr)) {
            _introvertedness = intr;
        } else {
            MyLog.i("Failed to set introvertedness");
        }
    }

    private boolean traitIsValid(int input_trait) {
        return (input_trait >= TRAIT_MIN) && (input_trait <= TRAIT_MAX);
    }

}
