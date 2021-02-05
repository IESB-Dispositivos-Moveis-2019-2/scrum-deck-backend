package br.iesb.message;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public final class MessageSource {
    private static final ResourceBundleMessageSource MESSAGE_SOURCE;

    static {
        MESSAGE_SOURCE = new ResourceBundleMessageSource();
        MESSAGE_SOURCE.setBasenames("properties/messages", "api/messages");
        MESSAGE_SOURCE.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        MESSAGE_SOURCE.setFallbackToSystemLocale(true);
    }

    private static MessageSource instance;

    private MessageSource() {
    }

    public static synchronized MessageSource get() {
        if (instance == null) {
            instance = new MessageSource();
        }
        return instance;
    }

    public String message(String key) {
        return MESSAGE_SOURCE.getMessage(key, null, Locale.getDefault());
    }

    public String message(String key, String... args) {
        return MESSAGE_SOURCE.getMessage(key, args, Locale.getDefault());
    }
}
