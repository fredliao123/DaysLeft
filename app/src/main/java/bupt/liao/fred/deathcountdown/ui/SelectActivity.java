package bupt.liao.fred.deathcountdown.ui;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import bupt.liao.fred.deathcountdown.PrefUtils;
import bupt.liao.fred.deathcountdown.R;
import bupt.liao.fred.deathcountdown.StringUtil;
import bupt.liao.fred.deathcountdown.model.CountryBean;
import cn.droidlover.xdroid.base.XActivity;

import static bupt.liao.fred.deathcountdown.StringUtil.DEATH;

/**
 * Created by Fred.Liao on 2017/11/28.
 * Email:fredliaobupt@qq.com
 * Description:
 */

public class SelectActivity extends XActivity {
    ArrayList<CountryBean> list = new ArrayList<>();

    TextView text;
    TextView estimate;
    TextView age;
    Handler handler = new Handler();

    {
        list.add(new CountryBean(0, "China", 85.1f));
        list.add(new CountryBean(1, "USA", 90.1f));
        list.add(new CountryBean(2, "Australia", 93.1f));
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        text = (TextView) this.findViewById(R.id.text);
        estimate = (TextView) this.findViewById(R.id.estimate);
        age = (TextView) this.findViewById(R.id.age);
        selectBirthday();
    }


    private void selectBirthday() {
        if(!PrefUtils.getInstance().getString(StringUtil.BIRTH).equals(StringUtil.DEFAULT_STRING)){
            selectCountry();
            return;
        }
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerView pvTime = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        PrefUtils.getInstance().putString(StringUtil.BIRTH, getTime(date));
                        selectCountry();

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

    private void selectCountry() {
        if(PrefUtils.getInstance().getFloat(StringUtil.AGE) != 0f){
            return;
        }
        text.setText("Your Country");
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        final float estimateAge = list.get(options1).getAge();
                        PrefUtils.getInstance().putFloat(StringUtil.AGE, estimateAge);
                        text.setVisibility(View.GONE);
                        estimate.setVisibility(View.VISIBLE);
                        age.setVisibility(View.VISIBLE);
                        age.setText(String.valueOf(estimateAge));
                        PrefUtils.getInstance().putString(DEATH, getDeathTime());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ShowLeftTimeActivity.startAction(context);

                            }
                        }, 2000);
                    }
                }).isDialog(true)
                        .build();
                pvOptions.setPicker(list);
                pvOptions.show();
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_select;
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    private String getDeathTime(){
        String birthDay = PrefUtils.getInstance().getString(StringUtil.BIRTH);
        float age = PrefUtils.getInstance().getFloat(StringUtil.AGE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar birthCalendar = Calendar.getInstance();
        Date birthDate = null;
        try {
            birthDate = dateFormat.parse(birthDay);
        }catch (ParseException e){
            Log.e("SelectActivity", "Can't parse date");
        }
        if(birthDate != null){
            birthCalendar.setTime(birthDate);
        }
        birthCalendar.add(Calendar.YEAR, (int)age);
        double month = age - Math.floor(age);
        birthCalendar.add(Calendar.MONTH, (int)(12*month));
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(birthCalendar.getTime());
    }
}
