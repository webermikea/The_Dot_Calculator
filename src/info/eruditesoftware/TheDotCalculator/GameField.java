package info.eruditesoftware.TheDotCalculator;

import android.graphics.Color;
import android.graphics.Point;
import info.eruditesoftware.TheDotCalculator.Players.Player;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 9/25/12
 * Time: 7:41 PM
 * <p/>
 * TODO Should this be a singleton?  we only want 1 of these per game; that enforces that...?
 * <p/>
 * Making this class *NOT* a view; that should be handled by the AnimationEngine - maybe split out to a 'ViewEngine' or something like that
 */
public class GameField {
    //Constants
    public static final int HEIGHT = 100;
    public static final int WIDTH = 100;
    public static final int TOP = 0;
    public static final int LEFT = 0;
    public static final int BOTTOM = HEIGHT;
    public static final int RIGHT = WIDTH;
    public static final int DEFAULT_BACKGROUND_COLOR = Color.BLACK;

    //board dimensions
    private int _height = HEIGHT;
    private int _width = WIDTH;
    //board colors, etc
    private int _background_color = DEFAULT_BACKGROUND_COLOR;


    //Instance Variables
    // TODO - this is a list, so it uses 'index' to get around; need to either maintain an index list, or some other means of measuring
    // TODO - maybe this should be HashMap of some sort, where I hash on location and player properties
    // TODO - this may need to be AL<Object> to hold anything else (goals, walls, effects, etc)
    ArrayList<PlayerPosition> _player_positions;


    // TODO - I keep wanting to use this because it neatly wraps all Player related stuff (aka - Player + Position) into one package, but I think having it in here may be wrong; it may need just to be a class that is accessible
    protected class PlayerPosition {
        private Player _player;
        private Point _location;

        protected PlayerPosition(Player player, Point location) {
            _player = player;
            _location = location;
        }

        public PlayerPosition(Player player) {
            this(player, new Point(GameField.LEFT, GameField.TOP));
        }

        public Point get_location() {
            return _location;
        }

        public void set_location(Point location) {
            _location = location;
        }

        public Player get_player() {
            return _player;
        }

        public void set_player(Player _player) {
            this._player = _player;
        }
    }

    public GameField(int height, int width, int background_color, ArrayList<PlayerPosition> list) {
        _height = height;
        _width = width;
        _background_color = background_color;
        _player_positions = list;
    }

    public GameField(ArrayList<PlayerPosition> list) {
        this(HEIGHT, WIDTH, DEFAULT_BACKGROUND_COLOR, list);
    }

    public void set_player_positions(ArrayList<PlayerPosition> _player_positions) {
        this._player_positions = _player_positions;
    }

    public int get_height() {
        return _height;
    }

    public int get_width() {
        return _width;
    }

    public int get_background_color() {
        return _background_color;
    }

    public ArrayList<PlayerPosition> get_player_positions() {
        return _player_positions;
    }


    //Public Methods (aka - API)
    public boolean playerExists(Player player) {
        //TODO
        return true;
    }

    public Point getPlayerPosition(Player player) {
        //TODO
        return new Point(TOP, LEFT);
    }

    public boolean movePlayerToPosition(Player player, Point old_position, Point new_position) {
        //PlayerPosition _pp = new PlayerPosition(playerPosition.get_player(), position);
        //TODO - remove old player
        return _player_positions.add(new PlayerPosition(player, new_position));
    }

    public boolean removePlayer(Player player) {
        //TODO -removes the first specified element
        return _player_positions.remove(player);
    }

    public Player removePlayerFromPosition(Point position) {
        //TODO
        return new Player();
    }

    public boolean addPlayerAtPosition(Player player, Point point) {
        PlayerPosition player_position = new PlayerPosition(player, point);
        return _player_positions.add(player_position);
    }

    public Player getPlayerAtPosition(Point point) {
        //TODO
        return new Player();
    }


    //Private Methods
    private boolean playerPositionsValid() {
        //TODO for now just make sure no two are at the same location
        return true;
    }


}
