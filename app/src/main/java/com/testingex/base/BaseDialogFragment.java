package com.testingex.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment {

    private BaseActivity baseActivity;
    private Context context;
    private boolean isFullScreen = false;
    private int style = -1;

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        this.baseActivity = (BaseActivity) context;
        super.onAttach(context);
    }

    public final void setFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
    }

    public final void setStyle(@StyleRes int style) {
        this.style = style;
    }

    public final BaseActivity getBaseActivity() {
        return baseActivity;
    }
}
