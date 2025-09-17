package com.example.logindemo;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.logindemo.database.DatabaseManager;
import com.example.logindemo.entity.User;

public class MainActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private ImageView mAvatarImageView;
    // 用一个变量来记录选择的头像资源ID，默认是 placeholder
    private int mSelectedAvatarResId = R.drawable.cxy;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsernameEditText = findViewById(R.id.editText_username);
        mPasswordEditText = findViewById(R.id.editText_password);
        mAvatarImageView = findViewById(R.id.imageView_avatar);
        
        databaseManager = DatabaseManager.getInstance(this);
        
        // 初始化一些测试数据
        initializeTestData();}

    // 处理头像点击事件，选择头像
    public void onAvatarClick(View view) {
        // 这里简化处理，直接弹出一个选择对话框
        // 实际项目中可能会使用 startActivityForResult 调用系统图库
        final int[] avatarResources = {R.drawable.cxy, R.drawable.xjmm, R.drawable.xjm}; // 预先在drawable放几个头像图片

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择头像");
        builder.setItems(new String[]{"头像1", "头像2", "头像3"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSelectedAvatarResId = avatarResources[which];
                mAvatarImageView.setImageResource(mSelectedAvatarResId);
            }
        });
        builder.show();
    }

    // 处理登录按钮点击事件
    public void onLoginClick(View view) {
        String username = mUsernameEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // 添加调试信息
        Toast.makeText(this, "正在验证用户: " + username, Toast.LENGTH_SHORT).show();
        
        // 临时测试：直接验证硬编码的测试账号
        if (username.equals("admin") && password.equals("123456")) {
            Toast.makeText(this, "测试登录成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("USER_ID", 1);
            intent.putExtra("USERNAME", "admin");
            intent.putExtra("AVATAR_RES_ID", R.drawable.cxy);
            startActivity(intent);
            return;
        }
        
        if (username.equals("user1") && password.equals("password")) {
            Toast.makeText(this, "测试登录成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("USER_ID", 2);
            intent.putExtra("USERNAME", "user1");
            intent.putExtra("AVATAR_RES_ID", R.drawable.xjmm);
            startActivity(intent);
            return;
        }
        
        if (username.equals("user2") && password.equals("password")) {
            Toast.makeText(this, "测试登录成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("USER_ID", 3);
            intent.putExtra("USERNAME", "user2");
            intent.putExtra("AVATAR_RES_ID", R.drawable.xjm);
            startActivity(intent);
            return;
        }
        
        // 尝试登录用户
        databaseManager.loginUser(username, password, new DatabaseManager.DatabaseCallback<User>() {
            @Override
            public void onSuccess(User user) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (user != null) {
                            // 登录成功，更新在线状态
                            user.setOnline(true);
                            databaseManager.updateUser(user, new DatabaseManager.DatabaseCallback<Void>() {
                                @Override
                                public void onSuccess(Void result) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                                            // 跳转到 HomeActivity，并传递数据
                                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                            intent.putExtra("USER_ID", user.getId());
                                            intent.putExtra("USERNAME", user.getUsername());
                                            intent.putExtra("AVATAR_RES_ID", user.getAvatarResId());
                                            startActivity(intent);
                                        }
                                    });
                                }
                            });
                        } else {
                            Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    
    // 初始化测试数据
    private void initializeTestData() {
        // 创建一些测试用户
        User testUser1 = new User("admin", "123456", "admin@example.com", R.drawable.cxy);
        User testUser2 = new User("user1", "password", "user1@example.com", R.drawable.xjmm);
        User testUser3 = new User("user2", "password", "user2@example.com", R.drawable.xjm);
        
        // 先检查用户是否已存在，避免重复插入
        databaseManager.getUserByUsername("admin", new DatabaseManager.DatabaseCallback<User>() {
            @Override
            public void onSuccess(User existingUser) {
                if (existingUser == null) {
                    // 用户不存在，插入测试数据
                    databaseManager.insertUser(testUser1, null);
                    databaseManager.insertUser(testUser2, null);
                    databaseManager.insertUser(testUser3, null);
                    
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "测试数据已初始化", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "数据库已包含用户数据", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}