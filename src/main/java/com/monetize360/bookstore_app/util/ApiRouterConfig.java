package com.monetize360.bookstore_app.util;

import com.monetize360.bookstore_app.service.ApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class ApiRouterConfig {
    @Bean
    public IntegrationFlow apiRouterFlow() {
        return IntegrationFlows.from("apiRequestChannel")
                .route("headers['apiType']", // Route based on the 'apiType' header
                        mapping -> mapping
                                .channelMapping("stripe", "stripeChannel")
                                .channelMapping("resend", "resendChannel"))
                .get();
    }

    @Bean
    public MessageChannel apiRequestChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MessageChannel stripeChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public MessageChannel resendChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow stripeFlow(ApiService stripeService) {
        return IntegrationFlows.from("stripeChannel")
                .handle((payload, headers) -> stripeService.processRequest((String) payload))
                .get();
    }

    @Bean
    public IntegrationFlow resendFlow(ApiService resendService) {
        return IntegrationFlows.from("resendChannel")
                .handle((payload, headers) -> resendService.processRequest((String) payload))
                .get();
    }
}

