package ui.king.com.kinglibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ui.king.com.kinglibrary.R;

public class BaseTitle extends LinearLayout{
    private View view;
    private TypedArray array;
    private TextView tvBack;
    private TextView tvMid;
    private TextView tvRight;
    private Context context;

    public BaseTitle(Context context) {
        super(context);
        this.context = context;
    }

    public BaseTitle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        array = context.obtainStyledAttributes(attrs,R.styleable.headTitle);
        view = LayoutInflater.from(context).inflate(R.layout.head_title, null);
        addView(view);
        initView();
    }

    private void initView() {
        tvBack = view.findViewById(R.id.tvBack);
        tvMid = view.findViewById(R.id.tvMid);
        tvRight = view.findViewById(R.id.tvRight);
        String centerTitle = array.getString(R.styleable.headTitle_centerTitle);
        String rightTitle = array.getString(R.styleable.headTitle_rightTitle);
        tvMid.setText(centerTitle);
        tvRight.setText(rightTitle);

    }



}
