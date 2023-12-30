package org.example;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.Attachment;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import com.resend.services.emails.model.Tag;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws ResendException {
        Resend resend = new Resend("re_ddL5T5Jf_9KS6nyvmRWBy2PRFEuLkCG1Q");

//        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
//                .from("onboarding@resend.dev")
//                .to("jhonatan.ibatac@gmail.com")
//                .subject("Hello World")
//                .html("<p>Congrats on sending your <strong>first email</strong>!</p>")
//                .build();
//
//        SendEmailResponse data = resend.emails().send(sendEmailRequest);

        sendEmail(resend);
    }

    public static void sendEmail(Resend resend) throws ResendException {
        Attachment att = Attachment.builder()
                .fileName("invoice.pdf")
                .content("invoiceBuffer")
                .build();

        Tag tag = Tag.builder()
                .name("category")
                .value("confirm_email")
                .build();

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("jhonatan.ibatac@gmail.com")
                .attachments(att)
                .text("hello world")
                .subject("it works!")
                .headers(Map.of(
                        "X-Entity-Ref-ID", "123456789"
                ))
                .tags(tag)
                .build();

        SendEmailResponse data = resend.emails().send(sendEmailRequest);

    }
}