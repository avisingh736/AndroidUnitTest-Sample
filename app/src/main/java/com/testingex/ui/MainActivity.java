package com.testingex.ui;

import android.os.Bundle;
import android.view.View;

import com.testingex.base.BaseActivity;
import com.testingex.databinding.ActivityMainBinding;
import com.testingex.ui.login.LoginActivity;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnTest.setOnClickListener(view -> navigateTo(LoginActivity.class,null,false));
    }

    public ActivityMainBinding getBinding() {
        return binding;
    }
}
