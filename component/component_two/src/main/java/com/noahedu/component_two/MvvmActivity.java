package com.noahedu.component_two;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.noahedu.mvvm.view.TestMVVMActivity;
import com.noahedu.router.Router;

/**
 * @Description: 测试mvvm
 * @Author: huangjialin
 * @CreateDate: 2020/6/10 9:15
 */
@Route(path = Router.component_two_MvvmActivity)
public class MvvmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        findViewById(R.id.mvvmquest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MvvmActivity.this, TestMVVMActivity.class);
                startActivity(intent);
            }
        });
    }
}
