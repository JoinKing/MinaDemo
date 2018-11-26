package ui.king.com.kinglibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ui.king.com.kinglibrary.R;

public class MainTitle extends LinearLayout implements View.OnClickListener{
    private View view;
    private TypedArray array;
    private TextView tvBack;
    private TextView tvMid;
    private ImageView imRight;

    public MainTitle(Context context) {
        super(context);
    }

    public MainTitle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        array = context.obtainStyledAttributes(attrs,R.styleable.mainTitle);
        view = LayoutInflater.from(context).inflate(R.layout.main_title, null);
        addView(view);
        initView();
    }

    private void initView() {
        tvBack = view.findViewById(R.id.tvBack);
        tvMid = view.findViewById(R.id.tvMid);
        imRight = view.findViewById(R.id.imRight);
        imRight.setOnClickListener(this);
        String leftTitle = array.getString(R.styleable.mainTitle_leftText);
        String centerTitle = array.getString(R.styleable.mainTitle_centerText);
        tvBack.setText(leftTitle);
        tvMid.setText(centerTitle);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==imRight.getId()){
            if (onclicklistenter!=null){
                onclicklistenter.onClick(imRight);
            }
        }
    }
    private Onclicklistenter onclicklistenter;

    public void setOnclicklistenter(Onclicklistenter onclicklistenter) {
        this.onclicklistenter = onclicklistenter;
    }

    public interface Onclicklistenter{
        void onClick(View view);
    }
}
