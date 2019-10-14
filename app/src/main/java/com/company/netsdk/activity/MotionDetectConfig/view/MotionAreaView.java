package com.company.netsdk.activity.MotionDetectConfig.view;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MotionAreaView extends View {
	
	public static final int	EDIT	= 0;
	public static final int	DELETE	= 1;
	public static final int	DRAG	= 2;
	
	private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

	private int			mUnChooseColor  	= Color.rgb(105, 105, 105);
	private int			mChooseColor		= Color.rgb(0, 191, 255);   // 蓝色
	private int			mDeleteColor		= Color.rgb(177, 177, 148);
	private int 			mLineColor1 		= Color.rgb(177, 177, 148);
	private int 			mLineColor2 		= Color.rgb(255, 255, 255);
	private int			mAlpha				= 220;
	private int			mRow				= 0;
	private int 			mCol				= 0;
	private int 			mWidth				= 0;
	private int 			mHeight			= 0;
	private int 			mSourceWidth		= 0;
	private int			mSourceHeight		= 0;
	private int 			mMode				= EDIT;
	
	private float			mStartX;
	private float			mStartY;
	
	private RectF			mMoveRectF;
	private RectF		 	mDeleteRectF;
	private CustomScrollView 					mVScrollView;
	private CustomHScrollView 					mHScrollView;
	
	private List<Cell> 	mCellList			= new ArrayList<Cell>();
	
	
	public MotionAreaView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public MotionAreaView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mWidth = attrs.getAttributeIntValue(NAMESPACE, "layout_width", 250);
		mHeight = attrs.getAttributeIntValue(NAMESPACE, "layout_height", 250);
		this.setBackgroundColor(mUnChooseColor);
		mCol = 22;
		mRow = 18;
	}
	
	/**
	 * 设置编辑模式
	 * @param mode ： EDIT<br/>DELETE<br/>DRAG
	 */
	public void setMode(int mode)
	{
		mMode = mode;
	}
	
	public void setScrollView(CustomScrollView vScrollView,CustomHScrollView hScrollView)
	{
		mVScrollView = vScrollView;
		mHScrollView = hScrollView;
	}
	
	public void setRowAndCol(int row,int col)
	{
		mRow = row;
		mCol = col;
		mStartX = 0;
		mStartY = 0;
		mMoveRectF = null;
		mDeleteRectF = null;
		initCells();
	}
	
	public void setAreas(long[] region)
	{
		int len = region.length;
		for(int i = 0; i< len; i++)
		{
			long col = region[i];
			for(int j = 0; j< mCol; j++)
			{
				if(( col & (0x01<<j) ) != 0)
				{
					mCellList.get(i * mCol + j).choose = true;
				}
			}
		}
	}
	
	public long[] getAreas()
	{
		long[] areas = new long[mRow];
		for(int i = 0; i< mRow; i++)
		{
			long col = 0;
			for(int j = 0; j< mCol; j++)
			{
				if(mCellList.get(i * mCol + j).choose)
				{
					col = col | 0x01<<j;
				}
			}
			areas[i] = col;
		}
		return areas;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		 switch (event.getAction()& MotionEvent.ACTION_MASK) 
		 {
		 	case MotionEvent.ACTION_POINTER_DOWN:
			case MotionEvent.ACTION_DOWN:
				 dealDown(event);
		 		 break;
		 		
		 	case MotionEvent.ACTION_MOVE:
		 		 dealMove(event);
		 		 break;
		 		 
		 	case MotionEvent.ACTION_UP:
		 		 dealUp(event);
		 		 break;
		 
		 }
		 
		 return true;
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if(w ==0 || h ==0)
		{
			return;
		}
		if(mWidth == 0 || mWidth == -1)
		{
			mSourceWidth = w;
			mSourceHeight = h;
		}
		mWidth = w;
		mHeight = h;
		changeCellSize();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		drawLines(canvas);
		drawCells(canvas);
	}

	//画线
	private void drawLines(Canvas canvas)
	{
		//立体感效果是由一条黑线和一条白线组成的
		Paint paint = new Paint();
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
		paint.setAntiAlias(true);
		
		paint.setColor(mLineColor1);
		paint.setStrokeWidth(1);
		paint.setStyle(Style.STROKE);
		float cellWidth = (float)mWidth/(mCol);
		float cellHeight = (float)mHeight/(mRow);
		// 画黑线
		for(int i = 1; i<= mCol; i++)
		{
			float x = cellWidth * i;
			canvas.drawLine(x, 0, x, mHeight, paint);
			
		}
		
		for(int i =1; i<= mRow; i++)
		{
			float y = cellHeight * i;
			canvas.drawLine(0, y, mWidth, y, paint);
			
		}
		
		// 画白线
		paint.setColor(mLineColor2);
		for(int i = 1; i<= mCol; i++)
		{
			float x = cellWidth * i + 1;
			canvas.drawLine(x, 0, x, mHeight, paint);
		}
		
		for(int i =1; i<= mRow; i++)
		{
			float y = cellHeight * i + 1;
			canvas.drawLine(0, y, mWidth, y,paint);
		}
	}

	//画格子
	private void drawCells(Canvas canvas)
	{
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
		paint.setColor(mChooseColor);
		paint.setAlpha(mAlpha);
		
		for(Cell cell : mCellList)
		{
			if(cell.choose)
			{
				canvas.drawRect(cell.rectF, paint);
			}
		}
		if(mMoveRectF != null)
		{
			canvas.drawRect(mMoveRectF, paint);
		}
		
		if(mDeleteRectF != null)
		{
			paint.setColor(mDeleteColor);
			paint.setAlpha(100);
			canvas.drawRect(mDeleteRectF, paint);
		}
	}
	
	
	private void initCells()
	{
		mCellList.clear();
		float cellWidth = (float)mWidth/(mCol);
		float cellHeight = (float)mHeight/(mRow);
		for(int i =0; i< mRow; i++)
		{
			for(int j =0; j < mCol; j++)
			{
				float left  	= cellWidth * j;
				float top		= cellHeight * i;
				float right		= left + cellWidth;
				float bottom 	= top + cellHeight;
				RectF rectF = new RectF(left, top, right, bottom);
				Cell cell = new Cell(rectF, false);
				mCellList.add(cell);
			}
		}
		mMoveRectF = null;
		mDeleteRectF = null;
		invalidate();
	}
	
	private void changeCellSize()
	{
		if(mCellList.isEmpty())
		{
			initCells();
			return;
		}
		float cellWidth = (float)mWidth/(mCol);
		float cellHeight = (float)mHeight/(mRow);
		for(int i =0; i< mRow; i++)
		{
			for(int j =0; j < mCol; j++)
			{
				float left  	= cellWidth * j;
				float top		= cellHeight * i;
				float right		= left + cellWidth;
				float bottom 	= top + cellHeight;
				int index = i * mCol + j;
				RectF rectF = mCellList.get(index).rectF;
				rectF.left = left;
				rectF.top = top;
				rectF.right = right;
				rectF.bottom = bottom;
			}
		}
		invalidate();
	}
	
	//根据坐标获取所在的格子
	private Cell getCellByPoint(float x,float y)
	{
		Cell retCell = null;
		for(Cell cell: mCellList)
		{
			if(cell.rectF.contains(x, y))
			{
				retCell = cell;
				break;
			}
		}
		return retCell;
	}
	
	private float ZoomStartdis,ZoomEnddis;
	private int	  tmpWidth,tmpHeight;
	
	private float spacing(MotionEvent event) 
	{
		 float x = event.getX(0) - event.getX(1);
		 float y = event.getY(0) - event.getY(1);
		 return (float)Math.sqrt(x * x + y * y);
	}
	
	private void dealDown(MotionEvent event)
	{
		ZoomStartdis = 0;
		ZoomEnddis = 0;
		mMoveRectF = null;
		mDeleteRectF = null;
 		mStartX = event.getX();
 		mStartY = event.getY();
 		if( mStartX < 0){
 			mStartX = 1;
		}
		if( mStartX > mWidth)
		{
			mStartX = mWidth - 1;
		}
		if(mStartY < 0){
			mStartY = 1;
		}
		if(mStartY > mHeight)
		{
			mStartY = mHeight -1;
		}
 		switch(mMode)
 		{
	 		case EDIT:
				break;
			case DELETE:
//				Cell end = getCellByPoint(mStartX, mStartY);
//				if(end != null)
//				{
//					end.choose = false;
//					invalidate();
//				}
				break;
			case DRAG:
				ZoomStartdis = 0;
				ZoomEnddis = 0;
				if(event.getPointerCount()>1)
				{
					tmpWidth = mWidth;
					tmpHeight = mHeight;
					ZoomStartdis = spacing(event);
					if(mVScrollView != null)
					{
						mVScrollView.setScrollEnable(false);
					}
					if(mHScrollView != null)
					{
						mHScrollView.setScrollEnable(false);
					}
				}
				break;
			default:
				break;
 		}
	}
	
	private void dealMove(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		if( x < 0){
			x = 1;
		}
		if( x > mWidth)
		{
			x = mWidth - 1;
		}
		if(y < 0){
			y = 1;
		}
		if(y > mHeight)
		{
			y = mHeight -1;
		}
		switch(mMode)
		{
			case EDIT:
				mMoveRectF = new RectF(mStartX, mStartY, x, y);
				invalidate();
				break;
			case DELETE:
				mDeleteRectF = new RectF(mStartX, mStartY, x, y);
				invalidate();
//				Cell end = getCellByPoint(x, y);
//				if(end != null)
//				{
//					end.choose = false;
//					invalidate();
//				}
				break;
			case DRAG:
				if(ZoomStartdis != 0  && event.getPointerCount()>1)
				{
					if(mVScrollView != null)
					{
						mVScrollView.setScrollEnable(false);
					}
					if(mHScrollView != null)
					{
						mHScrollView.setScrollEnable(false);
					}
					ZoomEnddis = spacing(event);
					float scale = ZoomEnddis/ZoomStartdis;
					int width = (int) (tmpWidth * scale);
					int height = (int)(tmpHeight * scale);
					if(width < mSourceWidth)
					{
						width = mSourceWidth;
					}
					if(height < mSourceHeight)
					{
						height = mSourceHeight;
					}
					if(width > 5*mSourceWidth  || height > 5* mSourceHeight)
					{
						return;
					}
					this.setLayoutParams(new LinearLayout.LayoutParams(width,height));
					((LinearLayout)this.getParent()).setLayoutParams(new FrameLayout.LayoutParams(width, height));
				}
				break;
			default:
				break;
		}
		invalidate();
	}
	
	private void dealUp(MotionEvent event)
	{
		mMoveRectF = null;
		mDeleteRectF = null;
		float x = event.getX();
		float y = event.getY();
		if( x < 0){
			x = 1;
		}
		if( x >= mWidth)
		{
			x = mWidth - 1;
		}
		if(y < 0){
			y = 1;
		}
		if(y >= mHeight)
		{
			y = mHeight -1;
		}
 		switch(mMode)
		{
			case EDIT:
				Cell start = getCellByPoint(mStartX, mStartY);
		 		Cell end = getCellByPoint(x, y);
		 		 
		 		float left 		= Math.min(start.rectF.left, end.rectF.left);
		 		float top		= Math.min(start.rectF.top, end.rectF.top);
		 		float right 	= Math.max(start.rectF.right, end.rectF.right);
		 		float bottom	= Math.max(start.rectF.bottom, end.rectF.bottom);
		 		 
		 		RectF rectF = new RectF(left, top, right, bottom);
		 		for(Cell cell: mCellList)
				{
		 			if(rectF.contains(cell.rectF))
					{
						cell.choose = true;
					}
				}
		 		invalidate();
				break;
			case DELETE:
				start = getCellByPoint(mStartX, mStartY);
		 		end = getCellByPoint(x, y);
		 		 
		 		left 	= Math.min(start.rectF.left, end.rectF.left);
		 		top		= Math.min(start.rectF.top, end.rectF.top);
		 		right 	= Math.max(start.rectF.right, end.rectF.right);
		 		bottom	= Math.max(start.rectF.bottom, end.rectF.bottom);
		 		 
		 		rectF = new RectF(left, top, right, bottom);
		 		for(Cell cell: mCellList)
				{
		 			if(rectF.contains(cell.rectF))
					{
						cell.choose = false;
					}
				}
		 		invalidate();
				break;
			case DRAG:
				if(ZoomStartdis != 0)
				{
					if(mVScrollView != null)
					{
						mVScrollView.setScrollEnable(true);
					}
					if(mHScrollView != null)
					{
						mHScrollView.setScrollEnable(true);
					}
				}
				break;
			default:
				break;
		}
 		
	}
	
}

