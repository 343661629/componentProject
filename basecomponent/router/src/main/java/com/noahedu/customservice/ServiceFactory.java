package com.noahedu.customservice;

/**
 * @Description: java类作用描述
 * @Author: huangjialin
 * @CreateDate: 2020/6/6 15:33
 */
public class ServiceFactory {
    private static ServiceFactory instace = null;
    private IGetComponOneDataService iGetComponOneDataService;

    public static ServiceFactory getInstance(){
        if(null == instace){
            synchronized (ServiceFactory.class){
                if(null == instace){
                    instace = new ServiceFactory();
                }
            }
        }
        return instace;
    }

    public void setService(IGetComponOneDataService service){
        this.iGetComponOneDataService = service;
    }


    public IGetComponOneDataService getiGetComponOneDataService(){
        return iGetComponOneDataService;
    }





}
