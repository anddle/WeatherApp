package com.anddle.weatherapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ForcastInfoTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void checkWeatherCode0_isCorrect() throws Exception {

        ForcastInfo forcastInfo0 = new ForcastInfo("", "", 0);
        assertEquals("ForcastInfoTest test weather code 0: ", R.mipmap.ic_sunny_s, forcastInfo0.iconResId);

    }

    @Test
    public void checkWeatherCode1_isCorrect() throws Exception {

        ForcastInfo forcastInfo1 = new ForcastInfo("", "", 1);
        assertEquals("ForcastInfoTest test weather code 1: ", R.mipmap.ic_rainy_s, forcastInfo1.iconResId);

    }

    @Test
    public void checkWeatherCode2_isCorrect() throws Exception {

        ForcastInfo forcastInfo2 = new ForcastInfo("", "", 2);
        assertEquals("ForcastInfoTest test weather code 2: ", R.mipmap.ic_cloudy_s, forcastInfo2.iconResId);

    }

    @Test
    public void checkWeatherCode3_isCorrect() throws Exception {

        ForcastInfo forcastInfo3 = new ForcastInfo("", "", 3);
        assertEquals("ForcastInfoTest test weather code 3: ", R.mipmap.ic_fog_s, forcastInfo3.iconResId);

    }

    @Test
    public void checkWeatherCode4_isCorrect() throws Exception {

        ForcastInfo forcastInfo4 = new ForcastInfo("", "", 4);
        assertEquals("ForcastInfoTest test weather code 4: ", R.mipmap.ic_snow_s, forcastInfo4.iconResId);

    }

    @Test
    public void checkWeatherCode5_isCorrect() throws Exception {

        ForcastInfo forcastInfo5 = new ForcastInfo("", "", 5);
        assertEquals("ForcastInfoTest test weather code 5: ", R.mipmap.ic_sunny_cloudy_s, forcastInfo5.iconResId);

    }
}