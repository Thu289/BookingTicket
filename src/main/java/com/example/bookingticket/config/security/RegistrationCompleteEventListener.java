package com.example.bookingticket.config.security;

import com.example.bookingticket.model.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    public final JavaMailSender mailSender;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

    }

    public void sendVerificationEmail(String url, Account account) throws UnsupportedEncodingException, MessagingException, jakarta.mail.MessagingException {
        String subject = "Tài khoản";
        String senderName = "Booking ticket";
        String mailContent = "<p> Chào, " + account.getName() + ", </p>" +
                "<p><b>Gần đây bạn đã được kích hoạt tài khoản, hãy đặt lại mật khẩu của mình</b></p>"  +
                "<p>Vui lòng nhấp vào liên kết bên dưới để hoàn thành hành động.</p>" +
                "<p>Tên đăng nhập: "+ account.getName()+"</p>"+
                "<a href=\"" + url + "\">Đăng nhập hệ thống!</a>" +
                "<p>Vào thông tin nhân viên để đổi mật khẩu</p>"+
                "<p> Dịch vụ cổng đăng ký người dùng";

        jakarta.mail.internet.MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("manahotelsystem@gmail.com", senderName);
        messageHelper.setTo(account.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }

    public void sendPasswordResetVerificationEmail(String url, Account account) throws UnsupportedEncodingException, MessagingException, jakarta.mail.MessagingException {
        String subject = "Xác minh yêu cầu đặt lại mật khẩu";
        String senderName = "ManaHotel";
        String mailContent = "<p> Chào, " + account.getName() + ", </p>" +
                "<p><b>Gần đây bạn đã yêu cầu đặt lại mật khẩu của mình</b></p>"  +
                "<p>Vui lòng nhấp vào liên kết bên dưới để hoàn thành hành động.</p>" +
                "<a href=\"" + url + "\">Đặt lại mật khẩu</a>" +
                "<p> Dịch vụ cổng đăng ký người dùng";
        jakarta.mail.internet.MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom("manahotelsystem@gmail.com", senderName);
        messageHelper.setTo(account.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}
