package com.noahedu.mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.noahedu.component_two.R;
import com.noahedu.mvvm.viewmodel.TestViewModel;

/**
 * @Description: 使用livedata + ViewModel + DataBinding  实现MVVM架构
 * @Author: huangjialin
 * @CreateDate: 2020/6/10 14:10
 */
public class TestMVVMActivity extends AppCompatActivity {
    private TestViewModel testViewModel;


    private Observer<String> getTestObserver = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            /**
             * 如果在这里直接使用DataBinding来将数据和控件绑定，那么一个完整的MVVM就形成了
             */
            Toast.makeText(TestMVVMActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mvvm);
        testViewModel = new ViewModelProvider(this).get(TestViewModel.class);

        testViewModel.getTestMutableLivdData().observe(this, getTestObserver);


        findViewById(R.id.mvvmtest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testViewModel.requestNetWokData();
            }
        });


    }
}
