package com.anddle.weatherapp;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MainActivityTest {

    private MainActivity mMainActivity;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mMainActivity = Mockito.spy(MainActivity.class);

    }

    @Test
    public void getWeatherInformationFromNetwork_ifUrlisNull() throws Exception {

        String data = mMainActivity.getWeatherInformationFromNetwork(null);

        assertEquals("MainActivityTest test getWeatherInformationFromNetwork: url is null", "", data);

    }

    @Test
    public void getWeatherInformationFromNetwork_ifUrlisEmpty() throws Exception {

        String data = mMainActivity.getWeatherInformationFromNetwork("");

        assertEquals("MainActivityTest test getWeatherInformationFromNetwork: url is empty", "", data);

    }

    @Test
    public void getWeatherInformationFromNetwork_ifUrlisInvalid() throws Exception {

        String data = mMainActivity.getWeatherInformationFromNetwork("http://www.xxx.com");

        assertEquals("MainActivityTest test getWeatherInformationFromNetwork: url is invalid", "", data);

    }

    @Test
    public void getWeatherInformationFromNetwork_ifUrlisValid() throws Exception {

        String data = mMainActivity.getWeatherInformationFromNetwork("http://book.anddle.com/api/query_weather");

        assertNotEquals("MainActivityTest test getWeatherInformationFromNetwork: url is valid", 0, data.length());

    }

}