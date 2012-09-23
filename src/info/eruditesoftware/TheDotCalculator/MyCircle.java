package info.eruditesoftware.TheDotCalculator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class MyCircle {
    private int x;
    private int y;
    private int radius;
    private int startAngle; // 3 o'clock is "0" angle degrees
    private int maxTrait;
    public static final int MAX_COLOR = 255;
    public static final int ARC_ANGLE = 90;
    private RectF rect = new RectF();

    private Paint paintBrushOuter = null;
    private Paint paintBrushInner = null;

    public MyCircle(int x, int y, int angle, int maxTrait) {
        super();
        this.x = x;
        this.y = y;
        this.startAngle = angle;
        this.radius = 75;
        this.maxTrait = maxTrait;
    } // end public MyCircle()

    public void setCenterTo(int x, int y) {
        this.x = x;
        this.y = y;
        setArcLocation();
    } // end public void setCenterTo()

    public void setArcLocation() {
        int rectLeft = this.x - this.radius * 3 / 4;
        int rectTop = this.y - this.radius * 3 / 4;
        int rectRight = this.x + this.radius * 3 / 4;
        int rectBottom = this.y + this.radius * 3 / 4;
        rect.set(rectLeft, rectTop, rectRight, rectBottom);
    }

    public void setColorByTrait(int trait1Value, int trait3Value) {
        int redAmount = trait1Value * MAX_COLOR / maxTrait;
        int blueAmount = (maxTrait - trait1Value) * MAX_COLOR / maxTrait;
        int alphaAmount = trait3Value * MAX_COLOR / maxTrait;

        paintBrushOuter = new Paint();
        paintBrushOuter.setColor(Color.argb(MAX_COLOR, redAmount, 0, blueAmount));
        paintBrushOuter.setAntiAlias(true);
        paintBrushOuter.setDither(true);

        paintBrushInner = new Paint();
        paintBrushInner.setColor(Color.argb(alphaAmount, MAX_COLOR - redAmount, 0, MAX_COLOR - blueAmount));
        paintBrushInner.setAntiAlias(true);
        paintBrushInner.setDither(true);

        Log.i("dot", "MyCircle Set Color");
    } // end public setColorByTrait()

    public void setArcStartAngle(int trait2Value) {
        this.startAngle = trait2Value * 360 / maxTrait;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paintBrushOuter);
        canvas.drawArc(rect, startAngle, ARC_ANGLE, true, paintBrushInner);
        Log.i("dot", "MyCircle draw");
    } // end public void draw()

} // end public class MyCircle