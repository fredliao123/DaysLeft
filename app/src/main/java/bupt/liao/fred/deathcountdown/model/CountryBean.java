package bupt.liao.fred.deathcountdown.model;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by Fred.Liao on 2017/11/28.
 * Email:fredliaobupt@qq.com
 * Description:
 */

public class CountryBean implements IPickerViewData {
    private int id;
    private String country;
    private float age;

    public CountryBean(int id, String country, float age){
        this.id = id;
        this.country = country;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    @Override
    public String getPickerViewText() {
        return country;
    }
}
