package com.topxgun.appbase.dao.spf;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;


public class ConfigSPF {

    private Map<String, SharedPreferences> spfMap = new HashMap<String, SharedPreferences>();
    private Context ctx;
    private static ConfigSPF configSPF;

    public final String NAME_DEFAULT = "SPF_DEFAULT";

    public static ConfigSPF getInstance(Context context) {
        if (configSPF == null) {
            synchronized (ConfigSPF.class) {
                configSPF = new ConfigSPF();
            }
        }
        configSPF.ctx=context;
        return configSPF;
    }

    public SharedPreferences getConfigSPF(String spfName) {
        if (spfMap.containsKey(spfName)) {
            return spfMap.get(spfName);
        } else if(ctx!=null){
            SharedPreferences preferences = ctx.getSharedPreferences(spfName, Context.MODE_APPEND);
            spfMap.put(spfName, preferences);
            return preferences;
        }
        return null;
    }

    public SharedPreferences getDefaultSPF(){
        return getConfigSPF(NAME_DEFAULT);
    }

}
