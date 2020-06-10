package com.noahedu.mvvm.model;

import android.os.Handler;

import com.noahedu.basecomponent_common.LogUtils;
import com.noahedu.basecomponent_common.base.CallBack;

/**
 * @Description: 模拟从网络获取数据
 * @Author: huangjialin
 * @CreateDate: 2020/6/10 14:49
 */
public class TestmvvpModel {

    public void getNetWorkData(final CallBack callBack){
        LogUtils.e("开始请求数据");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callBack.successCallBack("我是从服务器中获取的数据");
            }
        },3000);

    }


}
