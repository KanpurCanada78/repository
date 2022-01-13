package com.helper;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import io.qameta.allure.Step;

import static org.awaitility.Awaitility.await;
import static com.helper.ExeptionWrapper.throwingMsgExeptionConsumerWrapper;


public class EmailUtils {

    public static final Logger log = Logger.getLogger(EmailUtils.class);

    private Properties properties = PropertiesManager.getProperties();

    private Session session = null;
    private Folder folder = null;
    private Store store = null;
    
    @Step("Connect to Gmail Inbox")
    public Folder connectToInbox(String userName, String password, EmailClientType emailClientType) throws MessagingException {
        Properties props = new Properties();
        if (emailClientType.equals(EmailClientType.OUTLOOK)) {
            props.put("mail.imaps.host", properties.getProperty("outlook.imaps.host"));
            props.setProperty("mail.imaps.socketFactory.class", properties.getProperty("outlook.imaps.socketFactory.class"));
            props.setProperty("mail.imaps.socketFactory.fallback", "false");
            props.setProperty("mail.imaps.port", "993");
            props.setProperty("mail.imaps.socketFactory.port", "993");
            props.setProperty("mail.debug", "true");
            session = Session.getInstance(props);
            store = session.getStore("imaps");

        } else {
            props.put("mail.imap.host", properties.getProperty("mail.imap.host"));
            props.put("mail.imap.port", properties.getProperty("mail.imap.port"));
            props.put("mail.imap.socketFactory.class", properties.getProperty("mail.imap.socketFactory.class"));
            props.put("mail.imap.socketFactory.fallback", properties.getProperty("mail.imap.socketFactory.fallback"));
            props.put("mail.imap.socketFactory.port", properties.getProperty("mail.imap.socketFactory.port"));
            session = Session.getInstance(props);
            store = session.getStore("imap");

        }
        store.connect(userName, password);
        folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        return folder;
    }
    
    @Step("Disconnect and close Email")
    public void disconnectInbox() throws MessagingException {
        folder.close();
        store.close();
    }

    @Step("Delete Emails")
    public void deleteEmails() throws MessagingException {
        Message[] messages = getEmailMessages();
        Arrays.stream(messages).forEach(
                throwingMsgExeptionConsumerWrapper(msg -> msg.setFlag(Flags.Flag.DELETED, true)));
    }

    private Message[] getEmailMessages() throws MessagingException {
        return folder.getMessages(1, folder.getMessageCount());
    }

    public Message getEmailMessage() throws MessagingException {
        await("No emails was found").atMost(240, TimeUnit.SECONDS).
                with().pollInterval(5, TimeUnit.SECONDS).until(() ->{
                log.info("Current unread message count: "+ folder.getMessageCount()+", (will retry if it is not greater than 0)");
                return folder.getMessageCount() >0;
                });
        return getFirstEmailMessage();
    }
    
    @Step("Get recent email")
    public Message getRecentEmailMessage() throws MessagingException {
        await("No emails was found").atMost(240, TimeUnit.SECONDS).
                with().pollInterval(5, TimeUnit.SECONDS).until(() ->{
                log.info("Current unread message count: "+ folder.getMessageCount()+", (will retry if it is not greater than 0)");
                return folder.getMessageCount() >0;
                });
        return getMostRecentUnReadMessage();
    }

    private  Message getMostRecentUnReadMessage() throws MessagingException{
        Flags seenFlags = new Flags(Flags.Flag.SEEN);
        Message[] unreadMessages = folder.search(new FlagTerm(seenFlags, false));
        if(unreadMessages!= null && unreadMessages.length >0){
            return unreadMessages[unreadMessages.length - 1];
        }
        return  null;
    }

    private Message getFirstEmailMessage() throws MessagingException {
        Message[] messages = getEmailMessages();
        if (messages != null && messages.length >0){
            return messages[0];
        }
        return null;
    }


    public String getEmailBodyHtml(Message emailMessage) throws MessagingException, IOException {
        String body = null;
        if (emailMessage.getContentType().toLowerCase().contains("multipart")) {
            body = getHtmlBodyPart((Multipart) emailMessage.getContent());
        } else if (emailMessage.getContentType().toLowerCase().contains("text/html")) {
            body = emailMessage.getContent().toString();
        }
        return body;
    }

    private String getHtmlBodyPart(Multipart multipart) throws MessagingException, IOException {
        for (int i = 0; i < multipart.getCount(); i++) {
            BodyPart currentPart = multipart.getBodyPart(i);
            if (currentPart.getContentType().toLowerCase().contains("multipart")) {
                return getHtmlBodyPart((Multipart) currentPart.getContent());
            } else if (currentPart.getContentType().toLowerCase().contains("text/html")) {
                return currentPart.getContent().toString();
            }
        }
        return null;
    }

    public List<String> getEmailUrls(Message emailMessage) throws Exception {
        String html = getEmailBodyHtml(emailMessage);
        List<String> allMatches = new ArrayList<String>();
        Matcher matcher = Pattern.compile("(<a [^>]+>)</a>").matcher(html);
        while (matcher.find()) {
            String aTag = matcher.group(1);
            allMatches.add(aTag.substring(aTag.indexOf("http"), aTag.indexOf("\">")));
        }
        return allMatches;
    }

    public List<BodyPart> getEmailAttachments(Message emailMessage) throws MessagingException, IOException {
        List<BodyPart> attachments = new ArrayList<>();
        if (emailMessage.getContentType().contains("multipart")) {
            Multipart emailMessageMultiPart = (Multipart) emailMessage.getContent();
            for (int i = 0; i < emailMessageMultiPart.getCount(); i++) {
                BodyPart currentPart = emailMessageMultiPart.getBodyPart(i);
                if (Part.ATTACHMENT.equalsIgnoreCase(currentPart.getDisposition())) {
                    attachments.add(currentPart);
                }
            }
        }
        return attachments;
    }
    
    @Step("Send email to twilio agent")
    public void sendEmail(String SenderEmail, String Password, String RecepientEmail, String Subject, String Body) {
        Properties props = new Properties();

        props.put("mail.transport.protocol", properties.getProperty("mail.transport.protocol"));
        props.put("mail.smtp.host", properties.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", properties.getProperty("mail.smtp.port"));
        props.put("mail.smtp.auth", properties.getProperty("mail.smtp.auth"));
        props.put("mail.debug", properties.getProperty("mail.debug"));
        props.put("mail.smtp.socketFactory.class", properties.getProperty("mail.smtp.socketFactory.class"));
        props.put("mail.smtp.socketFactory.fallback", properties.getProperty("mail.smtp.socketFactory.fallback"));
        props.put("mail.smtp.socketFactory.port", properties.getProperty("mail.smtp.socketFactory.port"));

        Session session = Session.getInstance(props);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SenderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(RecepientEmail));
            message.setSubject(Subject);
            message.setText(Body);

            Transport t = session.getTransport();
            t.connect(SenderEmail, Password);

            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException err) {
            err.printStackTrace();

        }
    }

}
