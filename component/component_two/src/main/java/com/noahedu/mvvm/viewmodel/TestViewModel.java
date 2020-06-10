package com.noahedu.mvvm.viewmodel;

import com.noahedu.basecomponent_common.LogUtils;
import com.noahedu.basecomponent_common.base.BaseViewModel;
import com.noahedu.basecomponent_common.base.CallBack;
import com.noahedu.mvvm.model.TestmvvpModel;

import androidx.lifecycle.MutableLiveData;

/**
 * @Description: 作用和MVP中的presenter类似
 * @Author: huangjialin
 * @CreateDate: 2020/6/10 14:10
 */
public class TestViewModel extends BaseViewModel {
    private MutableLiveData<String> testMutableLivdData = new MutableLiveData<>();
    private TestmvvpModel mModel;

    public TestViewModel() {
        LogUtils.e("TestViewModel被初始化了");
        mModel = new TestmvvpModel();
    }

    public MutableLiveData<String> getTestMutableLivdData(){
        if(null == testMutableLivdData){
            testMutableLivdData = new MutableLiveData<>();
        }
        return testMutableLivdData;
    }


    public void requestNetWokData(){
        mModel.getNetWorkData(new CallBack() {
            @Override
            public void successCallBack(Object data) {
                testMutableLivdData.postValue((String) data);
            }

            @Override
            public void faildCallBack() {

            }
        });
    }








}
