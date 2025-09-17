package com.example.logindemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    
    private TextView scoreTextView;
    private TextView questionTextView;
    private Button[] answerButtons;
    private int currentScore = 0;
    private int currentQuestion = 0;
    private int correctAnswer;
    private Random random = new Random();
    
    // 简单的数学题目
    private String[] questions = {
        "2 + 3 = ?",
        "5 × 4 = ?",
        "10 - 6 = ?",
        "8 ÷ 2 = ?",
        "3 + 7 = ?",
        "6 × 3 = ?",
        "15 - 8 = ?",
        "12 ÷ 4 = ?",
        "4 + 9 = ?",
        "7 × 2 = ?"
    };
    
    private int[] answers = {5, 20, 4, 4, 10, 18, 7, 3, 13, 14};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        initViews();
        setupGame();
    }
    
    private void initViews() {
        scoreTextView = findViewById(R.id.textView_score);
        questionTextView = findViewById(R.id.textView_question);
        
        answerButtons = new Button[4];
        answerButtons[0] = findViewById(R.id.button_answer1);
        answerButtons[1] = findViewById(R.id.button_answer2);
        answerButtons[2] = findViewById(R.id.button_answer3);
        answerButtons[3] = findViewById(R.id.button_answer4);
        
        // 设置按钮点击事件
        for (int i = 0; i < answerButtons.length; i++) {
            final int index = i;
            answerButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(index);
                }
            });
        }
        
        findViewById(R.id.button_restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });
    }
    
    private void setupGame() {
        currentScore = 0;
        currentQuestion = 0;
        updateScore();
        generateQuestion();
    }
    
    private void generateQuestion() {
        if (currentQuestion >= questions.length) {
            showGameOver();
            return;
        }
        
        questionTextView.setText(questions[currentQuestion]);
        correctAnswer = answers[currentQuestion];
        
        // 生成4个选项，其中一个是正确答案
        int[] options = generateOptions(correctAnswer);
        
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(String.valueOf(options[i]));
            answerButtons[i].setEnabled(true);
        }
    }
    
    private int[] generateOptions(int correctAnswer) {
        int[] options = new int[4];
        options[0] = correctAnswer;
        
        // 生成3个错误选项
        for (int i = 1; i < 4; i++) {
            int wrongAnswer;
            do {
                wrongAnswer = correctAnswer + random.nextInt(10) - 5;
            } while (wrongAnswer == correctAnswer || wrongAnswer < 0 || contains(options, wrongAnswer));
            options[i] = wrongAnswer;
        }
        
        // 打乱选项顺序
        for (int i = 0; i < options.length; i++) {
            int randomIndex = random.nextInt(options.length);
            int temp = options[i];
            options[i] = options[randomIndex];
            options[randomIndex] = temp;
        }
        
        return options;
    }
    
    private boolean contains(int[] array, int value) {
        for (int item : array) {
            if (item == value) {
                return true;
            }
        }
        return false;
    }
    
    private void checkAnswer(int selectedIndex) {
        Button selectedButton = answerButtons[selectedIndex];
        int selectedAnswer = Integer.parseInt(selectedButton.getText().toString());
        
        // 禁用所有按钮
        for (Button button : answerButtons) {
            button.setEnabled(false);
        }
        
        if (selectedAnswer == correctAnswer) {
            currentScore += 10;
            selectedButton.setBackgroundColor(0xFF4CAF50); // 绿色
            Toast.makeText(this, "正确！+10分", Toast.LENGTH_SHORT).show();
        } else {
            selectedButton.setBackgroundColor(0xFFF44336); // 红色
            Toast.makeText(this, "错误！正确答案是: " + correctAnswer, Toast.LENGTH_SHORT).show();
        }
        
        updateScore();
        currentQuestion++;
        
        // 延迟1秒后显示下一题
        selectedButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                resetButtonColors();
                generateQuestion();
            }
        }, 1000);
    }
    
    private void resetButtonColors() {
        for (Button button : answerButtons) {
            button.setBackgroundColor(0xFF2196F3); // 蓝色
        }
    }
    
    private void updateScore() {
        scoreTextView.setText("得分: " + currentScore);
    }
    
    private void showGameOver() {
        questionTextView.setText("游戏结束！\n最终得分: " + currentScore);
        
        for (Button button : answerButtons) {
            button.setEnabled(false);
        }
        
        Toast.makeText(this, "恭喜！你完成了所有题目！", Toast.LENGTH_LONG).show();
    }
    
    private void restartGame() {
        setupGame();
        resetButtonColors();
    }
}
