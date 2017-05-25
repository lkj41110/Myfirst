package com.rj.lk.untils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.rj.lk.R;

public class PaintView extends CircleImageView {

	public float bitmapX;
	public float bitmapY;

	public PaintView(Context context) {
		super(context);
		bitmapX = 0;
		bitmapY = 200;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.guider18758114722);
		int heigh = bitmap.getRowBytes() * bitmap.getHeight();
		int wedth = bitmap.getRowBytes() * bitmap.getWidth();
		canvas.drawBitmap(bitmap, bitmapX-wedth/1000, bitmapY-heigh/1000, paint);
		if (bitmap.isRecycled()) {
			bitmap.recycle();
		}

	}
}
