package com.zhao.shopapp.activity;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.zhao.shopapp.R;
import com.zhao.shopapp.activity.base.BaseActivity;

import java.util.List;

import butterknife.Bind;

import static com.andrognito.patternlockview.PatternLockView.PatternViewMode.CORRECT;
import static com.andrognito.patternlockview.PatternLockView.PatternViewMode.WRONG;

public class LockActivity extends BaseActivity {
    @Bind(R.id.patter_lock_view)
    PatternLockView patterLockView;

    private String correct="012345678";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lock;
    }

    @Override
    protected void initData() {
        patterLockView.addPatternLockListener(mPatternLockViewListener);
    }

    @Override
    protected void initTitle() {
    }

    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {

        @Override
        public void onStarted() {
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            String result=PatternLockUtils.patternToString(patterLockView, pattern);
            if(correct.equals(result))
            {
                patterLockView.setViewMode(CORRECT);
            }else
            {
                patterLockView.setViewMode(WRONG);
            }
        }

        @Override
        public void onCleared() {
            patterLockView.clearPattern();
        }
    };
}
