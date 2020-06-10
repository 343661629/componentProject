package com.noahedu.mvp.model;

import android.os.Handler;

import com.noahedu.basecomponent_common.LogUtils;
import com.noahedu.basecomponent_common.base.CallBack;
import com.noahedu.basecomponent_common.base.IBaseModel;

/**
 * @Description: java类作用描述
 * @Author: huangjialin
 * @CreateDate: 2020/6/9 17:31
 */
public class TestModel extends IBaseModel {

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
