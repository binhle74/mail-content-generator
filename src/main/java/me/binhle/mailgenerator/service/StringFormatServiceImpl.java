package me.binhle.mailgenerator.service;

import me.binhle.mailgenerator.exception.MissingParameterValueException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StringFormatServiceImpl implements StringFormatService {
    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\{[a-zA-Z0-9]+}");

    @Override
    public String format(String template, Map<String, String> parameters) {
        if (template == null) {
            throw new IllegalArgumentException("'template' can not be null");
        }

        if (parameters == null) {
            throw new IllegalArgumentException("'parameters' can not be null");
        }

        StringBuilder mailContent = new StringBuilder();
        Matcher phMatcher = PLACEHOLDER_PATTERN.matcher(template);
        int lastCharMatchedIndex = 0;
        while (phMatcher.find()) {
            mailContent.append(template, lastCharMatchedIndex, phMatcher.start());
            // Get parameter name without curly brackets. E.g. {name} -> name
            String paramName = template.substring(phMatcher.start() + 1, phMatcher.end() - 1);

            if (!parameters.containsKey(paramName)) {
                throw new MissingParameterValueException(paramName);
            }

            mailContent.append(parameters.get(paramName));
            // Keep last character matched index to append substring into content
            lastCharMatchedIndex = phMatcher.end();
        }
        return mailContent.length() > 0 ? mailContent.toString() : template;
    }
}
