package com.meimengmeng.one.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meimengmeng.one.R;
import com.meimengmeng.one.api.ApiManager;
import com.meimengmeng.one.api.ApiRequest;
import com.meimengmeng.one.base.BaseActivity;
import com.meimengmeng.one.base.BaseApplication;
import com.meimengmeng.one.bean.Constant;
import com.meimengmeng.one.bean.DataBean;
import com.meimengmeng.one.bean.UABean;
import com.meimengmeng.one.ui.adapter.MyAdapter;
import com.meimengmeng.one.ui.widget.MDEditDialog;
import com.meimengmeng.one.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

import static com.meimengmeng.one.bean.Constant.mListUA;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView   mRecyclerView;
    @BindView(R.id.layout1)
    LinearLayout   mLayout1;
    @BindView(R.id.main_fragme_layout)
    FrameLayout    mMainFragmeLayout;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;
    @BindView(R.id.suiji)
    TextView       mSuiji;

    List<DataBean.UrlsEntity> mDatas = new ArrayList<>();

    //    private boolean flag = false;
    private MyAdapter    mAdapter;
    private MDEditDialog mMDEditDialog;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 5));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        initAdapter();

        if (mListUA == null || mListUA.size() == 0) {
            getUAData();
        }


    }


    public void getUAData() {

        Subscriber subscriber = new Subscriber<UABean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UABean bean) {
               /* if (Contants.OK.equals(bean.getResult())) {
                    onBaseCallBack.onSuccess(bean);
                } else {
                    onBaseCallBack.onFailed(bean.getMessage());
                }*/
                if ("ok".equals(bean.result)) {
                    onSuccessUA(bean);
                } else {
                    ToastUtil.showShort(MainActivity.this, "数据有误");
                }
            }
        };
        ApiRequest.getUAData(subscriber);

        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscriber);
    }

    //获取UA数据
    private void onSuccessUA(UABean bean) {
        if (bean.ualist != null) {
            mListUA.clear();
            mListUA.addAll(bean.ualist);

            clickRandom(false);
        }
    }


    //    private class ExampleWebViewClient extends WebViewClient {
    //        @Override
    //        public boolean shouldOverrideUrlLoading(WebView view, String url) {
    //            view.loadUrl(url);
    //            return true;
    //        }
    //      /*  @Override
    //        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
    //            handler.proceed();
    //        }
    //
    //
    //        @Override
    //        public void onPageFinished(WebView view, String url) {
    //            view.loadUrl("javascript: var allLinks = document.getElementsByTagName('a'); if (allLinks) {var i;for (i=0; i<allLinks.length; i++) {var link = allLinks[i];var target = link.getAttribute('target'); if (target && target == '_blank') {link.setAttribute('target','_self');link.href = 'newtab:'+link.href;}}}");
    //        }
    //
    //        @Override
    //        public void onPageStarted(WebView view, String url, Bitmap favicon) {
    //            super.onPageStarted(view, url, favicon);
    //        }
    //
    //        @Override
    //        public void onLoadResource(WebView view, String url) {
    //            super.onLoadResource(view, url);
    //        }
    //
    //
    //        @Override
    //        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
    //
    //            //            shouldOverrideUrlLoading(view, url);
    //            return super.shouldInterceptRequest(view, url);
    //        }*/
    //
    //    }


    private void initAdapter() {
        mAdapter = new MyAdapter(this, mDatas);
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                skipWebView(position);

            }
        });
        mRecyclerView.setAdapter(mAdapter);

    }

    //跳转显示webView
    private void skipWebView(int position) {

        //        flag = true;
        loadWebViewUrl(position);
        //        mLayout1.setVisibility(View.GONE);
        //        mLayout2.setVisibility(View.VISIBLE);


    }

    //加载webbview url
    private void loadWebViewUrl(int position) {
        if (mDatas.size() > position) {
            DataBean.UrlsEntity bean = mDatas.get(position);
            //            mWebView.loadUrl("about:blank");
            Intent intent = new Intent(MainActivity.this, SecondWebActivity.class);
            intent.putExtra("url", bean.url);
            startActivity(intent);

            /*Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(bean.url);
            intent.setData(content_url);
            startActivity(intent);*/

        }


    }

    @Override
    protected void loadData() {

    }

    private void getData() {

       /* RequestQueue mQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest("http://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", response);
                       *//* ResultModel lResultModel = JSON.parseObject(response, ResultModel.class);
                        List<DataBean> lGoodsInfoModels = JSON.parseArray(lResultModel.data, GoodsInfoModel.class);*//*

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(stringRequest);*/


        Subscriber subscriber = new Subscriber<DataBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DataBean bean) {
               /* if (Contants.OK.equals(bean.getResult())) {
                    onBaseCallBack.onSuccess(bean);
                } else {
                    onBaseCallBack.onFailed(bean.getMessage());
                }*/
                if ("ok".equals(bean.result)) {
                    onSuccess(bean);
                } else {
                    ToastUtil.showShort(MainActivity.this, "数据有误");
                }
            }
        };
        ApiRequest.getNetData(subscriber);

        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscriber);


    }

    //成功获取网络数据
    private void onSuccess(DataBean bean) {
        if (bean.urls != null) {
            mDatas.clear();
            mDatas.addAll(bean.urls);
        }
        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    public CompositeSubscription mCompositeSubscription;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
            mCompositeSubscription.unsubscribe();
    }


  /*  //返回首页
    private void backHome() {
//        if (flag) {
//            flag = false;
           *//* clearCookie();

            mLayout1.setVisibility(View.VISIBLE);
            mLayout2.setVisibility(View.GONE);*//*
            getData();

//        }
    }*/

/*

    //清除cookie
    private void clearCookie() {
        mWebView.clearHistory();
        mWebView.clearFormData();
        mWebView.clearCache(true);

        CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();
        cookieManager.removeAllCookie();
    }
*/



   /* @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        //        WebView scrollView = (WebView) findViewById(R.id.ch01);
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN) {
                    mWebView.pageUp(false);
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    mWebView.pageDown(false);
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }*/

  /*  @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            backHome();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();// 返回前一个页面
            } else {
                backHome();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/


    @OnClick(R.id.suiji)
    public void clickUA(View view) {
        clickRandom(true);

    }

    private void clickRandom(boolean flag) {
        if (mListUA != null && mListUA.size() > 0) {
            Random r = new Random();
            int j = mListUA.size();
            int i = r.nextInt(j);
            String s = mListUA.get(i).ua;
            Constant.UA = s;

            String pk = Constant.mListUA.get(i).pk;
            //            BaseApplication.getInstance().setPackageName(pk);

            BaseApplication.mPackageName = pk;

            mSuiji.setText("UA-" + i + "-pk-" + pk);
        } else {
            if (flag) {
                getUAData();
                ToastUtil.showShort(this, "拉取UA数据，请重试");
            }
        }
    }

    //点击更换域名
    @OnClick(R.id.change)
    public void clickChane(View view) {

        //                .setMaxLength(7)
        //                .setContentTextColor(R.color.colorPrimary)
        //                .setLeftButtonTextColor(R.color.colorPrimary)
        //                .setRightButtonTextColor(R.color.colorPrimary)
        //                .setLineColor(R.color.colorPrimary)
        mMDEditDialog = new MDEditDialog.Builder(this)
                .setTitleVisible(true)
                .setTitleText("更改域名")
                .setContentText("http://www.")
                //                .setMaxLength(7)
                .setHintText("请输入新域名")
                .setMaxLines(1)
                //                .setContentTextColor(R.color.colorPrimary)
                .setButtonTextSize(14)
                //                .setLeftButtonTextColor(R.color.colorPrimary)
                .setLeftButtonText("取消")
                //                .setRightButtonTextColor(R.color.colorPrimary)
                .setRightButtonText("确定")
                //                .setLineColor(R.color.colorPrimary)
                .setOnclickListener(new MDEditDialog.OnClickEditDialogListener() {
                    @Override
                    public void clickLeftButton(View view, String editText) {
                        mMDEditDialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(View view, String editText) {
                        if (TextUtils.isEmpty(editText)) {
                            return;
                        }
                        mMDEditDialog.dismiss();
                        if (!editText.startsWith("http://")) {
                            ApiManager.BASE_URL = "http://" + editText + "/";
                        } else {
                            ApiManager.BASE_URL = editText + "/";
                        }

                        getUAData();
                        getData();

                    }
                })
                .setMinHeight(0.2f)
                .setWidth(0.8f)
                .build();
        mMDEditDialog.show();

//        KeyboardUtil.showSoftInput(this,mMDEditDialog.getEditTextInstant());

    }

}

