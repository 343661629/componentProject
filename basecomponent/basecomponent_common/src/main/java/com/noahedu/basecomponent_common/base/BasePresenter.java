package com.noahedu.basecomponent_common.base;

import com.noahedu.basecomponent_common.LogUtils;

/**
 * @Description: 所有的Presenter的父类
 * @Author: huangjialin
 * @CreateDate: 2020/6/9 17:48
 */
public class BasePresenter<V extends IBaseView> implements IBasePresenter {
    public V mView;


    public BasePresenter(V view) {
        this.mView = view;
        createPresenter();
    }

    @Override
    public void createPresenter() {
        LogUtils.e("Presenter创建");
    }

    @Override
    public void destoryPresenter() {
        LogUtils.e("Presenter销毁");
    }
}
