package com.anddle.weatherapp;


import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    MainActivity mMainActivity;

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setUp() throws Exception {

        mMainActivity = (MainActivity) mActivityRule.getActivity();

    }

    @Test
    public void getWeatherInformationFromNetwork_ifUrlisValid() throws Exception {

        String data = mMainActivity.getWeatherInformationFromNetwork("http://book.anddle.com/api/query_weather");

        assertNotEquals("MainActivityTest test getWeatherInformationFromNetwork: url is valid", 0, data.length());

    }

    @Test
    public void checkWeatherDetail_Activity() {

        onView(withId(R.id.current_temperature))
                .check(matches(withText("23°")));

        onView(withId(R.id.temperature_range))
                .check(matches(withText("18℃～23℃")));
    }


}
