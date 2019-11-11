package com.karman.factoryapp.learning.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.karman.factoryapp.R;
import com.karman.factoryapp.databinding.ActivitySampleDatabindingBinding;
import com.karman.factoryapp.learning.model.User;

public class SampleDataBindingActivity extends AppCompatActivity {
    ActivitySampleDatabindingBinding binding;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_databinding);
        user = new User();
        binding.setUser(user);
        binding.btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("karman", user.toString());
            }
        });
    }
}