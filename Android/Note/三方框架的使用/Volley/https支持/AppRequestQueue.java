package com.jinzheng.qhlh.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.jinzheng.qhlh.R;
import com.jinzheng.qhlh.app.App;

import org.json.JSONArray;

import java.io.InputStream;
import java.util.List;

/**
 * Created by chenpp on 2015/12/2.
 */
public class AppRequestQueue {
    public static final int RESPONSE_LIST = 0;
    public static final int RESPONSE_JSONARRAY = 1;

    private static AppRequestQueue appRequestQueue;
    public static Context applicationContext;
    private static RequestQueue netQueue;
    public static Handler handler = new NetworkHandler();

    public static com.android.volley.RequestQueue getNetQueue() {
        return netQueue;
    }

    private AppRequestQueue(Context context) {
        applicationContext = context;
        InputStream keyStore = App.applicationContext.getResources().openRawResource(R.raw.test1);
//        netQueue = Volley
//                .newRequestQueue(App.applicationContext,
//                        new HttpsClientStack(new SslHttpClient(keyStore, "111111", 8443)));
        netQueue = Volley
                .newRequestQueue(App.applicationContext,
                        new ExtHttpClientStack(new SslHttpClient(keyStore, "111111", 8443)));
        netQueue.start();
    }


    public static synchronized void postRequest(Request request) {
        if (netQueue == null) {
            if (applicationContext == null) {
                throw new RuntimeException("网络请求队列还没有被初始化，请先初始化");
            } else {
                initAppRequestQueue(applicationContext);
            }
        }
        netQueue.add(request);
    }

    public static void initAppRequestQueue(Context applicationContext) {
        if (applicationContext == null) throw new RuntimeException("application 为空");
        if (appRequestQueue == null || netQueue == null)
            appRequestQueue = new AppRequestQueue(applicationContext);
    }

    /**
     * 网络处理的handler
     */
    static class NetworkHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            //Object[0] listener
            //Object[1] code
            //Object[2] msg
            //Object[3] data
            //Object[4] response type
            Object[] objects = (Object[]) msg.obj;
            int responseType = (int) objects[4];
            int code = (int) objects[1];
            String errMsg = (String) objects[2];

            if (responseType == RESPONSE_LIST) {
                NetworkListener networkListener = (NetworkListener) objects[0];
                List list = (List) objects[3];
                if (networkListener != null) {
                    if (code == 0) {
                        networkListener.success(list);
                    } else networkListener.fail(code, errMsg);
                }
            } else {
                JsonArrayListener jsonArrayListener = (JsonArrayListener) objects[0];
                JSONArray jsonArray = (JSONArray) objects[3];
                if (jsonArrayListener != null) {
                    if (code == 0) {
                        jsonArrayListener.success(jsonArray);
                    } else jsonArrayListener.fail(code, errMsg);
                }
            }


        }
    }



}
