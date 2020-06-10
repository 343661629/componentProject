package com.noahedu.mvp.contract;

import com.noahedu.basecomponent_common.base.IBaseView;

/**
 * @Description: 定义view和presenter接口
 * @Author: huangjialin
 * @CreateDate: 2020/6/10 9:15
 */
public class TestContract {

    /**
     * 接口之间可能有一些公共部分，比如说toast  showDialog  等等，可以抽取出来作为公共接口，具体的需要看自己的项目
     */
    public interface TestContractIView extends IBaseView {
        void testView(String test);
    }


    public interface TestContractIPresenter{
        void getNetWorkData();
    }


}
