package com.topxgun.appbase.net.retrofit;

import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    public final static long defaut_read_time = 10000;
    public final static long defaut_write_time = 10000;
    public final static long defaut_connect_time = 10000;

    private static OkHttpClient client;
    private static Map retrofitApis = new HashMap();
    private static Map<String,Retrofit> retrofits=new HashMap<String, Retrofit>();

    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .readTimeout(defaut_read_time, TimeUnit.MILLISECONDS)
                .writeTimeout(defaut_write_time, TimeUnit.MILLISECONDS)
                .connectTimeout(defaut_connect_time, TimeUnit.MILLISECONDS)
                .addInterceptor(logging)
                .build();
    }

    public static <T> T getRetrofitApi(Class<T> tClass,String... url) {
        Retrofit retrofit=null;
        if(url!=null&&url.length>0&& !TextUtils.isEmpty(url[0])){
            if(retrofits.containsKey(tClass.getName())){
                retrofit=retrofits.get(tClass.getName());
            }else{
                retrofit = new Retrofit.Builder()
                        .baseUrl(url[0])
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(client)
                        .build();
                retrofits.put(tClass.getName(),retrofit);
            }
        }else{
            retrofit=retrofits.get(tClass.getName());
        }
        if(retrofit==null){
            return null;
        }else if (retrofitApis.containsKey(tClass.getName())) {
            return tClass.cast(retrofitApis.get(tClass.getName()));
        } else {
            retrofitApis.put(tClass.getName(), retrofit.create(tClass));
            return tClass.cast(retrofitApis.get(tClass.getName()));
        }
    }

}
