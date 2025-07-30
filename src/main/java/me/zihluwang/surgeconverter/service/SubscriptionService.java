package me.zihluwang.surgeconverter.service;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import me.zihluwang.surgeconverter.property.AppProperty;
import me.zihluwang.surgeconverter.util.UriUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SubscriptionService {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionService.class);
    private final WebClient webClient;
    private final Configuration configuration;
    private final AppProperty appProperty;

    public SubscriptionService(WebClient webClient, Configuration configuration, AppProperty appProperty) {
        this.webClient = webClient;
        this.configuration = configuration;
        this.appProperty = appProperty;
    }

    public List<String> getNodes(List<String> urls) {
        var content = webClient.get()
                .uri((builder) -> {
                    var uri = builder
                            .scheme(appProperty.getConverterScheme())
                            .host(appProperty.getConverterHost())
                            .path("/sub")
                            .queryParam("target", "surge")
                            .queryParam("ver", 4)
                            .queryParam("url", UriUtil.encodeURIComponent(String.join("|", urls)))
                            .queryParam("insert", false)
                            .queryParam("exclude", "(.*IPv6.*)|(.*时间.*)|(.*流量.*)|(.*重置.*)|(.*到期.*)")
                            .queryParam("emoji", false)
                            .queryParam("list", true)
                            .queryParam("tfo", false)
                            .queryParam("scv", true)
                            .queryParam("fdn", false)
                            .queryParam("sort", false)
                            .build();
                    log.debug("uri = {}", uri);
                    return uri;
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (Objects.isNull(content) || content.isBlank()) {
            throw new IllegalStateException("No nodes were found.");
        }

        return List.of(content.split("\\r?\\n"));
    }

    public String render(Map<String, Object> dataModel) {
        try (var out = new StringWriter()) {
            var template = configuration.getTemplate("subscription.ftl");
            template.process(dataModel, out);
            return out.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
