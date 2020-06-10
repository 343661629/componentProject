package com.noahedu.basecomponent_common.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * @Description: 所有activity父类
 * @Author: huangjialin
 * @CreateDate: 2020/6/9 17:38
 */
public abstract class BaseActivity<P extends IBasePresenter> extends FragmentActivity {
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
    }

    /**
     * 通过抽象方法给子类返回对应的Presenter对象
     * @return
     */
    protected abstract P getPresenter();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
