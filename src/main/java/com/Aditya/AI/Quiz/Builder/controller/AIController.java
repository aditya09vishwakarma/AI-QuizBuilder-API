package com.Aditya.AI.Quiz.Builder.controller;



import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.api.MemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;

import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AIController {
    private final ChatClient chatClient;
//    public AIController(OllamaChatModel chatModel){
//        this.chatClient= ChatClient
//                .create(chatModel);
//    }
public AIController(ChatClient.Builder builder) {
   this.chatClient = builder
            .build();
}

    @GetMapping("/{message}")
    public String getAnswer(@PathVariable String message){
        ChatResponse chatResponse=chatClient
                .prompt(message)
                .call().chatResponse();
        System.out.println(chatResponse.getMetadata().getModel());
         return chatResponse
                .getResult().getOutput().getText();

    }

}
