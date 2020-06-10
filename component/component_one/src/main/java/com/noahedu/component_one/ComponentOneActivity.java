package com.noahedu.component_one;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.noahedu.customservice.ServiceFactory;
import com.noahedu.router.ARouterNavigation;
import com.noahedu.router.Router;

@Route(path = Router.component_one_mainactivity)
public class ComponentOneActivity extends AppCompatActivity {
    private GetComponOneDataServiceImpl getComponOneDataService;
//    @Autowired
//    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component_one);
        ARouter.getInstance().inject(this);

        findViewById(R.id.mvp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouterNavigation.Navigation(Router.component_one_testactivity);
            }
        });


        findViewById(R.id.mvvm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouterNavigation.Navigation(Router.component_two_MvvmActivity);
            }
        });


//        String test = bundle.getString("test");
//        TextView textView = findViewById(R.id.testData);
//        textView.setText(test);



        getComponOneDataService = new GetComponOneDataServiceImpl();
        getComponOneDataService.setData("没见过靓仔吗");
        ServiceFactory.getInstance().setService(getComponOneDataService);


    }
}
