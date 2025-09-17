package com.example.logindemo;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

import com.example.logindemo.database.DatabaseManager;
import com.example.logindemo.entity.Friend;
import com.example.logindemo.entity.Message;

public class ChartsActivity extends AppCompatActivity {
    
    private int userId;
    private DatabaseManager databaseManager;
    private BarChart barChart;
    private PieChart pieChart;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        
        userId = getIntent().getIntExtra("USER_ID", -1);
        databaseManager = DatabaseManager.getInstance(this);
        
        barChart = findViewById(R.id.bar_chart);
        pieChart = findViewById(R.id.pie_chart);
        
        loadChartData();
    }
    
    private void loadChartData() {
        // 加载好友状态数据用于饼图
        databaseManager.getFriendsByUserId(userId, new DatabaseManager.DatabaseCallback<List<Friend>>() {
            @Override
            public void onSuccess(List<Friend> friends) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (friends != null) {
                            setupPieChart(friends);
                        }
                    }
                });
            }
        });
        
        // 加载消息数据用于柱状图
        loadMessageData();
    }
    
    private void setupPieChart(List<Friend> friends) {
        List<PieEntry> entries = new ArrayList<>();
        
        // 统计好友状态
        int onlineCount = 0;
        int offlineCount = 0;
        int busyCount = 0;
        
        for (Friend friend : friends) {
            switch (friend.getStatus()) {
                case "在线":
                    onlineCount++;
                    break;
                case "离线":
                    offlineCount++;
                    break;
                case "忙碌":
                    busyCount++;
                    break;
            }
        }
        
        if (onlineCount > 0) entries.add(new PieEntry(onlineCount, "在线"));
        if (offlineCount > 0) entries.add(new PieEntry(offlineCount, "离线"));
        if (busyCount > 0) entries.add(new PieEntry(busyCount, "忙碌"));
        
        PieDataSet dataSet = new PieDataSet(entries, "好友状态分布");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.setDescription(null);
        pieChart.animateY(1000);
        pieChart.invalidate();
    }
    
    private void loadMessageData() {
        // 这里简化处理，创建一些模拟数据
        setupBarChart();
    }
    
    private void setupBarChart() {
        List<BarEntry> entries = new ArrayList<>();
        
        // 模拟每日消息数量数据
        entries.add(new BarEntry(1f, 15f)); // 周一
        entries.add(new BarEntry(2f, 23f)); // 周二
        entries.add(new BarEntry(3f, 18f)); // 周三
        entries.add(new BarEntry(4f, 31f)); // 周四
        entries.add(new BarEntry(5f, 27f)); // 周五
        entries.add(new BarEntry(6f, 12f)); // 周六
        entries.add(new BarEntry(7f, 8f));  // 周日
        
        BarDataSet dataSet = new BarDataSet(entries, "每日消息数量");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        
        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.setDescription(null);
        barChart.animateY(1000);
        barChart.invalidate();
    }
}
