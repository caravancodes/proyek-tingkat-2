package com.phadcode.perpustakaanku.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hudio Hizari on 4/10/2018.
 */

public class Session {
    public static final String SHARED_PREFERENCES = "shared_preferences";

    public static final String LOGED_IN = "loged_in";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Session(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setSessionString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }
    public String getSessionString(String key){
        return sharedPreferences.getString(key, null);
    }

    public void setSessionBoolean(String key, boolean value){
        editor.putBoolean(key, value);
        editor.commit();
    }
    public Boolean getSessionBoolean(String key){
        return sharedPreferences.getBoolean(key, false);
    }

    public void setSessionInt(String key, int value){
        editor.putInt(key, value);
        editor.commit();
    }
    public int getSessionInt(String key){
        return sharedPreferences.getInt(key, 0);
    }
}
