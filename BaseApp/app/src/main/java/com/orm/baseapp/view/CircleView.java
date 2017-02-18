package com.orm.baseapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class CircleView extends TextView {
	private Paint mBgPaint = new Paint();
 private int flag=0;
	PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0,
			Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

	public CircleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	public CircleView(Context context, AttributeSet attrs) {

		super(context, attrs);

		mBgPaint.setColor(Color.RED);

		mBgPaint.setAntiAlias(true);

	}

	public CircleView(Context context) {

		super(context);

		mBgPaint.setColor(Color.RED);

		mBgPaint.setAntiAlias(true);
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int measuredWidth = getMeasuredWidth();

		int measuredHeight = getMeasuredHeight();

		int max = Math.max(measuredWidth, measuredHeight);

		setMeasuredDimension(max, max);

	}

	public void setBackgroundColor(int color) {
		mBgPaint.setColor(color);
	}

	public void setNotifiText(int text) {

		setText(text + "");

	}

	public void draw(Canvas canvas) {
		canvas.setDrawFilter(pfd);
		canvas.drawCircle(getWidth() / 2, getHeight() / 2,
				Math.max(getWidth(), getHeight()) / 2, mBgPaint);
		
		super.draw(canvas);

	}

}
