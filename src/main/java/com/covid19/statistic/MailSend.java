package com.covid19.statistic;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class MailSend {
    public void Sender() throws MessagingException {
        final String user = "javajuniortesthomework@gmail.com";
        final String password = "Cuprum63546";

        Properties properties = System.getProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smpt.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });


        BufferedReader br = null;
        FileReader fr = null;
        List<String> addressList = new ArrayList<>();

        try {
            fr = new FileReader("address.txt");
            br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
                addressList.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            for (String iterator : addressList) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(iterator));
            }
            LocalDate yesterday = LocalDate.now().minusDays(1);

            JsonReader reader = new JsonReader();
            Statistic[] statistic = reader.readData();

            Optional<Statistic> yesterdayStat = Optional.empty();

            for (Statistic stat : statistic) {
                if (yesterday.compareTo(stat.getDate().toLocalDate()) == 0) {
                    if (yesterdayStat.isPresent() && yesterdayStat.get().
                            getDate().isBefore(stat.getDate())) {
                        yesterdayStat = Optional.of(stat);
                    } else if (yesterdayStat.isEmpty()) {
                        yesterdayStat = Optional.of(stat);
                    }
                }
            }

            yesterdayStat.ifPresent(statistic1 -> {

                try {
                    message.setSubject("Yesterday in Poland new corona cases: " + statistic1.getDailyInfected()
                            + ".  Died due to Covid: " + statistic1.getDailyDeceasedDueToCovid());

                    BodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setText("Did it work?");

                    message.setText("It must work!");

                    MimeBodyPart attachment = new MimeBodyPart();
                    try {
                        attachment.attachFile("Statistic.xlsx");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Multipart multipart = new MimeMultipart();
                    multipart.addBodyPart(messageBodyPart);
                    multipart.addBodyPart(attachment);

                    message.setContent(multipart);

                    Transport tr = session.getTransport();
                    tr.connect(user, password);
                    tr.sendMessage(message, message.getAllRecipients());
                    tr.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
