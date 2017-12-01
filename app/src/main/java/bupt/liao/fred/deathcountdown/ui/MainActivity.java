package bupt.liao.fred.deathcountdown.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import bupt.liao.fred.deathcountdown.PrefUtils;
import bupt.liao.fred.deathcountdown.R;
import bupt.liao.fred.deathcountdown.StringUtil;
import bupt.liao.fred.flipviewlibrary.FlipView;
import cn.droidlover.xdroid.base.XActivity;

public class MainActivity extends XActivity {
    private static final String TAG = "MainActivity";

    private static final String BIRTH = "birth";

    TextView text;

    FlipView flipView;

    @Override
    public void initData(Bundle savedInstanceState) {
        flipView = (FlipView)findViewById(R.id.flipview);
        //当计时结束时，跳转至主界面
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                flipView.getAnimatorSet().addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if(PrefUtils.getInstance().getString(StringUtil.BIRTH).equals(StringUtil.DEFAULT_STRING)||
                                PrefUtils.getInstance().getFloat(StringUtil.AGE) == 0f ||
                                PrefUtils.getInstance().getString(StringUtil.DEATH).equals(StringUtil.DEFAULT_STRING)) {
                            startActivity(new Intent(MainActivity.this, SelectActivity.class));
                        }else{
                            startActivity(new Intent(MainActivity.this, ShowLeftTimeActivity.class));
                        }
                            MainActivity.this.finish();

                    }
                });
                flipView.startFlip();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


}

