package com.zhao.shopapp.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.RequestParams;
import com.zhao.shopapp.util.UIUtils;
import com.zhao.shopapp.view.LoadingLayout;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    private LoadingLayout loadingLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingLayout = new LoadingLayout(UIUtils.getContext()) {

            @Override
            public void onSuccess(ResultState resultState, View SuccessView) {
                ButterKnife.bind(BaseFragment.this,SuccessView);
                initTitle();
                initData(resultState.getContent());
            }

            @Override
            public RequestParams params() {
                return getParams();
            }

            @Override
            public int LayoutId() {
                return getLayoutId();
            }

            @Override
            public String url() {
                return getUrl();
            }
        };
        return loadingLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                show();
            }
        }, 1000);
    }

    protected abstract int getLayoutId();

    protected abstract void initData(String content);

    protected abstract void initTitle();

    protected abstract String getUrl();

    protected abstract RequestParams getParams();

    public void show()
    {
        loadingLayout.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
