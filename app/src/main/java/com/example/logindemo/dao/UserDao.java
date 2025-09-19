package com.example.logindemo.dao;

import androidx.room.*;
import com.example.logindemo.entity.User;
import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAllUsers();
    
    @Query("SELECT * FROM users WHERE id = :id")
    User getUserById(int id);
    
    @Query("SELECT * FROM users WHERE username = :username")
    User getUserByUsername(String username);
    
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    User loginUser(String username, String password);
    
    @Insert
    long insertUser(User user);
    
    @Update
    void updateUser(User user);
    
    @Delete
    void deleteUser(User user);
    
    @Query("DELETE FROM users WHERE id = :id")
    void deleteUserById(int id);
    
    @Query("UPDATE users SET isOnline = :isOnline WHERE id = :id")
    void updateUserOnlineStatus(int id, boolean isOnline);
    
    @Query("SELECT COUNT(*) FROM users")
    int getUserCount();
}






