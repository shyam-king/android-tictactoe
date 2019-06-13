package com.github.shyamking.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    BoardView board;
    LinearLayout rootContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = new BoardView(this);
        rootContainer = findViewById(R.id.rootContainer);
        LinearLayout.LayoutParams rootContainerLayout = new LinearLayout.LayoutParams(-1, -1);
        rootContainerLayout.setMargins(10,10,10,10);
        rootContainer.addView(board, rootContainerLayout);

    }
}
