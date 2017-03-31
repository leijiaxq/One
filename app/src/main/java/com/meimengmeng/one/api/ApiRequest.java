package com.meimengmeng.one.api;

import com.meimengmeng.one.bean.DataBean;
import com.meimengmeng.one.bean.UABean;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by leijiaxq
 * Data       2016/12/20 11:07
 * Describe
 */


public class ApiRequest {


    /**
     * 通用的请求设置
     *
     * @return
     */
    private static ApiService ApiRequest() {
        return ApiManager.getInstance().initRetrofit().create(ApiService.class);
    }


    //    获取红包列表数据
    public static void getNetData(Subscriber<DataBean> subscriber) {
        ApiRequest().getNetData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    //    获取红包列表数据
    public static void getUAData(Subscriber<UABean> subscriber) {
        ApiRequest().getUAData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
