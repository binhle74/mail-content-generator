package me.binhle.mailgenerator.service;

import me.binhle.mailgenerator.dto.MailContentRequest;
import me.binhle.mailgenerator.dto.MailContentResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MailContentServiceImpl implements MailContentService {
    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\{[a-zA-Z0-9]+}");

    @Override
    public MailContentResponse generateMailContent(MailContentRequest mailContentRequest) {
        String mailTemplate = mailContentRequest.getMailTemplate();
        Map<String, Object> parameters = mailContentRequest.getParameters();
        Matcher phMatcher = PLACEHOLDER_PATTERN.matcher(mailTemplate);

        int nextIndex = 0;
        StringBuilder mailContent = new StringBuilder();
        while (phMatcher.find()) {
            mailContent.append(mailTemplate, nextIndex, phMatcher.start());
            // Get parameter name without curly brackets. E.g. {name} -> name
            String paramName = mailTemplate.substring(phMatcher.start() + 1, phMatcher.end() - 1);
            mailContent.append(parameters.get(paramName));
            // Keep next index to append substring into content
            nextIndex = phMatcher.end();
        }

        MailContentResponse response = new MailContentResponse();
        response.setMailContent(mailContent.toString());
        return response;
    }
}
