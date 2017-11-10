package com.zhao.shopapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhao.shopapp.activity.base.BaseActivity;
import com.zhao.shopapp.fragment.ClazzFragment;
import com.zhao.shopapp.fragment.HomeFragment;
import com.zhao.shopapp.fragment.HotFragment;
import com.zhao.shopapp.fragment.MineFragment;
import com.zhao.shopapp.util.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.frame)
    FrameLayout frame;
    @Bind(R.id.iv_home)
    ImageView ivHome;
    @Bind(R.id.tv_home)
    TextView tvHome;
    @Bind(R.id.iv_hot)
    ImageView ivHot;
    @Bind(R.id.tv_hot)
    TextView tvHot;
    @Bind(R.id.iv_clazz)
    ImageView ivClazz;
    @Bind(R.id.tv_clazz)
    TextView tvClazz;
    @Bind(R.id.iv_mine)
    ImageView ivMine;
    @Bind(R.id.tv_mine)
    TextView tvMine;

    private HomeFragment homeFragment;
    private HotFragment hotFragment;
    private ClazzFragment clazzFragment;
    private MineFragment mineFragment;

    private FragmentTransaction fragmentTransaction;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        showSelectIconAndTextColor(R.id.ll_home);
        showFragmentById(R.id.ll_home);
    }

    @Override
    protected void initTitle() {
    }

    /**
     * 底部栏点击事件
     *
     * @param view
     */
    @OnClick({R.id.ll_home, R.id.ll_hot, R.id.ll_class, R.id.ll_mine})
    public void onClick(View view) {
        int id = view.getId();
        showSelectIconAndTextColor(id);
        showFragmentById(id);
    }

    /**
     * 根据用户选中显示对应的frament
     * @param id
     */
    private void showFragmentById(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        hideAllFragment();
        switch (id) {
            case R.id.ll_home:
                if(homeFragment==null)
                {
                    homeFragment=new HomeFragment();
                    fragmentTransaction.add(R.id.frame,homeFragment,"homeFragment");
                }
                fragmentTransaction.show(homeFragment);
                break;
            case R.id.ll_hot:
                if(hotFragment==null)
                {
                    hotFragment=new HotFragment();
                    fragmentTransaction.add(R.id.frame,hotFragment,"hotFragment");
                }
                fragmentTransaction.show(hotFragment);
                break;
            case R.id.ll_class:
                if(clazzFragment==null)
                {
                    clazzFragment=new ClazzFragment();
                    fragmentTransaction.add(R.id.frame,clazzFragment,"clazzFragment");
                }
                fragmentTransaction.show(clazzFragment);
                break;
            case R.id.ll_mine:
                if(mineFragment==null)
                {
                    mineFragment=new MineFragment();
                    fragmentTransaction.add(R.id.frame,mineFragment,"mineFragment");
                }
                fragmentTransaction.show(mineFragment);
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 显示用户选中的图标
     *
     * @param id
     */
    private void showSelectIconAndTextColor(int id) {
        //图片切换
        ivHome.setImageResource(id == R.id.ll_home ? R.drawable.bid01 : R.drawable.bid02);
        ivHot.setImageResource(id == R.id.ll_hot ? R.drawable.bid03 : R.drawable.bid04);
        ivClazz.setImageResource(id == R.id.ll_class ? R.drawable.bid07 : R.drawable.bid08);
        ivMine.setImageResource(id == R.id.ll_mine ? R.drawable.bid05 : R.drawable.bid06);

        //文字切换
        tvHome.setTextColor(id == R.id.ll_home ? UIUtils.getColor(R.color.bottom_back_selected) : UIUtils.getColor(R.color.bottom_back_unselected));
        tvHot.setTextColor(id == R.id.ll_hot ? UIUtils.getColor(R.color.bottom_back_selected) : UIUtils.getColor(R.color.bottom_back_unselected));
        tvClazz.setTextColor(id == R.id.ll_class ? UIUtils.getColor(R.color.bottom_back_selected) : UIUtils.getColor(R.color.bottom_back_unselected));
        tvMine.setTextColor(id == R.id.ll_mine ? UIUtils.getColor(R.color.bottom_back_selected) : UIUtils.getColor(R.color.bottom_back_unselected));
    }

    /**
     * 隐藏所有fragment
     */
    private void hideAllFragment() {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (hotFragment != null) {
            fragmentTransaction.hide(hotFragment);
        }
        if (clazzFragment != null) {
            fragmentTransaction.hide(clazzFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }
}
