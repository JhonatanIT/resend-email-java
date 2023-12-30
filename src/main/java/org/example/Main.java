package org.example;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.batch.model.CreateBatchEmailsResponse;
import com.resend.services.emails.model.*;

import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws ResendException {

        //Use a Resend API Key
        Resend resend = new Resend("re_ddL5T5Jf_9KS6nyvmRWBy2PRFEuLkCG1Q");

        SendEmailResponse data = sendEmail(resend);
        System.out.println(STR."Email sent: \{data.getId()}");

//        Email email = retrieveEmail(resend, data.getId());
//        System.out.println(STR."Email retrieved: \{email}");

        CreateBatchEmailsResponse dataBatch = sendBatchEmails(resend);
        dataBatch.getData().forEach(x -> System.out.println(STR."Email batch sent: \{x.getId()}"));

    }

    public static SendEmailResponse sendEmail(Resend resend) throws ResendException {
        Attachment att = Attachment.builder()
                .fileName("invoice.pdf")
                .content("invoiceBuffer")
                .build();

        Tag tag = Tag.builder()
                .name("category")
                .value("confirm_email")
                .build();

        /*
         * Test delivered emails: delivered@resend.dev
         * Test bounced emails: bounced@resend.dev
         * Test “Marked as Spam” emails: complained@resend.dev
         */
        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("delivered@resend.dev")
                .attachments(att)
                .text("hello world GAAAAAAAAAA")
                .subject("it works!")
                .headers(Map.of(
                        "X-Entity-Ref-ID", "123456789"
                ))
                .tags(tag)
                .build();

        return resend.emails().send(sendEmailRequest);
    }

    public static Email retrieveEmail(Resend resend, String emailId) throws ResendException {
//        return resend.emails().get("acbde81a-0455-4514-b144-7f52fd69332c");
        return resend.emails().get(emailId);
    }

    public static CreateBatchEmailsResponse sendBatchEmails(Resend resend) throws ResendException {

        SendEmailRequest firstEmailRequest = SendEmailRequest.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("jhonatan.ibatac@gmail.com")
                .subject("hello world 1")
                .html("<h1>it works 1!</h1>")
                .build();

        SendEmailRequest secondEmailRequest = SendEmailRequest.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("jhonatan.ibatac@gmail.com")
                .subject("world hello 2")
                .html("<p>it works 2!</p>")
                .build();

        return resend.batch().send(
                Arrays.asList(firstEmailRequest, secondEmailRequest)
        );
    }

}