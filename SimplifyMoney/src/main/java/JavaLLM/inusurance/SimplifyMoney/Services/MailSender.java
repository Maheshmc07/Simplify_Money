//package JavaLLM.inusurance.SimplifyMoney.Services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MailSender  {
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//    public void sendEmail(String to, String subject, String body) {
//        try {
//            SimpleMailMessage mail = new SimpleMailMessage();
//            mail.setTo(to);
//            mail.setSubject(subject);
//            mail.setText(body);
//            javaMailSender.send(mail);
//            System.out.println("Email sent successfully to: " + to);
//        } catch (Exception e) {
//            System.out.println("Failed to send email to: " + to);
//            e.printStackTrace(); // Print the error details
//        }
//    }
//
//}
