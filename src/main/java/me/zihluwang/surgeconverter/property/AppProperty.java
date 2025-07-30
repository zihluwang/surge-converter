package me.zihluwang.surgeconverter.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperty {

    private String host;

    private String converterHost;

    private String converterScheme;

    public AppProperty() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getConverterHost() {
        return converterHost;
    }

    public void setConverterHost(String converterHost) {
        this.converterHost = converterHost;
    }

    public String getConverterScheme() {
        return converterScheme;
    }

    public void setConverterScheme(String converterScheme) {
        this.converterScheme = converterScheme;
    }
}
