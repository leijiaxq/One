package com.meimengmeng.one.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.meimengmeng.one.R;


public class ZProgressHUD extends Dialog {
    
	public static final int FADED_ROUND_SPINNER = 0;
//	public static final int GEAR_SPINNER = 1;
//	public static final int SIMPLE_ROUND_SPINNER = 2;
    
	static ZProgressHUD instance;
	View view;
	TextView tvMessage;
//	ImageView ivSuccess;
//	ImageView ivFailure;
	ImageView ivProgressSpinner;
	AnimationDrawable adProgressSpinner;
	Context context;
    
	OnDialogDismiss onDialogDismiss;
    
	public OnDialogDismiss getOnDialogDismiss() {
		return onDialogDismiss;
	}
    
	public void setOnDialogDismiss(OnDialogDismiss onDialogDismiss) {
		this.onDialogDismiss = onDialogDismiss;
	}
    
	public static ZProgressHUD getInstance(Context context) {
		if (instance == null) {
			instance = new ZProgressHUD(context);
		}
		return instance;
	}
    
	public ZProgressHUD(Context context) {
		super(context, R.style.DialogTheme);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setBackgroundDrawable(
                                               new ColorDrawable(Color.TRANSPARENT));
		this.setCanceledOnTouchOutside(false);
		this.context = context;
		view = getLayoutInflater().inflate(R.layout.dialog_progress, null);
		tvMessage = (TextView) view.findViewById(R.id.textview_message);
		ivProgressSpinner = (ImageView) view
        .findViewById(R.id.imageview_progress_spinner);
        
		this.setContentView(view);
	}

    
	public void setMessage(String message) {
		tvMessage.setText(message);
	}
    
	@Override
	public void show() {
		if (!((Activity) context).isFinishing()) {
			super.show();
		} else {
			instance = null;
		}
	}
    

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		ivProgressSpinner.post(new Runnable() {
            
			@Override
			public void run() {
				adProgressSpinner.start();
                
			}
		});
	}
    
	public interface OnDialogDismiss {
		public void onDismiss();
	}
    
}
