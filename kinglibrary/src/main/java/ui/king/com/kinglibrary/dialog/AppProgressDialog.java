package ui.king.com.kinglibrary.dialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import ui.king.com.kinglibrary.R;


public class AppProgressDialog extends ProgressDialog {
	private String message;
	private TextView messageTextView;

	public AppProgressDialog(Activity activity) {
		super(activity, R.style.button_dialogs);
		message = "正在加载...";
	}

	public void setMessage(String message) {
		this.message = message;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_progress_dialog);
		messageTextView =  findViewById(R.id.define_progress_msg);
		messageTextView.setText(message);

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		if (messageTextView != null) {
			messageTextView.setText(message);
		}
	}

}
