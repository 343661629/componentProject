package com.noahedu.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.noahedu.basecomponent_common.LogUtils;
import com.noahedu.basecomponent_common.base.BaseActivity;
import com.noahedu.component_one.R;
import com.noahedu.mvp.contract.TestContract;
import com.noahedu.mvp.presenter.TestPresenter;
import com.noahedu.router.Router;

/**
 * @Description: 测试mvp
 * @Author: huangjialin
 * @CreateDate: 2020/6/10 9:15
 */
@Route(path = Router.component_one_testactivity)
public class TestActivity extends BaseActivity<TestPresenter> implements TestContract.TestContractIView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getNetWorkData();
            }
        });
    }

    @Override
    protected TestPresenter getPresenter() {
        return new TestPresenter(this);
    }

    @Override
    public void testView(String test) {
        LogUtils.e("-----------test--------> ");
    }

    @Override
    public void showToast(String tag, String content) {
        switch (tag) {
            case "test":
                Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void showDialog(String tag, Object object) {

    }
}
