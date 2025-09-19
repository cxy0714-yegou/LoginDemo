package com.example.logindemo.database;

import android.content.Context;
import android.os.AsyncTask;
import com.example.logindemo.entity.User;
import com.example.logindemo.entity.Friend;
import com.example.logindemo.entity.Message;
import java.util.List;
import com.example.logindemo.entity.DailyMessageCount;

public class DatabaseManager {
    private AppDatabase database;
    private static DatabaseManager instance;
    
    private DatabaseManager(Context context) {
        database = AppDatabase.getInstance(context);
    }
    
    public static synchronized DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }
    
    // 用户相关操作
    public void insertUser(User user, DatabaseCallback<Long> callback) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return database.userDao().insertUser(user);
            }
            
            @Override
            protected void onPostExecute(Long result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }
    
    public void getUserByUsername(String username, DatabaseCallback<User> callback) {
        new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... voids) {
                return database.userDao().getUserByUsername(username);
            }
            
            @Override
            protected void onPostExecute(User result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }
    
    public void loginUser(String username, String password, DatabaseCallback<User> callback) {
        new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... voids) {
                return database.userDao().loginUser(username, password);
            }
            
            @Override
            protected void onPostExecute(User result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }
    
    public void updateUser(User user, DatabaseCallback<Void> callback) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.userDao().updateUser(user);
                return null;
            }
            
            @Override
            protected void onPostExecute(Void result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }
    
    public void deleteUser(User user, DatabaseCallback<Void> callback) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.userDao().deleteUser(user);
                return null;
            }
            
            @Override
            protected void onPostExecute(Void result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }
    
    // 好友相关操作
    public void insertFriend(Friend friend, DatabaseCallback<Long> callback) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return database.friendDao().insertFriend(friend);
            }
            
            @Override
            protected void onPostExecute(Long result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }
    
    public void getFriendsByUserId(int userId, DatabaseCallback<List<Friend>> callback) {
        new AsyncTask<Void, Void, List<Friend>>() {
            @Override
            protected List<Friend> doInBackground(Void... voids) {
                return database.friendDao().getFriendsByUserId(userId);
            }
            
            @Override
            protected void onPostExecute(List<Friend> result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }
    
    public void updateFriend(Friend friend, DatabaseCallback<Void> callback) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.friendDao().updateFriend(friend);
                return null;
            }
            
            @Override
            protected void onPostExecute(Void result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }
    
    public void deleteFriend(Friend friend, DatabaseCallback<Void> callback) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.friendDao().deleteFriend(friend);
                return null;
            }
            
            @Override
            protected void onPostExecute(Void result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }
    
    // 消息相关操作
    public void insertMessage(Message message, DatabaseCallback<Long> callback) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return database.messageDao().insertMessage(message);
            }
            
            @Override
            protected void onPostExecute(Long result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }
    
    public void getMessagesBetweenUsers(int userId1, int userId2, DatabaseCallback<List<Message>> callback) {
        new AsyncTask<Void, Void, List<Message>>() {
            @Override
            protected List<Message> doInBackground(Void... voids) {
                return database.messageDao().getMessagesBetweenUsers(userId1, userId2);
            }
            
            @Override
            protected void onPostExecute(List<Message> result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }
    
    public void updateMessage(Message message, DatabaseCallback<Void> callback) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.messageDao().updateMessage(message);
                return null;
            }
            
            @Override
            protected void onPostExecute(Void result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }

    public void deleteMessage(Message message, DatabaseCallback<Void> callback) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.messageDao().deleteMessage(message);
                return null;
            }
            
            @Override
            protected void onPostExecute(Void result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }

    // 在 DatabaseManager 类中添加以下方法
    public void getDailyMessageCounts(int userId, DatabaseCallback<List<DailyMessageCount>> callback) {
        new AsyncTask<Void, Void, List<DailyMessageCount>>() {
            @Override
            protected List<DailyMessageCount> doInBackground(Void... voids) {
                try {
                    // 注意：这里需要处理 LiveData 到普通列表的转换
                    // 如果直接使用 LiveData，需要在 Activity 中观察
                    return database.messageDao().getDailyMessageCountsSync(userId);
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<DailyMessageCount> result) {
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        }.execute();
    }
    // 回调接口
    public interface DatabaseCallback<T> {
        void onSuccess(T result);
    }
}






