package com.sendEmail.sendEmailContext.Service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class HtmlReaderService {

    private final ResourceLoader resourceLoader;

    public HtmlReaderService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String readHtmlFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/emailContext.html");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        }
    }
}