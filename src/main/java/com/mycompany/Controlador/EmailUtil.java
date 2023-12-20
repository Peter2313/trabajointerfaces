/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Pedro garcia Vicente
 */
public class EmailUtil {

  public static void sendEmail(Session session, String toEmail, String subject, String body) throws MessagingException {
    try {
      MimeMessage msg = new MimeMessage(session);
      msg.addHeader("Content-type", "text/HTML; charset-UTF-8");
      msg.addHeader("format", "flowed");
      msg.addHeader("Content-Transfer-Encoding", "8bit");
      msg.setFrom(new InternetAddress("pedro.garvic@educa.jcyl.es", "NoReply-JD"));
      msg.setReplyTo(InternetAddress.parse("pedro.garvic@educa.jcyl.es", false));
      msg.setSubject(subject, "UTF-8");
      msg.setText(body, "UTF-8");
      msg.setSentDate(new Date());
      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
      System.out.println("Message is ready");
      Transport.send(msg);
      System.out.println("Email sent succesfully");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
