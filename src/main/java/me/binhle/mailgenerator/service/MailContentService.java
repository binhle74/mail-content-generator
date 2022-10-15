package me.binhle.mailgenerator.service;

import me.binhle.mailgenerator.dto.MailContentRequest;
import me.binhle.mailgenerator.dto.MailContentResponse;

public interface MailContentService {
    MailContentResponse generateMailContent(MailContentRequest mailContentRequest);
}
