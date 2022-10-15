package me.binhle.mailgenerator.controller;

import me.binhle.mailgenerator.dto.MailContentRequest;
import me.binhle.mailgenerator.service.MailContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/mail")
public class MailContentController {
    @Autowired
    private MailContentService mailContentService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateMailContent(@RequestBody MailContentRequest mailContentRequest) {
        return new ResponseEntity<>(mailContentService.generateMailContent(mailContentRequest), HttpStatus.OK);
    }
}
