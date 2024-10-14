package com.example.myrok.service.openapi;
import com.example.myrok.dto.chat.ChatRequest;
import com.example.myrok.dto.chat.Message;
import com.example.myrok.infrastructure.ChatCompletionClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ChatCompletionService {

    private final ChatCompletionClient chatCompletionClient;
    private final static String ROLE_USER = "user";
    private final static String ROLE_SYSTEM = "system";
    private final static String MODEL = "gpt-3.5-turbo";

    @Value("${open_ai.api.key}")
    private String apiKey;

    public String chatCompletions(String question) {

        System.out.println(apiKey);

        Message message = Message.builder()
                .role(ROLE_USER)
                .content(question)
                .build();

        String systemContent = "위 내용을 요약해줘";
        Message systemMessage = Message.builder()
                .role(ROLE_SYSTEM)
                .content(systemContent)
                .build();

        ChatRequest chatRequest = ChatRequest.builder()
                .model(MODEL)
                .messages(Arrays.asList(message, systemMessage))
                .build();

        return chatCompletionClient
                .chatCompletions("Bearer " + apiKey, chatRequest)
                .getChoices()
                .stream()
                .findFirst()
                .orElseThrow()
                .getMessage()
                .getContent();
    }
}