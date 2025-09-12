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
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

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

        // 3. 设置ListView
        setupFriendListView();
    }

    private void setupFriendListView() {
        // 1. 准备数据
        List<Map<String, Object>> friendList = new ArrayList<>();
        String[] friends = {"张三", "李四", "王五", "赵六"};
        String[] statuses = {"在线", "离线", "忙碌", "在线"};

        for (int i = 0; i < friends.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", friends[i]);
            map.put("status", statuses[i]);
            friendList.add(map);
        }

        // 2. 创建适配器，将数据和布局项绑定
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                friendList,
                R.layout.item_friend, // 需要创建一个新的布局文件 item_friend.xml
                new String[]{"name", "status"}, // Map中的key
                new int[]{R.id.textView_friend_name, R.id.textView_friend_status} // item_friend布局中的控件ID
        );

        // 3. 获取ListView并设置适配器
        CustomListView listView = findViewById(R.id.listView_friends);
        listView.setAdapter(adapter);

        // 4. 设置项目点击事件
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedFriend = friends[position];
            Toast.makeText(HomeActivity.this, "你点击了: " + selectedFriend, Toast.LENGTH_SHORT).show();
        });
    }
}