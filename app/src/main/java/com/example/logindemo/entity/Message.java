package com.example.logindemo.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "messages")
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String content;
    private long timestamp;
    private int senderId;
    private int receiverId;
    private boolean isRead;
    private String messageType; // "text", "image", "file"
    @Ignore
    public Message() {}
    
    public Message(String content, int senderId, int receiverId, String messageType) {
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageType = messageType;
        this.timestamp = System.currentTimeMillis();
        this.isRead = false;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    
    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }
    
    public int getReceiverId() { return receiverId; }
    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }
    
    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }
    
    public String getMessageType() { return messageType; }
    public void setMessageType(String messageType) { this.messageType = messageType; }
}






