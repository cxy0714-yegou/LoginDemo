package com.example.logindemo;

import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ListView;

public class CustomListView extends ListView {

    public CustomListView(Context context) {
        super(context);
        init(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.setDividerHeight(2); // 设置分割线高度
        this.setCacheColorHint(Color.TRANSPARENT);
    }
}