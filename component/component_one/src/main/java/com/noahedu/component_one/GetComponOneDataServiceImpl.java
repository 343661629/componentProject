package com.noahedu.component_one;

import android.text.TextUtils;

import com.noahedu.customservice.IGetComponOneDataService;

/**
 * @Description: 实现对应的接口
 * @Author: huangjialin
 * @CreateDate: 2020/6/6 15:47
 */
public class GetComponOneDataServiceImpl implements IGetComponOneDataService {
    private String string = "暂无数据";

    @Override
    public void setData(String str) {
        if(!TextUtils.isEmpty(str)){
            this.string = str;
        }
    }

    @Override
    public String getData() {
        return string;
    }
}
