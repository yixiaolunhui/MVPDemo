package com.example.com.qqmenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.com.qqmenu.R;

/**
 * 2 tab切换
 * Created by zhouweilong on 15/11/27.
 */
public class TwoTabView  extends LinearLayout implements View.OnClickListener {


    private RadioButton twotab_message_btn;//消息

    private RadioButton twotab_phone_btn;//电话

    private OnTabClickListener onTabClickListener;

    public TwoTabView(Context context) {
        this(context,null);
    }

    public TwoTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.view_twotab_layout, this);
        twotab_message_btn = (RadioButton) findViewById(R.id.twotab_message_btn);
        twotab_phone_btn = (RadioButton) findViewById(R.id.twotab_phone_btn);
        twotab_message_btn.setOnClickListener(this);
        twotab_phone_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.twotab_message_btn://消息
                selected(0);
                break;
            case R.id.twotab_phone_btn://电话
                selected(1);
                break;
        }
    }

    private void selected(int position) {
        if (position == 0) {
            twotab_message_btn.setChecked(true);
            twotab_phone_btn.setChecked(false);
        } else {
            twotab_message_btn.setChecked(false);
            twotab_phone_btn.setChecked(true);
        }
        if (onTabClickListener != null) {
            onTabClickListener.click(position);
        }
    }

    public interface OnTabClickListener {
        void click(int position);
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.onTabClickListener = onTabClickListener;
    }
}
