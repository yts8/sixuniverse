package com.yts8.sixuniverse.mail;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendMailgun {

  @Value("${spring.mailgun.base.domain}")
  private String MAILGUN_DOMAIN;
  @Value("${spring.mailgun.api}")
  private String MAILGUN_API;

  @Value("${spring.mailgun.to.email}")
  private String TO_EMAIL;


  public void sendMail(String email, String subject, String text) throws UnirestException {
    HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + MAILGUN_DOMAIN + "/messages")
        .basicAuth("api", MAILGUN_API)
        .queryString("from", TO_EMAIL)
        .queryString("to", email)
        .queryString("subject", subject)
        .queryString("text", text)
        .asJson();
    request.getBody();
  }

}

