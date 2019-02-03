package com.logmei.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "wechat")
@Data
@Component
public class WechatAccountConfig {
    private String mpAppId;
    private String mchId;
    private String mchKey;
}
