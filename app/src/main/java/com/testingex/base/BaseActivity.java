package com.testingex.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.testingex.app.prefs.Constants;
import com.testingex.app.utils.KeyboardUtil;
import com.testingex.app.utils.StackSet;

public abstract class BaseActivity extends AppCompatActivity {

    private StackSet<Fragment> stackSet = new StackSet<>();

    public final Activity getActivity(){
        return this;
    }

    public final void hideKeyboard(){
        if (getCurrentFocus() != null)
            KeyboardUtil.hideKeyboard(this,getCurrentFocus());
    }

    public final StackSet<Fragment> getBackStack() {
        return stackSet;
    }

    public final Fragment getCurrentFragment() {
        return stackSet.peek();
    }

    public final void replaceFragment(@NonNull Fragment fragment, @IdRes int containerId, boolean addToBackStack) {
        try {
            FragmentManager fm = getSupportFragmentManager();
            String fragmentName = fragment.getClass().getName();
            fm.beginTransaction().replace(containerId,fragment,fragmentName).commit();
            if (addToBackStack) stackSet.push(fragment);
            hideKeyboard();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public final void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }

    public final <T> void navigateTo(Class<T> destination, Bundle bundle) {
        Intent intent = new Intent(this,destination);
        if (bundle != null) {
            intent.putExtra(Constants.KEY_BUNDLE_PARAM,bundle);
        }
        startActivity(intent);
    }

    public final <T> void navigateTo(Class<T> destination, Bundle bundle, boolean isFinishing) {
        Intent intent = new Intent(this,destination);
        if (bundle != null) {
            intent.putExtra(Constants.KEY_BUNDLE_PARAM,bundle);
        }
        startActivity(intent);
        if (isFinishing) finish();
    }

    public final void navigateTo(Intent intent, boolean isFinishing) {
        startActivity(intent);
        if (isFinishing) finish();
    }

}
