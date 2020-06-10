package com.noahedu.mvp.presenter;

import com.noahedu.basecomponent_common.LogUtils;
import com.noahedu.basecomponent_common.base.BasePresenter;
import com.noahedu.basecomponent_common.base.CallBack;
import com.noahedu.basecomponent_common.base.IBaseView;
import com.noahedu.mvp.contract.TestContract;
import com.noahedu.mvp.model.TestModel;

/**
 * @Description: 测试Presenter
 * @Author: huangjialin
 * @CreateDate: 2020/6/9 17:32
 */
public class TestPresenter extends BasePresenter<TestContract.TestContractIView> implements TestContract.TestContractIPresenter {
    private TestModel mModel;

    public TestPresenter(TestContract.TestContractIView view) {
        super(view);
        mModel = new TestModel();
    }


    /**
     * 模拟获取网络数据  注意网络请求不能在Presenter中进行  需要在Model中进行获取
     */
    @Override
    public void getNetWorkData() {
        mModel.getNetWorkData(new CallBack() {
            @Override
            public void successCallBack(Object data) {
                mView.testView("我是黄家磷，我是从服务器过来的");
                mView.showToast("test","----------我是黄家磷，我是从服务器过来的--------------");
            }

            @Override
            public void faildCallBack() {

            }
        });

    }


}
