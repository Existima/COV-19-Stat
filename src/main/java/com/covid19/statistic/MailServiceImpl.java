package com.covid19.statistic;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class MailServiceImpl implements MailService {
    private static final Logger LOGGER = Logger.getLogger("MailServiceImpl");
    private final String user;
    private final String password;
    private final Properties properties;

    public MailServiceImpl(String user, String password, Properties properties) {
        this.user = user;
        this.password = password;
        this.properties = properties;
    }

    public void sendMail(List<String> to,
                         Statistic statistic,
                         Path attachmentFile) {

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));

            for (String iterator : to) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(iterator));
            }

            message.setSubject("Yesterday in Poland new corona cases: " + statistic.getDailyInfected()
                    + ".  Died due to Covid: " + statistic.getDailyDeceasedDueToCovid());

            message.setText("Statistics is in the attachment!");

            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(attachmentFile.toFile());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(attachment);

            message.setContent(multipart);

            Transport tr = session.getTransport();
            tr.connect(user, password);
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
        } catch (MessagingException | IOException e) {
            LOGGER.severe(String.format("Can't send mail, error: %s", e.getMessage()));
        }
    }
}
