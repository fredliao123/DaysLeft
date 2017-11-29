package bupt.liao.fred.deathcountdown;

import android.app.Application;
import android.content.Context;

/**
 * Created by Fred.Liao on 2017/11/20.
 * Email:fredliaobupt@qq.com
 * Description:
 */

public class App extends Application{
    private static Context context;
    @Override
    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){return context;}
}
