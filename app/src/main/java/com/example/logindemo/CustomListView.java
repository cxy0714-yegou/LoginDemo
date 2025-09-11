package com.example.logindemo;

import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Color;
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
        // 在这里实现你的自定义逻辑
        // 例如：设置默认分割线、点击效果、滚动条样式等
        this.setDividerHeight(2); // 设置分割线高度
        this.setCacheColorHint(Color.TRANSPARENT); // 消除滚动时列表背景变黑的问题
        // 这里可以添加更多自定义属性...
    }
}