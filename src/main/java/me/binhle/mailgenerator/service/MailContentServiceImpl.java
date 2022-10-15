package me.binhle.mailgenerator.service;

import me.binhle.mailgenerator.dto.MailContentRequest;
import me.binhle.mailgenerator.dto.MailContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailContentServiceImpl implements MailContentService {
    @Autowired
    private StringFormatService stringFormatService;

    @Override
    public MailContentResponse generateMailContent(MailContentRequest mailContentRequest) {
        if (mailContentRequest.getMailTemplate() == null) {
            throw new IllegalArgumentException("'mailTemplate' can not be null");
        }

        if (mailContentRequest.getParameters() == null) {
            throw new IllegalArgumentException("'parameters' can not be null");
        }

        return new MailContentResponse(stringFormatService.format(mailContentRequest.getMailTemplate(), mailContentRequest.getParameters()));
    }
}
