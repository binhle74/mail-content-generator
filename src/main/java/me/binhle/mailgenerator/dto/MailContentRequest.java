package me.binhle.mailgenerator.dto;

import java.util.Map;

public class MailContentRequest {
    private String mailTemplate;

    private Map<String, String> parameters;

    public String getMailTemplate() {
        return mailTemplate;
    }

    public void setMailTemplate(String mailTemplate) {
        this.mailTemplate = mailTemplate;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
