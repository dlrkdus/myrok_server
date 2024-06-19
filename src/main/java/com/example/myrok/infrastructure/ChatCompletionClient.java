package com.example.myrok.infrastructure;

import com.example.myrok.dto.chat.ChatRequest;
import com.example.myrok.dto.chat.ChatResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "chat", url = "https://api.openai.com/v1/")
public interface ChatCompletionClient {

    @Headers("Content-Type: application/json")
    @PostMapping("/chat/completions")
    ChatResponse chatCompletions(@RequestHeader("Authorization") String apiKey, ChatRequest request);
}