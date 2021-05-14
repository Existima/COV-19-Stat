package com.covid19.statistic;

import java.util.Properties;

public class MailServiceFactory {
    MailService getMailService(){
        final String user = "javajuniortesthomework@gmail.com";
        final String password = "Cuprum63546";

        Properties properties = System.getProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smpt.auth", "true");

        return new MailServiceImpl(user, password, properties);
    }
}
