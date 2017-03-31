package com.meimengmeng.one.api;

import com.meimengmeng.one.bean.DataBean;
import com.meimengmeng.one.bean.UABean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by leijiaxq
 * Data       2016/12/20 11:07
 * Describe
 */
public interface ApiService {

    //   获取新数据
    @GET("JL/index.jsp")
    Observable<DataBean> getNetData();
    //   获取新数据
    @GET("JL/getAllUserAgent.jsp")
    Observable<UABean> getUAData();

}
