package com.victor.stockalarms.service;

import com.victor.stockalarms.entity.Alarm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    private static final Logger LOG = LogManager.getLogger(EmailService.class.getName());

    private static final String COULD_NOT_SEND_EMAIL_MESSAGE = "Could not send email to [%s], exception was: %s";
    private static final String EMAIL_SENT_MESSAGE = "Successfully sent email to [%s].";

    private static final String TLS_ENABLE = "mail.smtp.starttls.enable";
    private static final String HOST = "mail.smtp.host";
    private static final String PORT = "mail.smtp.port";
    private static final String AUTH = "mail.smtp.auth";

    private static final String STOCK_ALERT_EMAIL_SUBJECT = "Stock Alert!";
    private static final String EMAIL_CONTENT = "Dear %s,\nYour alarm for stock %s has been triggered.\nSet value was %s, new value is %s.";

    private final Session emailSession;
    private final String emailSenderUsername;

    public EmailService(@Value("${emailSender.username}") final String emailSenderUsername,
                        @Value("${emailSender.password}") final String emailSenderPassword) {
        emailSession = createSession(emailSenderUsername, emailSenderPassword);
        this.emailSenderUsername = emailSenderUsername;
    }

    void sendEmail(final Alarm alarm, double newPrice) {
        try {
            final Message message = new MimeMessage(emailSession);

            message.setFrom(new InternetAddress(emailSenderUsername));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(alarm.getUser().getEmail()));
            message.setSubject(STOCK_ALERT_EMAIL_SUBJECT);
            message.setText(String.format(EMAIL_CONTENT, alarm.getUser().getName(), alarm.getStockName(), alarm.getStockValue(), newPrice));

            Transport.send(message);

            LOG.info(String.format(EMAIL_SENT_MESSAGE, alarm.getUser().getEmail()));
        } catch (final MessagingException e) {
            LOG.error(String.format(COULD_NOT_SEND_EMAIL_MESSAGE, alarm.getUser().getEmail(), e));
        }
    }

    private Session createSession(final String username, final String password) {
        return Session.getInstance(getEmailProperties(),
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    private Properties getEmailProperties() {
        final Properties properties = new Properties();

        properties.put(HOST, "smtp.gmail.com");
        properties.put(PORT, "587");
        properties.put(AUTH, "true");
        properties.put(TLS_ENABLE, "true");

        return properties;
    }

}