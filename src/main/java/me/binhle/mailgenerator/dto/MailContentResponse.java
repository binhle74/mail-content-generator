package me.binhle.mailgenerator.dto;

public class MailContentResponse {
    private String mailContent;

    public MailContentResponse(String mailContent) {
        this.mailContent = mailContent;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }
}
