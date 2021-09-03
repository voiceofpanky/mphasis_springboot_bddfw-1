package com.mphasis.qe.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import java.io.File;

public class AppiumServerUtil {
    private static AppiumDriverLocalService appiumDriverLocalService;

    private AppiumServerUtil() {}

    public static void startAppiumServer() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.usingAnyFreePort()
                .withArgument(GeneralServerFlag.LOG_TIMESTAMP)
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                .withArgument(GeneralServerFlag.LOG_LEVEL,"info")
                .withArgument(GeneralServerFlag.RELAXED_SECURITY);
        //Below 1 line is needed only if the appium does'nt get started automatically
        appiumServiceBuilder.withAppiumJS(new File("/usr/local/lib/node_modules/appium"));
        appiumDriverLocalService = appiumServiceBuilder.build();
        appiumDriverLocalService.start();
    }

    public static String getAppiumServerAddress() {
        return appiumDriverLocalService.getUrl().toString();
    }

    public static void stopAppiumServer() {
        appiumDriverLocalService.stop();
    }
}