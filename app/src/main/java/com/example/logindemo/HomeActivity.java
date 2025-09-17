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
import android.widget.Button;

import com.example.logindemo.database.DatabaseManager;
import com.example.logindemo.entity.Friend;
import com.example.logindemo.entity.Message;

public class HomeActivity extends AppCompatActivity {
    
    private int currentUserId;
    private String currentUsername;
    private int currentAvatarResId;
    private DatabaseManager databaseManager;
    private List<Friend> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 1. 接收从MainActivity传递过来的数据
        Intent intent = getIntent();
        currentUserId = intent.getIntExtra("USER_ID", -1);
        currentUsername = intent.getStringExtra("USERNAME");
        currentAvatarResId = intent.getIntExtra("AVATAR_RES_ID", R.drawable.cxy);

        // 2. 找到控件并显示数据
        TextView usernameTv = findViewById(R.id.textView_username);
        ImageView avatarIv = findViewById(R.id.imageView_home_avatar);

        usernameTv.setText("欢迎: " + currentUsername);
        avatarIv.setImageResource(currentAvatarResId);

        // 3. 初始化数据库管理器
        databaseManager = DatabaseManager.getInstance(this);
        
        // 4. 设置ListView
        setupFriendListView();
        
        // 5. 设置按钮点击事件
        setupButtons();
    }

    private void setupFriendListView() {
        // 从数据库获取好友列表
        databaseManager.getFriendsByUserId(currentUserId, new DatabaseManager.DatabaseCallback<List<Friend>>() {
            @Override
            public void onSuccess(List<Friend> friends) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        friendList = friends;
                        if (friendList == null) {
                            friendList = new ArrayList<>();
                        }
                        
                        // 如果数据库中没有好友，创建一些测试数据
                        if (friendList.isEmpty()) {
                            createTestFriends();
                        } else {
                            updateFriendListView();
                        }
                    }
                });
            }
        });
    }
    
    private void createTestFriends() {
        // 创建测试好友数据
        Friend friend1 = new Friend("张三", "在线", R.drawable.cxy, currentUserId);
        Friend friend2 = new Friend("李四", "离线", R.drawable.xjmm, currentUserId);
        Friend friend3 = new Friend("王五", "忙碌", R.drawable.xjm, currentUserId);
        Friend friend4 = new Friend("赵六", "在线", R.drawable.cxy, currentUserId);
        
        databaseManager.insertFriend(friend1, null);
        databaseManager.insertFriend(friend2, null);
        databaseManager.insertFriend(friend3, null);
        databaseManager.insertFriend(friend4, null);
        
        // 重新加载好友列表
        setupFriendListView();
    }
    
    private void updateFriendListView() {
        // 准备数据
        List<Map<String, Object>> friendDataList = new ArrayList<>();
        
        for (Friend friend : friendList) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", friend.getName());
            map.put("status", friend.getStatus());
            friendDataList.add(map);
        }

        // 创建适配器，将数据和布局项绑定
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                friendDataList,
                R.layout.item_friend,
                new String[]{"name", "status"},
                new int[]{R.id.textView_friend_name, R.id.textView_friend_status}
        );

        // 获取ListView并设置适配器
        CustomListView listView = findViewById(R.id.listView_friends);
        listView.setAdapter(adapter);

        // 设置项目点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Friend selectedFriend = friendList.get(position);
                Toast.makeText(HomeActivity.this, "你点击了: " + selectedFriend.getName(), Toast.LENGTH_SHORT).show();
                
                // 跳转到聊天界面
                Intent chatIntent = new Intent(HomeActivity.this, ChatActivity.class);
                chatIntent.putExtra("CURRENT_USER_ID", currentUserId);
                chatIntent.putExtra("FRIEND_ID", selectedFriend.getId());
                chatIntent.putExtra("FRIEND_NAME", selectedFriend.getName());
                startActivity(chatIntent);
            }
        });
    }
    
    private void setupButtons() {
        Button btnAddFriend = findViewById(R.id.btn_add_friend);
        Button btnViewCharts = findViewById(R.id.btn_view_charts);
        Button btnPlayGame = findViewById(R.id.btn_play_game);
        
        if (btnAddFriend != null) {
            btnAddFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addNewFriend();
                }
            });
        }
        
        if (btnViewCharts != null) {
            btnViewCharts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openChartsActivity();
                }
            });
        }
        
        if (btnPlayGame != null) {
            btnPlayGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openGameActivity();
                }
            });
        }
    }
    
    private void addNewFriend() {
        // 创建新好友
        Friend newFriend = new Friend("新朋友" + (friendList.size() + 1), "离线", R.drawable.cxy, currentUserId);
        databaseManager.insertFriend(newFriend, new DatabaseManager.DatabaseCallback<Long>() {
            @Override
            public void onSuccess(Long result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(HomeActivity.this, "添加好友成功", Toast.LENGTH_SHORT).show();
                        setupFriendListView(); // 重新加载列表
                    }
                });
            }
        });
    }
    
    private void openChartsActivity() {
        Intent intent = new Intent(this, ChartsActivity.class);
        intent.putExtra("USER_ID", currentUserId);
        startActivity(intent);
    }
    
    private void openGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}