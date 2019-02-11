package br.com.acoaapp.main.login;

import android.support.test.BuildConfig;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.acoaapp.R;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginScreenTest {

    private LoginActivity loginActivity;
    private String email;
    private String password;

    //TODO set other values according to BuildConfig.FLAVOR - mock, debug, etc
    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule =
            new ActivityTestRule<>(LoginActivity.class, false, true);


    @Before
    public void setup() {
        loginActivity = loginActivityTestRule.getActivity();

        if (BuildConfig.FLAVOR.equals("mock")) {
            email = "bed.rick@gmail.com";
            password = "password";
        } else {
            email = "bed.rick@yahoo.com";
            password = "password";
        }
    }

    @Test
    public void whenActivityIsLaunched_shouldDisplayInitialState() {
        onView(withId(R.id.tv_logomark)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
        onView(withId(R.id.et_email)).check(matches(isDisplayed()));
        onView(withId(R.id.et_password)).check(matches(isDisplayed()));
        onView(withId(R.id.bt_login)).check(matches(isDisplayed()));
    }

    @Test
    public void whenFillAllFields_andPressEnterButton_shouldOpenInitialScreen() {
        onView(withId(R.id.et_email)).perform(typeText(email));
        closeSoftKeyboard();
        onView(withId(R.id.et_password)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.bt_login)).perform(click());
        //onView(withText("Estatísticas gerais")).check(matches(isDisplayed()));
    }

    @Test
    public void whenFillOnlyEmailField_andPressEnterButton_shouldDisplayAnErrorMessage(){
        onView(withId(R.id.et_email)).perform(typeText(email));
        closeSoftKeyboard();
        onView(withId(R.id.bt_login)).perform(click());
    }

    @Test
    public void whenFillOnlyPasswordField_andPressEnterButton_shouldDisplayAnErrorMessage(){
        onView(withId(R.id.et_password)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.bt_login)).perform(click());
    }
}
