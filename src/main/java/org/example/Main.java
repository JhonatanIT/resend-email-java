package org.example;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;

public class Main {
    public static void main(String[] args) throws ResendException {
        Resend resend = new Resend("re_ddL5T5Jf_9KS6nyvmRWBy2PRFEuLkCG1Q");

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("onboarding@resend.dev")
                .to("jhonatan.ibatac@gmail.com")
                .subject("Hello World")
                .html("<p>Congrats on sending your <strong>first email</strong>!</p>")
                .build();

        SendEmailResponse data = resend.emails().send(sendEmailRequest);
    }
}