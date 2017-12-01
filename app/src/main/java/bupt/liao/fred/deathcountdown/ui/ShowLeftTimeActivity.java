package bupt.liao.fred.deathcountdown.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import bupt.liao.fred.deathcountdown.PrefUtils;
import bupt.liao.fred.deathcountdown.R;
import bupt.liao.fred.deathcountdown.StringUtil;
import cn.droidlover.xdroid.base.XActivity;

/**
 * Created by Fred.Liao on 2017/11/29.
 * Email:fredliaobupt@qq.com
 * Description:
 */

public class ShowLeftTimeActivity extends XActivity{

    String birth;
    String death;
    Date birthDate = null;
    Date deathDate;
    TextView textView;
    TimeHandler timeHandler = new TimeHandler();
    Calendar birthCalendar = Calendar.getInstance();
    Calendar currentCalendar = Calendar.getInstance();
    Calendar deathCalendar = Calendar.getInstance();

    public static void startAction(Context context){
        Intent intent = new Intent(context, ShowLeftTimeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        textView = (TextView)findViewById(R.id.text);
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        this.birth = PrefUtils.getInstance().getString(StringUtil.BIRTH);
        this.death = PrefUtils.getInstance().getString(StringUtil.DEATH);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
        try {
            deathDate = dateFormat.parse(death);
        }catch (ParseException e){
            Log.e("ShowLeftTimeActivity", "Can't parse date");
        }
        if(deathDate != null){
            deathCalendar.setTime(deathDate);
        }
        Timer timer = new Timer();
        timer.schedule(new MyTimeTask(), 0, 1000);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_showlefttime;
    }


    class TimeHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            textView.setText((long)((deathCalendar.getTimeInMillis() - System.currentTimeMillis())/(1000))+ "");
        }
    }

    class MyTimeTask extends TimerTask{

        @Override
        public void run() {
            timeHandler.sendEmptyMessage(0);
        }
    }
}
