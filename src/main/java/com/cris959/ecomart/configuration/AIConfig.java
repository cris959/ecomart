package com.cris959.ecomart.configuration;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AIConfig {
    @Bean
    @Primary // Esto hace que este sea el modelo por defecto en tu app
    public ChatModel chatModelWithLogging(ChatModel googleChatModel) {
        return new LoggingChatModelDecorator(googleChatModel);
    }
}
