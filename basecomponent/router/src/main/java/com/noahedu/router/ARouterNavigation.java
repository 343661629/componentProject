package com.noahedu.router;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import androidx.fragment.app.Fragment;

/**
 * @Description: 路由跳转类
 * @Author: huangjialin
 * @CreateDate: 2020/6/5 15:56
 */
public class ARouterNavigation {

    /**
     * 普通跳转
     *
     * @param path
     */
    public static void Navigation(String path) {
        ARouter.getInstance()
                .build(path)
                .navigation();
    }

    /**
     * 携带参数跳转
     */
    public static void Navigation(String path, Bundle bundle) {
        ARouter.getInstance()
                .build(path)
                .withBundle("bundle", bundle)
                .navigation();
    }

    /**
     * 有返回结果的跳转 和Android startActivityForResult 一样
     */
    public static void Navigation(String path, int resultCode) {
        ARouter.getInstance()
                .build(path)
                .withFlags(resultCode)
                .navigation();
    }

    /**
     * 跳转携带参数，并有返回结果的跳转 和Android startActivityForResult 一样
     */
    public static void Navigation(String path, Bundle bundle, int resultCode) {
        ARouter.getInstance()
                .build(path)
                .with(bundle)
                .withFlags(resultCode)
                .navigation();
    }

    /**
     * 获取其他组件中的fragment
     */
    public static Fragment NavigationGetFragment(String path) {
        Fragment fragment = (Fragment) ARouter.getInstance().build(path).navigation();
        return fragment;
    }


    /**
     * 监听跳转结果
     *
     * @param mContext
     * @param path
     * @param callBack
     */
    public static void Navigation(Context mContext, String path, final NavigationCallBack callBack) {
        ARouter.getInstance().build(path).navigation(mContext, new NavCallback() {
            @Override
            public void onFound(Postcard postcard) { //创建
                callBack.onFound(postcard);
            }

            @Override
            public void onLost(Postcard postcard) { //丢失
                callBack.onLost(postcard);
            }

            @Override
            public void onArrival(Postcard postcard) { //成功
                callBack.onArrival(postcard);
            }

            @Override
            public void onInterrupt(Postcard postcard) { //拦截   需要添加拦截器
                callBack.onInterrupt(postcard);
            }
        });
    }


    public interface NavigationCallBack {
        /**
         * 找到页面
         *
         * @param postcard
         */
        void onFound(Postcard postcard);

        /**
         * 跳转失败-- 大多是没有找到该页面
         *
         * @param postcard
         */
        void onLost(Postcard postcard);

        /**
         * 跳转结束 一般可以在该方法中Finnish上一个页面
         *
         * @param postcard
         */
        void onArrival(Postcard postcard);

        /**
         * 拦截 一般在跳转前比如需要判断是否登录
         *
         * @param postcard
         */
        void onInterrupt(Postcard postcard);


    }


}
