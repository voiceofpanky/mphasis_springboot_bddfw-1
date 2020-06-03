package com.mphasis.qe;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class PropertySourceResolver {

    @Value("${app.url.base}")
    private String appBaseUrl;

    @Value("${app.url.api}")
    private String appApiUrl;

    @Value("${platform.name}")
    private String platformName;

    @Value("${browser.name}")
    private String browserName;

    @Value("${selenium.chrome.driver}")
    private String chromeDriverPath;

    @Value("${selenium.gecko.driver}")
    private String geckoDriverPath;

    @Value("${selenium.ie.driver}")
    private String ieDriverPath;

    @Value("${android.apk.file}")
    private String androidApkFile;

    @Value("${android.device.name}")
    private String androidDeviceName;

    @Value("${android.platform.version}")
    private String androidPlatformVersion;

    @Value("${ios.app.file}")
    private String iosAppFile;

    @Value("${ios.device.name}")
    private String iosDeviceName;

    @Value("${ios.platform.version}")
    private String iosPlatformVersion;

    @Value("${perfecto.username}")
    private String perfectoUsername;

    @Value("${perfecto.token}")
    private String perfectoToken;

    @Value("${perfecto.url}")
    private String perfectoUrl;

    @Value("${encrypted.username}")
    private String userId;

    @Value("${encrypted.password}")
    private String password;

    @Value("${data.source}")
    private String dataSource;

    @Value("${data.path}")
    private String dataPath;

    public String getAppBaseUrl() {
        return appBaseUrl;
    }
  
    public String getAppApiUrl() {
        return appApiUrl;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public String getGeckoDriverPath() {
        return geckoDriverPath;
    }

    public String getIeDriverPath() {
        return ieDriverPath;
    }

    public String getAndroidApkFile() {
        return androidApkFile;
    }

    public String getAndroidDeviceName() {
        return androidDeviceName;
    }

    public String getAndroidPlatformVersion() {
        return androidPlatformVersion;
    }

    public String getIosAppFile() {
        return iosAppFile;
    }

    public String getIosDeviceName() {
        return iosDeviceName;
    }

    public String getIosPlatformVersion() {
        return iosPlatformVersion;
    }

    public String getPerfectoUsername() {
        return perfectoUsername;
    }

    public String getPerfectoToken() {
        return perfectoToken;
    }

    public String getPerfectoUrl() {
        return perfectoUrl;
    }

    public String getUserId(){
        return userId;
    }

    public String getPassword(){
        return password;
    }

    public String getDataSource() {
        return dataSource;
    }

    public String getDataPath() {
        return dataPath;
    }
}
