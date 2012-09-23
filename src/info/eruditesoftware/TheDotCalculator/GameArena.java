package info.eruditesoftware.TheDotCalculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

public class GameArena extends View {
    private MyCircle circle = null;
    private MyCircle circle2 = null;
    int yDot = 150;
    int yEnc = 325;
    //blah

    public GameArena(Context context) {
        super(context);
        int maxTrait = The_Dot_Calculator.MAX_TRAIT;
        circle = new MyCircle(yDot, yDot, 0, maxTrait);
        circle2 = new MyCircle(yEnc, yEnc, 90, maxTrait);
    } // end public GameArena()

    public void setColors(int circleDot1, int circleDot3, int circleEnc1, int circleEnc3) {
        circle.setColorByTrait(circleDot1, circleDot3);
        circle2.setColorByTrait(circleEnc1, circleEnc3);
    }

    public void setLocation(int circleDot4, int circleEnc4) {
        int maxTrait = The_Dot_Calculator.MAX_TRAIT;
        circle.setCenterTo((circleDot4 * 350 / maxTrait) + 50, yDot);
        circle2.setCenterTo((circleEnc4 * 350 / maxTrait) + 50, yEnc);
    }

    public void setAngle(int circleDot2, int circleEnc2) {
        circle.setArcStartAngle(circleDot2);
        circle2.setArcStartAngle(circleEnc2);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
        circle.draw(canvas);
        circle2.draw(canvas);
    } // end public void onDraw()

} // end public class GameArena