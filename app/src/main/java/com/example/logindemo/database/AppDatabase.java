package com.example.logindemo.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.logindemo.dao.UserDao;
import com.example.logindemo.dao.FriendDao;
import com.example.logindemo.dao.MessageDao;
import com.example.logindemo.entity.User;
import com.example.logindemo.entity.Friend;
import com.example.logindemo.entity.Message;

@Database(
    entities = {User.class, Friend.class, Message.class},
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    
    public abstract UserDao userDao();
    public abstract FriendDao friendDao();
    public abstract MessageDao messageDao();
    
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "login_demo_database"
            ).build();
        }
        return INSTANCE;
    }
}






