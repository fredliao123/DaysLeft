package bupt.liao.fred.deathcountdown.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bupt.liao.fred.deathcountdown.PrefUtils;
import bupt.liao.fred.deathcountdown.R;
import cn.droidlover.xdroid.base.XActivity;

public class MainActivity extends XActivity {
    private static final String TAG = "MainActivity";

    private static final String BIRTH = "birth";

    TextView text;

    @Override
    public void initData(Bundle savedInstanceState) {
        text = (TextView)this.findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerView pvTime = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        Log.d(TAG, getTime(date));
                        PrefUtils.getInstance().putString("birth", getTime(date));
                        Intent intent = new Intent(context, CountryActivity.class);
                        context.startActivity(intent);

                    }
                })
                        .isDialog(true)
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}

