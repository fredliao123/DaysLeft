package bupt.liao.fred.deathcountdown.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;

import bupt.liao.fred.deathcountdown.R;
import bupt.liao.fred.deathcountdown.model.CountryBean;
import cn.droidlover.xdroid.base.XActivity;

/**
 * Created by Fred.Liao on 2017/11/28.
 * Email:fredliaobupt@qq.com
 * Description:
 */

public class CountryActivity extends XActivity {
    ArrayList<CountryBean> list = new ArrayList<>();

    TextView text;

    {
        list.add(new CountryBean(0, "China", 85.1));
        list.add(new CountryBean(1, "USA", 90.1));
        list.add(new CountryBean(2, "Australia", 93.1));
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        text = (TextView) this.findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        Double age = list.get(options1).getAge();
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
        return R.layout.activity_country;
    }
}
