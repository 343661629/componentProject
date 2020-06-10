package com.noahedu.componentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.noahedu.basecomponent_common.LogUtils;
import com.noahedu.customservice.ServiceFactory;
import com.noahedu.router.ARouterNavigation;
import com.noahedu.router.Router;
import com.noahedu.service.LoginService;

public class MainActivity extends AppCompatActivity {
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);


        findViewById(R.id.test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = ServiceFactory.getInstance().getiGetComponOneDataService().getData();
                textView.setText(string);
            }
        });


        /**
         * 测试组件间数据通信  比如组件1 中获取 组件2中的某一个值   使用的是接口+ 实现的问题
         */
        findViewById(R.id.test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouterNavigation.Navigation(Router.component_one_mainactivity);
                //ARouter.getInstance().navigation(LoginService.class).isLogin("我是组件1中的数据");
            }
        });


        /**
         * 测试跳转
         */
        findViewById(R.id.test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ARouterNavigation.Navigation(Router.component_one_mainactivity);

//                Bundle bundle = new Bundle();
//                bundle.putString("test","我是携带数据");
//                ARouterNavigation.Navigation(Router.component_one_mainactivity,bundle);


                ARouterNavigation.Navigation(MainActivity.this, Router.component_one_mainactivity, new ARouterNavigation.NavigationCallBack() {
                    @Override
                    public void onFound(Postcard postcard) {
                        LogUtils.e("--------onFound-------");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        LogUtils.e("--------onLost-------");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        LogUtils.e("--------onArrival-------");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        LogUtils.e("--------onInterrupt-------");
                    }
                });
            }
        });
    }
}
