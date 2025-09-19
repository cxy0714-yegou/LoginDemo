package com.example.logindemo.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "friends")
public class Friend {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String name;
    private String status;
    private int avatarResId;
    private long lastActiveTime;
    private int userId; // 关联的用户ID
    @Ignore
    public Friend() {}
    
    public Friend(String name, String status, int avatarResId, int userId) {
        this.name = name;
        this.status = status;
        this.avatarResId = avatarResId;
        this.userId = userId;
        this.lastActiveTime = System.currentTimeMillis();
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public int getAvatarResId() { return avatarResId; }
    public void setAvatarResId(int avatarResId) { this.avatarResId = avatarResId; }
    
    public long getLastActiveTime() { return lastActiveTime; }
    public void setLastActiveTime(long lastActiveTime) { this.lastActiveTime = lastActiveTime; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}






