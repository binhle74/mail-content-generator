package me.binhle.mailgenerator.service;

import java.util.Map;

public interface StringFormatService {
    /**
     * Returns a formatted string using template and parameters.
     *
     * @param template A string contains {parameter} as placeholders. E.g. Hi {user}, welcome to {company}
     * @param parameters A map contains desired parameter values in form of key-value pairs
     * @return A formatted string
     */
    String format(String template, Map<String, String> parameters);
}
