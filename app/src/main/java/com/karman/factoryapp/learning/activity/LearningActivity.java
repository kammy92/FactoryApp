package com.karman.factoryapp.learning.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.karman.factoryapp.R;
import com.karman.factoryapp.databinding.ActivityLearningBinding;

public class LearningActivity extends AppCompatActivity {
    ActivityLearningBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_learning);
        binding.btDatabinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearningActivity.this, SampleDataBindingActivity.class);
                startActivity(intent);
            }
        });
        binding.btRxjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearningActivity.this, SampleRxJavaActivity.class);
                startActivity(intent);
            }
        });
    }
}