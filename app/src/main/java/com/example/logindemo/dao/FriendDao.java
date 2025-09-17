package com.example.logindemo.dao;

import androidx.room.*;
import com.example.logindemo.entity.Friend;
import java.util.List;

@Dao
public interface FriendDao {
    @Query("SELECT * FROM friends WHERE userId = :userId")
    List<Friend> getFriendsByUserId(int userId);
    
    @Query("SELECT * FROM friends WHERE id = :id")
    Friend getFriendById(int id);
    
    @Insert
    long insertFriend(Friend friend);
    
    @Update
    void updateFriend(Friend friend);
    
    @Delete
    void deleteFriend(Friend friend);
    
    @Query("DELETE FROM friends WHERE id = :id")
    void deleteFriendById(int id);
    
    @Query("UPDATE friends SET status = :status WHERE id = :id")
    void updateFriendStatus(int id, String status);
    
    @Query("SELECT COUNT(*) FROM friends WHERE userId = :userId")
    int getFriendCountByUserId(int userId);
}




