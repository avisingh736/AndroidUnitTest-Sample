package com.testingex.base;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.testingex.app.utils.StackSet;

public class BaseFragment extends Fragment {

    private StackSet<Fragment> stackSet = new StackSet<>();
    protected Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        this.activity = (Activity) context;
        super.onAttach(context);
    }

    public final StackSet<Fragment> getChildBackStack() {
        return stackSet;
    }

    public final Fragment getCurrentChildFragment() {
        return stackSet.peek();
    }

    public final void replaceChildFragment(@NonNull Fragment fragment, @IdRes int containerId, boolean addToBackStack) {
        try {
            FragmentManager fm = getChildFragmentManager();
            String fragmentName = fragment.getClass().getName();
            fm.beginTransaction().replace(containerId,fragment,fragmentName).commit();
            if (addToBackStack) stackSet.push(fragment);
            ((BaseActivity) activity).hideKeyboard();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
