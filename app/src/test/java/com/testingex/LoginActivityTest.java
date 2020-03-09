package com.testingex;

import android.os.Build;
import android.view.View;

import androidx.test.core.app.ActivityScenario;

import com.testingex.ui.login.LoginActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class LoginActivityTest {

    @Test
    public void loginLoginOrRegisterButtonTest_shouldLoginOrRegisterWithProgressLoader() {
        ActivityScenario<LoginActivity> scenario = ActivityScenario.launch(LoginActivity.class);
        scenario.onActivity( activity -> {
            activity.getBinding().login.performClick();

            assertEquals("Progress bar should be visible now", activity.getBinding().login.getVisibility(), View.VISIBLE);
        });
    }
}
