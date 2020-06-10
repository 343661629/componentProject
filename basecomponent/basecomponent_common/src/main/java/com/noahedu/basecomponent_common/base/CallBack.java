package com.noahedu.basecomponent_common.base;

/**
 * @Description: java类作用描述
 * @Author: huangjialin
 * @CreateDate: 2020/6/9 18:12
 */
public interface CallBack<T> {

    void successCallBack(T data);

    void faildCallBack();

}
