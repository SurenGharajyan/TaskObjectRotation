package com.pixomaticcanvas.git;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CanvasActivity extends AppCompatActivity implements IDegreeChange {
    PaintWithCanvas mPaintWithCanvas;
    TextView mRotateText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        init();
    }

    private void init() {
        mRotateText = findViewById(R.id.txt_360_gradius);
        mPaintWithCanvas = findViewById(R.id.paint_canvas);
        mPaintWithCanvas.setOnDrawCalledListener(this);
    }

    @Override
    public void onDegreeCalled(int degree) {
        mRotateText.setText(degree + " degree");
    }
}
