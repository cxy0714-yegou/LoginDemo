package com.example.logindemo.entity;

import androidx.room.Ignore;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * 用于接收每日消息数量统计结果的数据持有类
 */
@Entity(tableName = "daily_message_counts")
public class DailyMessageCount {
    @PrimaryKey(autoGenerate = true)
    private String day;
    private int count;
    @Ignore
    public DailyMessageCount() {
    }
    public DailyMessageCount(String day, int count) {
        this.day = day;
        this.count = count;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}