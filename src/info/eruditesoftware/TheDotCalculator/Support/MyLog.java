package info.eruditesoftware.TheDotCalculator.Support;

import android.util.Log;
import info.eruditesoftware.TheDotCalculator.BuildConfig;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 8/3/12
 * Time: 9:28 PM
 * <p/>
 * The 'if (BuildConfig.DEBUG) {' line makes it such that all logging is automatically removed from production builds, as is recommended by Android
 */
public class MyLog {
    private static final String _tag = "dotcalc";

    public static void v(String message) {
        if (BuildConfig.DEBUG) {
            Log.v(_tag, message);
        }
    }

    public static void i(String message) {
        if (BuildConfig.DEBUG) {
            Log.i(_tag, message);
        }
    }

    public static void e(String message) {
        if (BuildConfig.DEBUG) {
            Log.e(_tag, message);
        }
    }
}
