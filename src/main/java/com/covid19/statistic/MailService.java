package com.covid19.statistic;

import java.nio.file.Path;
import java.util.List;

public interface MailService {
    void sendMail(List<String> to,
                  Statistic statistic,
                  Path attachmentFile);
}
