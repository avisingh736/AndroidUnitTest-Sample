package com.testingex.app.prefs;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefHelper {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    private static PrefHelper sInstance;

    public static synchronized PrefHelper instance() {
        return sInstance;
    }

    @SuppressLint("CommitPrefEdits")
    private PrefHelper(Context context) {
        sInstance = this;
        String prefsFile = context.getPackageName();
        sharedPreferences = context.getSharedPreferences(prefsFile, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void init(Context context) {
        if (sInstance == null) {
            new PrefHelper(context);
        }
    }

    private void delete(String key) {
        if (sharedPreferences.contains(key)) {
            editor.remove(key).commit();
        }
    }

    public boolean remove(String key) {
        if (sharedPreferences.contains(key)) {
            editor.remove(key).commit();
            return true;
        }
        return false;
    }

    public void removeAll() {
        editor.clear().commit();
    }

    public void savePref(String key, Object value) {
        delete(key);
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Enum) {
            editor.putString(key, value.toString());
        } else if (value != null) {
            throw new RuntimeException("Attempting to save non-primitive preference");
        }
        editor.commit();
    }

    @SuppressWarnings("unchecked")
    public <T> T getPref(String key) {
        return (T) sharedPreferences.getAll().get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getPref(String key, T defValue) {
        T returnValue = (T) sharedPreferences.getAll().get(key);
        return returnValue == null ? defValue : returnValue;
    }

    public boolean isPrefExists(String key) {
        return sharedPreferences.contains(key);
    }
}
