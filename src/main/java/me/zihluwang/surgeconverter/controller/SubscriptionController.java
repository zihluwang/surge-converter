package me.zihluwang.surgeconverter.controller;

import me.zihluwang.surgeconverter.property.AppProperty;
import me.zihluwang.surgeconverter.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

    private final SubscriptionService subscriptionService;
    private final AppProperty appProperty;

    public SubscriptionController(SubscriptionService subscriptionService, AppProperty appProperty) {
        this.subscriptionService = subscriptionService;
        this.appProperty = appProperty;
    }

    @GetMapping
    public String getSubscription(@RequestParam List<String> urls) {
        var nodes = subscriptionService.getNodes(urls);
        var nodeNames = nodes.stream()
                .map((node) -> node.split("=")[0].trim())
                .toList();

        var paramMap = new HashMap<String, Object>();
        paramMap.put("proxyNodes", nodes);
        paramMap.put("proxyNodeNames", nodeNames);
        paramMap.put("host", appProperty.getHost());
        paramMap.put("urls", urls);

        return subscriptionService.render(paramMap);
    }
}
