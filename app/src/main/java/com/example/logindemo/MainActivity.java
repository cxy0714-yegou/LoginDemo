package com.example.logindemo;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private ImageView mAvatarImageView;
    // 用一个变量来记录选择的头像资源ID，默认是 placeholder
    private int mSelectedAvatarResId = R.drawable.cxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsernameEditText = findViewById(R.id.editText_username);
        mAvatarImageView = findViewById(R.id.imageView_avatar);
    }

    // 处理头像点击事件，选择头像
    public void onAvatarClick(View view) {
        // 这里简化处理，直接弹出一个选择对话框
        // 实际项目中可能会使用 startActivityForResult 调用系统图库
        final int[] avatarResources = {R.drawable.cxy, R.drawable.xjmm, R.drawable.xjm}; // 预先在drawable放几个头像图片

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择头像");
        builder.setItems(new String[]{"头像1", "头像2", "头像3"}, (dialog, which) -> {
            mSelectedAvatarResId = avatarResources[which];
            mAvatarImageView.setImageResource(mSelectedAvatarResId);
        });
        builder.show();
    }

    // 处理登录按钮点击事件
    public void onLoginClick(View view) {
        String username = mUsernameEditText.getText().toString().trim();
        // 密码验证逻辑这里省略...

        // 跳转到 HomeActivity，并传递数据
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("USERNAME", username); // 传递用户名（String）
        intent.putExtra("AVATAR_RES_ID", mSelectedAvatarResId); // 传递头像资源ID（int）
        startActivity(intent);

        // 可选：结束登录界面
        // finish();
    }
}