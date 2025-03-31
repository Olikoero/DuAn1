package org.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtils {
    private static final String USERNAME = "thundershop2025@gmail.com";
    private static final String PASSWORD = "vuqendrlnadqaufs"; // App Password của bạn

    // Hàm gửi email chứa mật khẩu mới
    public void sendNewPasswordEmail(String toEmail, String newPassword) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.debug", "true"); // Bật debug để kiểm tra lỗi

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Mật Khẩu Mới - Thunder Shop");
        message.setText("Chào bạn,\n\n" +
                "Mật khẩu mới của bạn là: " + newPassword + "\n" +
                "Vui lòng đổi mật khẩu sau khi đăng nhập.\n\n" +
                "Trân trọng,\nThunder Shop");

        Transport.send(message);
    }
}