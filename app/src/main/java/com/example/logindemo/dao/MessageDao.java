package com.example.logindemo.dao;

import androidx.room.*;
import com.example.logindemo.entity.Message;
import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM messages WHERE (senderId = :userId1 AND receiverId = :userId2) OR (senderId = :userId2 AND receiverId = :userId1) ORDER BY timestamp ASC")
    List<Message> getMessagesBetweenUsers(int userId1, int userId2);
    
    @Query("SELECT * FROM messages WHERE receiverId = :userId AND isRead = 0")
    List<Message> getUnreadMessages(int userId);
    
    @Query("SELECT * FROM messages WHERE id = :id")
    Message getMessageById(int id);
    
    @Insert
    long insertMessage(Message message);
    
    @Update
    void updateMessage(Message message);
    
    @Delete
    void deleteMessage(Message message);
    
    @Query("DELETE FROM messages WHERE id = :id")
    void deleteMessageById(int id);
    
    @Query("UPDATE messages SET isRead = 1 WHERE id = :id")
    void markMessageAsRead(int id);
    
    @Query("UPDATE messages SET isRead = 1 WHERE receiverId = :userId")
    void markAllMessagesAsRead(int userId);
    
    @Query("SELECT COUNT(*) FROM messages WHERE receiverId = :userId AND isRead = 0")
    int getUnreadMessageCount(int userId);
}




