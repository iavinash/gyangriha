package com.three.gyangriha.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String recipientEmail, String name, String planName,
                                      String startDate, String endDate, String price) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(recipientEmail);
            helper.setSubject("Gyan Griha - Registration Confirmation");
            helper.setText(buildEmailContent(name, planName, startDate, endDate, price), true);

            mailSender.send(message);
            System.out.println("Email sent successfully!");
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildEmailContent(String name, String planName, String startDate, String endDate, String price) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; }
                    .container { padding: 20px; border: 1px solid #ddd; border-radius: 8px; max-width: 600px; margin: auto; }
                    .header { text-align: center; color: #4CAF50; }
                    .details { margin-top: 20px; }
                    .details td { padding: 8px; }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2 class="header">Welcome, %s!</h2>
                    <p>Thank you for registering with us. Here are your registration and subscription details:</p>
                    <div class="details">
                        <table>
                            <tr><td><strong>Email:</strong></td><td>%s</td></tr>
                            <tr><td><strong>Subscription Plan:</strong></td><td>%s</td></tr>
                            <tr><td><strong>Start Date:</strong></td><td>%s</td></tr>
                            <tr><td><strong>End Date:</strong></td><td>%s</td></tr>
                            <tr><td><strong>Price:</strong></td><td>%s</td></tr>
                        </table>
                    </div>
                    <p>We hope you enjoy our services!</p>
                </div>
            </body>
            </html>
        """.formatted(name, name, planName, startDate, endDate, price);
    }
}