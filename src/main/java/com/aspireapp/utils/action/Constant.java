package com.aspireapp.utils.action;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.TreeMap;

public class Constant {

    public static final ThreadLocal<String> folderProfiles =  new ThreadLocal<String>();

    public static int shortTimewait = 15;
    public static int mediumTimewait = 30;
    public static int longTimewait = 60;
    public static int veryLongTimewait = 180;

    public static int shortTimeSleep = 1000;
    public static int mediumTimeSleep = 5000;
    public static int longTimeSleep = 30000;
    public static int verylongTimeSleep = 60000;



}
