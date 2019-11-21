package com.railwayTicketManager.config.sendMail.mail;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * @Author: Qidian
 * @ClassName: EmailUtil
 * @Date: 2019-06-30 22:10
 * @E-mail： qidian@jiamsh.com
 * @Description:
 * @Version: V1.0.0
 */
public class EmailUtil {
    /**
     * 进行base64加密，防止中文乱码
     */
    private static String changeEncode(String str) {
        try {
            str = MimeUtility.encodeText(new String(str.getBytes(), "UTF-8"), "UTF-8", "B"); // "B"代表Base64
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static boolean sendHtmlMail(EmailInfo emailInfo) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", emailInfo.getSmtpServer());
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // 使用JSSE的SSL
        properties.put("mail.smtp.socketFactory.fallback", "false"); // 只处理SSL的连接,对于非SSL的连接不做处理
        properties.put("mail.smtp.port", emailInfo.getPort());
        properties.put("mail.smtp.socketFactory.port",emailInfo.getPort());
        Session session = Session.getInstance(properties);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);

        try {
            // 发件人
            Address address = new InternetAddress(emailInfo.getFromUserName());
            message.setFrom(address);
            // 收件人
            Address toAddress = new InternetAddress(emailInfo.getToUser());
            message.setRecipient(MimeMessage.RecipientType.TO, toAddress); // 设置收件人,并设置其接收类型为TO

            // 主题message.setSubject(changeEncode(emailInfo.getSubject()));
            message.setSubject(emailInfo.getSubject());
            // 时间
            message.setSentDate(new Date());

            Multipart multipart = new MimeMultipart();

            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(emailInfo.getContent(), "text/html; charset=utf-8");
            multipart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            message.setContent(multipart);
            message.saveChanges();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        try {
            Transport transport = session.getTransport("smtp");
            transport.connect(emailInfo.getSmtpServer(), emailInfo.getFromUserName(), emailInfo.getFromUserPassword());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
