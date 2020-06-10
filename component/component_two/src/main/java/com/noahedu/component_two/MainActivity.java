package com.noahedu.component_two;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.noahedu.service.LoginService;

public class MainActivity extends AppCompatActivity {
//    @Autowired
//    private LoginService loginService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
