package com.example.logindemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import android.widget.SimpleAdapter;

import com.example.logindemo.database.DatabaseManager;
import com.example.logindemo.entity.Message;

public class ChatActivity extends AppCompatActivity {
    
    private int currentUserId;
    private int friendId;
    private String friendName;
    private DatabaseManager databaseManager;
    private List<Message> messageList;
    private SimpleAdapter messageAdapter;
    private ListView messageListView;
    private EditText messageEditText;
    private Button sendButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        
        // 获取传递的数据
        currentUserId = getIntent().getIntExtra("CURRENT_USER_ID", -1);
        friendId = getIntent().getIntExtra("FRIEND_ID", -1);
        friendName = getIntent().getStringExtra("FRIEND_NAME");
        
        // 设置标题
        TextView titleTextView = findViewById(R.id.textView_chat_title);
        titleTextView.setText("与 " + friendName + " 的聊天");
        
        // 初始化数据库管理器
        databaseManager = DatabaseManager.getInstance(this);
        
        // 初始化UI组件
        messageListView = findViewById(R.id.listView_messages);
        messageEditText = findViewById(R.id.editText_message);
        sendButton = findViewById(R.id.button_send);
        
        // 设置发送按钮点击事件
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        
        // 加载聊天记录
        loadMessages();
    }
    
    private void loadMessages() {
        databaseManager.getMessagesBetweenUsers(currentUserId, friendId, new DatabaseManager.DatabaseCallback<List<Message>>() {
            @Override
            public void onSuccess(List<Message> messages) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        messageList = messages != null ? messages : new ArrayList<>();
                        updateMessageListView();
                    }
                });
            }
        });
    }
    
    private void updateMessageListView() {
        List<Map<String, Object>> messageDataList = new ArrayList<>();
        
        for (Message message : messageList) {
            Map<String, Object> map = new HashMap<>();
            map.put("content", message.getContent());
            map.put("timestamp", formatTimestamp(message.getTimestamp()));
            map.put("is_sender", message.getSenderId() == currentUserId);
            messageDataList.add(map);
        }
        
        messageAdapter = new SimpleAdapter(
            this,
            messageDataList,
            R.layout.item_message,
            new String[]{"content", "timestamp", "is_sender"},
            new int[]{R.id.textView_message_content, R.id.textView_message_time, R.id.textView_message_sender}
        );
        
        messageListView.setAdapter(messageAdapter);
        
        // 滚动到底部
        if (messageList.size() > 0) {
            messageListView.setSelection(messageList.size() - 1);
        }
    }
    
    private void sendMessage() {
        String content = messageEditText.getText().toString().trim();
        if (content.isEmpty()) {
            Toast.makeText(this, "请输入消息内容", Toast.LENGTH_SHORT).show();
            return;
        }
        
        Message message = new Message(content, currentUserId, friendId, "text");
        databaseManager.insertMessage(message, new DatabaseManager.DatabaseCallback<Long>() {
            @Override
            public void onSuccess(Long result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        messageEditText.setText("");
                        loadMessages(); // 重新加载消息列表
                    }
                });
            }
        });
    }
    
    private String formatTimestamp(long timestamp) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault());
        return sdf.format(new java.util.Date(timestamp));
    }
}
