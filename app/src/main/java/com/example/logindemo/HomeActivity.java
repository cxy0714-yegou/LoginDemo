package com.example.logindemo;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 1. 接收从MainActivity传递过来的数据
        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");
        int avatarResId = intent.getIntExtra("AVATAR_RES_ID", R.drawable.cxy); // 默认值

        // 2. 找到控件并显示数据
        TextView usernameTv = findViewById(R.id.textView_username);
        ImageView avatarIv = findViewById(R.id.imageView_home_avatar);

        usernameTv.setText("欢迎: " + username);
        avatarIv.setImageResource(avatarResId);

        // 3. 设置ListView（第5步会详细实现）
        setupFriendListView();
    }

    private void setupFriendListView() {
        // 代码将在第5步实现
    }
}