package ru.andr.producer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.andr.producer.service.SenderService;

@RestController
public class MessageController {
    private final SenderService service;

    public MessageController(SenderService service) {
        this.service = service;
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        service.sendMessages(message);
        return ResponseEntity.ok(message);
    }

}
