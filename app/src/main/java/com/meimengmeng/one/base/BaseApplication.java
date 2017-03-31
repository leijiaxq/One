package com.meimengmeng.one.base;

import android.app.Application;

import static com.tencent.smtt.sdk.QbSdk.initX5Environment;


/**
 * Created by leijiaxq
 * Data       2016/12/20 11:20
 * Describe   应用程序的Application
 */


public class BaseApplication extends Application {

    public static BaseApplication mApplication;

    public static String mPackageName = "";

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
//        mMainHandler = new Handler();

        initX5Environment(this, null);
    }

    public static BaseApplication getInstance() {
        return mApplication;
    }

    @Override
    public String getPackageName() {
        return mPackageName;
    }

    public void setPackageName(String string) {
        mPackageName = string;
    }


    //    public static Handler getMainHandler() {
//        return mMainHandler;
//    }


}
