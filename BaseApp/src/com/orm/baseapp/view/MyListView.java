package com.orm.baseapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

public class MyListView extends ListView {

	public MyListView(Context context) {
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
		MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);

		}

/*	public boolean onInterceptTouchEvent(MotionEvent ev) {
		 switch(ev.getAction()) {
		  case MotionEvent.ACTION_DOWN:
		                 
		 setParentScrollAble(false);//当手指触到listview的时候，让父ScrollView交出ontouch权限，也就是让父scrollview 停住不能滚动
		        break;
		 case MotionEvent.ACTION_MOVE:
		
		       break;
		         
		     case MotionEvent.ACTION_UP:
		                 
		           break;
		             case 
		 MotionEvent.ACTION_CANCEL:
		                 
		 
		                
		  setParentScrollAble(true);//当手指松�?时，让父ScrollView重新拿到onTouch权限
		         
		         break;
		             default:
		                 break;
		      
		    }
		return super.onInterceptTouchEvent(ev);
	}
	  private void setParentScrollAble(boolean flag) {
		ScrollView parentScrollView=(ScrollView) ((LinearLayout)this.getParent()).getParent();
		  parentScrollView.requestDisallowInterceptTouchEvent(!flag);//这里的parentScrollView就是listview外面的那个scrollview
		  
		  
		}
*/
}
