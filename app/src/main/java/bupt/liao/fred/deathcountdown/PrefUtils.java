package bupt.liao.fred.deathcountdown;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Fred.Liao on 2017/11/28.
 * Email:fredliaobupt@qq.com
 * Description:
 */

public class PrefUtils {
    private static PrefUtils instance = null;

    private static final String TAG = "pref";


    private PrefUtils(){

    }

    public static PrefUtils getInstance(){
        if(instance == null){
            instance = new PrefUtils();
        }
        return instance;
    }

    public SharedPreferences getSharedPreference(){
        return App.getContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getEditor(){
        return getSharedPreference().edit();
    }

    public void putString(String key, String value){
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key, value);
        editor.commit();
    }

    public void putFloat(String key, float value){
        SharedPreferences.Editor editor = getEditor();
        editor.putFloat(key, value);
        editor.commit();
    }

    public String getString(String key){
        SharedPreferences preferences = getSharedPreference();
        return preferences.getString(key, StringUtil.DEFAULT_STRING);
    }

    public float getFloat(String key){
        SharedPreferences preferences = getSharedPreference();
        return preferences.getFloat(key, 0f);
    }

}
