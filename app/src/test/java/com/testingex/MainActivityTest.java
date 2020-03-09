package com.testingex;

import android.app.Application;
import android.content.Intent;
import android.os.Build;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import com.testingex.ui.MainActivity;
import com.testingex.ui.login.LoginActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class MainActivityTest {

    @Test
    public void clickOnLoginButton_shouldStartLoginActivity() {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(activity -> {
            activity.getBinding().btnTest.performClick();

            Intent expectedIntent = new Intent(activity, LoginActivity.class);
            Application application = ApplicationProvider.getApplicationContext();
            Intent actualIntent = Shadows.shadowOf(application).getNextStartedActivity();

            assertEquals(expectedIntent.getComponent(),actualIntent.getComponent());
        });

    }
}
