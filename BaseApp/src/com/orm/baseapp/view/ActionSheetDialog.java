package com.orm.baseapp.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.orm.baseapp.R;



public class ActionSheetDialog {
	private Context context;
	private Dialog dialog;
	private TextView txt_title;
	private TextView txt_cancel;
	private LinearLayout lLayout_content;
	private ScrollView sLayout_content;
	private boolean showTitle = false;
	private List<SheetItem> sheetItemList;
	private List<SheetItem1> sheetItemList1;
	private Display display;

	public ActionSheetDialog(Context context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	public ActionSheetDialog builder() {
		// 鑾峰彇Dialog甯冨�?
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_sheet, null);

		// 璁剧疆Dialog鏈�灏忓搴︿负灞忓箷�?�藉�?
		view.setMinimumWidth(display.getWidth());

		// 鑾峰彇鑷畾涔塂ialog甯冨眬涓殑鎺т欢
		sLayout_content = (ScrollView) view.findViewById(R.id.sLayout_content);
		lLayout_content = (LinearLayout) view
				.findViewById(R.id.lLayout_content);
		txt_title = (TextView) view.findViewById(R.id.txt_title);
		txt_cancel = (TextView) view.findViewById(R.id.txt_cancel);
		txt_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		// 瀹氫箟Dialog甯冨眬鍜屽弬鏁�
		dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
		dialog.setContentView(view);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.x = 0;
		lp.y = 0;
		dialogWindow.setAttributes(lp);

		return this;
	}

	public ActionSheetDialog setTitle(String title) {
		showTitle = true;
		txt_title.setVisibility(View.VISIBLE);
		txt_title.setText(title);
		return this;
	}

	public ActionSheetDialog setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}

	public ActionSheetDialog setCanceledOnTouchOutside(boolean cancel) {
		dialog.setCanceledOnTouchOutside(cancel);
		return this;
	}

	/**
	 * 
	 * @param strItem
	 *            鏉＄洰鍚嶇�?
	 * @param color
	 *            鏉＄洰�?�椾綋棰滆壊锛岃缃畁ull鍒欓粯璁よ摑鑹�
	 * @param listener
	 * @return
	 */
	public ActionSheetDialog addSheetItem(String strItem, SheetItemColor color,
			OnSheetItemClickListener listener) {
		if (sheetItemList == null) {
			sheetItemList = new ArrayList<SheetItem>();
		}
		sheetItemList.add(new SheetItem(strItem, color, listener));
		return this;
	}
	public ActionSheetDialog addSheetItem1(String strItem, String mess,SheetItemColor color,
			OnSheetItemClickListener listener) {
		if (sheetItemList1 == null) {
			sheetItemList1 = new ArrayList<SheetItem1>();
		}
		sheetItemList1.add(new SheetItem1(strItem,mess, color, listener));
		return this;
	}
	/** 璁剧疆鏉＄洰甯冨�? */
	private void setSheetItems() {
		if (sheetItemList == null || sheetItemList.size() <= 0) {
			return;
		}

		int size = sheetItemList.size();

		// TODO 楂樺害鎺у埗锛岄潪鏈�浣宠В鍐冲姙娉�?
		// 娣诲姞鏉＄洰杩囧鐨勬椂鍊欐帶鍒堕珮搴�
		if (size >= 7) {
			LayoutParams params = (LayoutParams) sLayout_content
					.getLayoutParams();
			params.height = display.getHeight() / 2;
			sLayout_content.setLayoutParams(params);
		}

		// 寰幆娣诲姞鏉＄�?
		for (int i = 1; i <= size; i++) {
			final int index = i;
			SheetItem sheetItem = sheetItemList.get(i - 1);
			String strItem = sheetItem.name;
			SheetItemColor color = sheetItem.color;
			final OnSheetItemClickListener listener = (OnSheetItemClickListener) sheetItem.itemClickListener;
			TextView textView = new TextView(context);
			textView.setText(strItem);
			textView.setTextSize(18);
			textView.setGravity(Gravity.CENTER);

			// 鑳屾櫙鍥剧墖
			if (size == 1) {
				if (showTitle) {
					textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
				} else {
					textView.setBackgroundResource(R.drawable.actionsheet_single_selector);
				}
			} else {
				if (showTitle) {
					if (i >= 1 && i < size) {
						textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
					} else {
						textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
					}
				} else {
					if (i == 1) {
						textView.setBackgroundResource(R.drawable.actionsheet_top_selector);
					} else if (i < size) {
						textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
					} else {
						textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
					}
				}
			}

			// 瀛椾綋棰滆壊
			if (color == null) {
				textView.setTextColor(Color.parseColor(SheetItemColor.Blue
						.getName()));
			} else {
				textView.setTextColor(Color.parseColor(color.getName()));
			}

			// 楂樺�?
			float scale = context.getResources().getDisplayMetrics().density;
			int height = (int) (45 * scale + 0.5f);
			textView.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, height));

			// 鐐瑰嚮浜嬩欢
			textView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onClick(index);
					dialog.dismiss();
				}
			});

			lLayout_content.addView(textView);
		}
	}
	/** 璁剧疆鏉＄洰甯冨�? */
	private void setSheetItems1() {
		if (sheetItemList1 == null || sheetItemList1.size() <= 0) {
			return;
		}

		int size = sheetItemList1.size();

		// TODO 楂樺害鎺у埗锛岄潪鏈�浣宠В鍐冲姙娉�?
		// 娣诲姞鏉＄洰杩囧鐨勬椂鍊欐帶鍒堕珮搴�
		if (size >= 7) {
			LayoutParams params = (LayoutParams) sLayout_content
					.getLayoutParams();
			params.height = display.getHeight() / 2;
			sLayout_content.setLayoutParams(params);
		}

		// 寰幆娣诲姞鏉＄�?
		for (int i = 1; i <= size; i++) {
			final int index = i;
			SheetItem1 sheetItem1 = sheetItemList1.get(i - 1);
			String strItem1 = sheetItem1.name;
			SheetItemColor color = sheetItem1.color;
			String mess=sheetItem1.mess;
			final OnSheetItemClickListener listener = (OnSheetItemClickListener) sheetItem1.itemClickListener;
			View views=LayoutInflater.from(context).inflate(R.layout.actionsheet_item, null);
			TextView tv1=(TextView) views.findViewById(R.id.actionsheet_item_tv1);
			TextView tv2=(TextView) views.findViewById(R.id.actionsheet_item_tv2);
			tv1.setText(strItem1);
			tv2.setText(mess);
			// 鑳屾櫙鍥剧墖
			if (size == 1) {
				if (showTitle) {
					views.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
				} else {
					views.setBackgroundResource(R.drawable.actionsheet_single_selector);
				}
			} else {
				if (showTitle) {
					if (i >= 1 && i < size) {
						views.setBackgroundResource(R.drawable.actionsheet_middle_selector);
					} else {
						views.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
					}
				} else {
					if (i == 1) {
						views.setBackgroundResource(R.drawable.actionsheet_top_selector);
					} else if (i < size) {
						views.setBackgroundResource(R.drawable.actionsheet_middle_selector);
					} else {
						views.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
					}
				}
			}

			// 瀛椾綋棰滆壊
			if (color == null) {
				tv1.setTextColor(Color.parseColor(SheetItemColor.Blue
						.getName()));
			} else {
				tv1.setTextColor(Color.parseColor(color.getName()));
			}

			// 楂樺�?
			float scale = context.getResources().getDisplayMetrics().density;
			int height = (int) (45 * scale + 0.5f);
			
			views.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, height));

			views.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onClick(index);
					dialog.dismiss();
				}
			});

		
			lLayout_content.addView(views);
		}
	}

	public void show() {
		setSheetItems();
		dialog.show();
	}
	public void shows() {
		setSheetItems1();
		dialog.show();
	}

	public interface OnSheetItemClickListener {
		void onClick(int which);
	}

	public class SheetItem {
		String name;
		OnSheetItemClickListener itemClickListener;
		SheetItemColor color;

		public SheetItem(String name, SheetItemColor color,
				OnSheetItemClickListener itemClickListener) {
			this.name = name;
			this.color = color;
			this.itemClickListener = itemClickListener;
		}
	}
	public class SheetItem1 {
		String name;
		String mess;
		OnSheetItemClickListener itemClickListener;
		SheetItemColor color;

		public SheetItem1(String name,String mess, SheetItemColor color,
				OnSheetItemClickListener itemClickListener) {
			this.name = name;
			this.mess=mess;
			this.color = color;
			this.itemClickListener = itemClickListener;
		}
	}

	public enum SheetItemColor {
		Blue("#4361A7"), Red("#FD4A2E");

		private String name;

		private SheetItemColor(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
