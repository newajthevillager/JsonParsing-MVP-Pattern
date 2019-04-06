package example.com.jsonparsing.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MyAppUtil {

    public Context context;

    public MyAppUtil(Context context) {
        this.context = context;
    }

    /**
     * CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT
     */
    public boolean checkConnection() {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) { // connected to the internet

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;

    }

}
