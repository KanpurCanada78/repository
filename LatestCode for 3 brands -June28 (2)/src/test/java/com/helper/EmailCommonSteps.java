package com.helper;


import io.qameta.allure.Step;

import org.apache.log4j.Logger;
import org.testng.Assert;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import java.util.List;

public class EmailCommonSteps{

    private static final Logger log = Logger.getLogger(EmailCommonSteps.class);
    EmailUtils emailUtils;

    public EmailCommonSteps(EmailUtils emailUtils){
        this.emailUtils = emailUtils;
    }


    @Step("Check Email message")
    public void checkEmailMessage(Message emailMessage, String expectedBody) throws Exception {
        log.info("Email Message content type:" + emailMessage.getContentType());
        log.info("Email message:"+emailMessage.getContent());
        Assert.assertTrue(emailMessage.getContent().toString().trim().contains(expectedBody));
    }

    @Step("Check content in Email HTML")
    public void checkEmailContentInHTML(Message emailMessage, String expectedContent) throws Exception {
        Assert.assertTrue(emailUtils.getEmailBodyHtml(emailMessage).contains(expectedContent));
    }

    @Step("Check Email Attachments")
    public void checkEmailAttachments(Message emailMessage, List<String> expectedAttachments) throws Exception {
        List<BodyPart> attachments = emailUtils.getEmailAttachments(emailMessage);
        for (BodyPart attachment : attachments){
            Assert.assertTrue(expectedAttachments.contains(attachment.getFileName()), "Verifying the attachment name");
            Assert.assertTrue(attachment.getSize() > 0, "Verify the size of the current part");
        }
    }

    @Step("Check Email subject")
    public void checkEmailSubject(Message emailMessage, String expectedSubject) throws Exception {
        Assert.assertEquals(emailMessage.getSubject(), expectedSubject);
    }

    @Step("Check Email sender")
    public void checkEmailSender(Message emailMessage, String expectedSender) throws Exception {
        Assert.assertEquals(((InternetAddress)emailMessage.getFrom()[0]).getAddress(), expectedSender);
    }
}
