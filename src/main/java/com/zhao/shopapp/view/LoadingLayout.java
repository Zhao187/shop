package com.zhao.shopapp.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zhao.shopapp.R;
import com.zhao.shopapp.util.UIUtils;

import static com.zhao.shopapp.view.LoadingLayout.ResultState.EMPTY;
import static com.zhao.shopapp.view.LoadingLayout.ResultState.SUCCESS;

/**
 * 加载页面
 */

public abstract class LoadingLayout extends FrameLayout {
    AsyncHttpClient client=new AsyncHttpClient();

    private static final int PAGE_LOADING_STATE=1;
    private static final int PAGE_EMPTY_STATE=2;
    private static final int PAGE_ERROR_STATE=3;
    private static final int PAGE_SUCCESS_STATE=4;

    private static int PAGE_CURRENT_STATE=1;

    private ResultState resultState=null;
    //正在加载页面
    private View laodingView;
    //空数据的页面
    private View emptyView;
    //加载失败的页面
    private View errorView;
    //加载成功的页面
    private View successView;

    public LoadingLayout(@NonNull Context context) {
        this(context,null);
    }

    public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        if(laodingView==null)
        {
            laodingView= UIUtils.getXMLView(R.layout.page_loading);
            addView(laodingView);
        }
        if(emptyView==null)
        {
            emptyView= UIUtils.getXMLView(R.layout.page_empty);
            addView(emptyView);
        }
        if(errorView==null)
        {
            errorView= UIUtils.getXMLView(R.layout.page_error);
            addView(errorView);
        }
        if(successView==null)
        {
            successView=UIUtils.getXMLView(LayoutId());
            addView(successView);
        }
        showSafePage();
    }

    private void showSafePage() {
        UIUtils.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }

    private void showPage() {
        laodingView.setVisibility(PAGE_CURRENT_STATE==PAGE_LOADING_STATE?VISIBLE:INVISIBLE);
        emptyView.setVisibility(PAGE_CURRENT_STATE==PAGE_EMPTY_STATE?VISIBLE:INVISIBLE);
        errorView.setVisibility(PAGE_CURRENT_STATE==PAGE_ERROR_STATE?VISIBLE:INVISIBLE);
        successView.setVisibility(PAGE_CURRENT_STATE==PAGE_SUCCESS_STATE?VISIBLE:INVISIBLE);
    }

    /**
     * 获取网络数据
     */
    public void show() {
        if(PAGE_CURRENT_STATE!=PAGE_LOADING_STATE)
        {
            PAGE_CURRENT_STATE=PAGE_LOADING_STATE;
        }

        String url=url();
        if(url.isEmpty())
        {
            resultState= SUCCESS;
            resultState.content="";
            loadPage();
        }else
        {
            client.get(url,params(),new AsyncHttpResponseHandler(){
                @Override
                public void onSuccess(String content) {
                    if(content.isEmpty())
                    {
                        resultState= EMPTY;
                        resultState.content="";
                        loadPage();
                    }
                    else
                    {
                        resultState= SUCCESS;
                        resultState.content=content;
                        loadPage();
                    }
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    resultState=ResultState.ERROR;
                    resultState.content="";
                }
            });
        }
    }

    private void loadPage() {
        switch (resultState)
        {
            case EMPTY:
                PAGE_CURRENT_STATE=PAGE_EMPTY_STATE;
                break;
            case ERROR:
                PAGE_CURRENT_STATE=PAGE_ERROR_STATE;
                break;
            case SUCCESS:
                PAGE_CURRENT_STATE=PAGE_SUCCESS_STATE;
                break;
        }
        showSafePage();
        if(PAGE_CURRENT_STATE==PAGE_SUCCESS_STATE)
        {
            onSuccess(resultState,successView);
        }
    }


    /*
     状态结果的枚举
     */
    public enum ResultState{
        ERROR(2),EMPTY(3),SUCCESS(4);
        private int state;
        private String content;

        ResultState(int state)
        {
            this.state=state;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public abstract void onSuccess(ResultState resultState,View SuccessView);
    public abstract RequestParams params();
    public abstract int LayoutId();
    public abstract String url();
}
