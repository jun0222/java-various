package com.javarious.example.javavarious.app.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @GetMapping("/channels/{channelId}/messages")
    public List<Message> find(
            @PathVariable("channelId") int channelId,
            @RequestParam("searchWord") Optional<String> searchWord) {
        // TODO: Service作成後に修正する。
        return Collections.emptyList();
    }
}
