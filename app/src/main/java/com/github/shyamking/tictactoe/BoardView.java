package com.github.shyamking.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class BoardView extends View {
    static final int IDX = 1;
    static final int IDO = 4;
    static String TAG = "SHYAMDEBUG";

    enum Difficulty  {
        EASY,
        MEDIUM,
        HARD
    };

    boolean init = false;
    Paint paint;
    int[][] grid; //0 - empty; IDX - X; IDO - O;
    int turn = 0; //0 - X; 1 - O;
    Difficulty difficulty;
    int cellH, cellW;
    RectF rect;

    OnTouchListener onTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            float clientX = event.getX(), clientY = event.getY();
            int[] viewPos = new int[2];
            v.getLocationOnScreen(viewPos);
            int x = (int)(clientX) - viewPos[0];
            int y = (int)(clientY) - viewPos[1];

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (x <= v.getWidth() && y <= v.getHeight()) {
                        int c = x / cellW;
                        int r = y / cellH;

                        Log.d(TAG, c + ", " + r);

                        if (grid[c][r] == 0)
                        {
                            grid[c][r] = IDX;
                            AI_move();
                            invalidate();
                        }

                        return true;
                    }
            }

            return false;
        }
    };

    public BoardView(Context context) {
        super(context);

        setOnTouchListener(onTouchListener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!init) {
            paint = new Paint();
            init = true;
            cellH = getHeight()/3;
            cellW = getWidth()/3;
            rect = new RectF();
            grid = new int[3][3];

            reset();
        }

        canvas.drawColor(Color.rgb(200,200,200));
        paint.setColor(Color.rgb(0,0,0));
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        rect.set(0,0, getWidth(), getHeight());
        canvas.drawRect(rect, paint);

        //boundaries
        canvas.drawLine(0, cellH, getWidth(), cellH, paint);
        canvas.drawLine(0, cellH*2, getWidth(), cellH*2, paint);
        canvas.drawLine(cellW, 0, cellW, getHeight(), paint);
        canvas.drawLine(cellW*2, 0, cellW*2, getHeight(), paint);

        //drawing X and O
        int pad  = 10;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
            {
                if (grid[i][j] == IDX) {
                    paint.setColor(Color.rgb(255, 0, 0));
                    canvas.drawLine(i * cellW + pad, j * cellH + pad, (i+1) * cellW - pad, (j+1) * cellH - pad, paint);
                    canvas.drawLine((i+1) * cellW - pad, j * cellH + pad, i * cellW + pad, (j+1) * cellH - pad, paint);
                }
                else if (grid[i][j] == IDO) {
                    paint.setColor(Color.rgb(0, 0, 255));
                    rect.set(i * cellW + pad, j * cellH + pad, (i+1) * cellW - pad, (j+1) * cellH - 1);
                    canvas.drawOval(rect, paint);
                }
            }
    }

    void reset() {
        for (int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                grid[i][j] = 0;
        difficulty = Difficulty.EASY;
    }

    void setLevel(Difficulty d) {
        difficulty = d;
    }

    void AI_move() {
        switch (difficulty) {
            case EASY:
                break;
            case MEDIUM:
                break;
            case HARD:
                break;
        }
    }
}
