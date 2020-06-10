package com.noahedu.basecomponent_common.base;

/**
 * @Description: view的公共接口类
 * @Author: huangjialin
 * @CreateDate: 2020/6/10 9:37
 */
public interface IBaseView {

    /**
     * 通过tag来区分toast内容
     * @param tag
     * @param content
     */
    void showToast(String tag,String content);


    /**
     * 显示弹框
     * @param tag
     * @param object
     */
    void showDialog(String tag,Object object);

}
