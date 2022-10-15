package me.binhle.mailgenerator.dto;

import java.util.Map;

public class MailContentRequest {
    private String mailTemplate;
    private Map<String, Object> parameters;

    public String getMailTemplate() {
        return mailTemplate;
    }

    public void setMailTemplate(String mailTemplate) {
        this.mailTemplate = mailTemplate;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
