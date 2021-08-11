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
    
    @Value("${app.url.register}")
    private String appRegisterUrl;
    
    @Value("${app.url.getUser}")
    private String getUserUrl;

    @Value("${platform.name}")
    private String platformName;

    @Value("${browser.name}")
    private String browserName;
    
    @Value("${browser.version}")
    private String browserVersion;

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
    
    @Value("${reportType.csv}")
    private String csvReport;
    
    @Value("${reportType.db}")
    private String dbReport;
    
    @Value("${reportType.html}")
    private String htmlReport;

    @Value("${appium.execution.type}")
    private String appiumExecType;

    @Value("${browserstack.hosturl}")
    private String browserstackURL;

    @Value("${browserstack.username}")
    private String browserstackUsername;

    @Value("${browserstack.accessKey}")
    private String browserstackToken;
    
    @Value("${app.url.bankUri}")
    private String appBankUri;

    @Value("${parallel.crossbrowser}")
    private boolean isParallelCrossbrowser;
}
