package com.noahedu.service;

import android.util.Log;

/**
 * @Description: java类作用描述
 * @Author: huangjialin
 * @CreateDate: 2020/6/6 15:10
 */
public class LoginServiceImpl implements LoginService {


    @Override
    public void isLogin(String isLogin) {
        Log.e("LoginServiceImpl","------------------->" + isLogin);
    }
}
