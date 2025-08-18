package com.haoshe.community.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jdk.jfr.Unsigned;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

/**
 * Mail utility class for sending emails through SMTP
 * Provides a simple interface for sending both plain text and HTML emails
 */
@Component
public class MailClient {

    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    // Spring's email sender - automatically configured from application.properties
    @Autowired
    private JavaMailSender mailSender;
    // Email address to send from - loaded from application.properties
    @Value("${spring.mail.username}")
    private String from;

    /**
     * Send an email to a recipient
     *
     * @param to      recipient email address
     * @param subject email subject line
     * @param content email content (can be plain text or HTML)
     */
    public void sendMail(String to, String subject, String content){
        try {
            // Create a new MIME message
            MimeMessage message = mailSender.createMimeMessage();
            // Helper to easily configure the message
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            // Set content - 'true' parameter enables HTML content
            helper.setText(content, true);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e){
            logger.error("Failed to send email: " + e.getMessage());
        }
    }
}
