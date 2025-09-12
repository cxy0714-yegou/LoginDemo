package com.example.logindemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomIconButton extends LinearLayout {

    private ImageView mIconImage;
    private TextView mIconText;

    // 必须提供的所有构造函数
    public CustomIconButton(Context context) {
        super(context);
        init(context);
    }

    public CustomIconButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomIconButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        // 使用LayoutInflater加载布局
        LayoutInflater.from(context).inflate(R.layout.custom_icon_button, this, true);

        // 绑定UI元素
        mIconImage = findViewById(R.id.icon_image);
        mIconText = findViewById(R.id.icon_text);

        // 设置点击监听器
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "自定义图标被点击", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 提供方法来动态更新图标和文字
    public void setIconImage(int resourceId) {
        mIconImage.setImageResource(resourceId);
    }

    public void setIconText(String text) {
        mIconText.setText(text);
    }
}