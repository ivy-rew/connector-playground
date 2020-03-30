package com.microsoft;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.microsoft.auth.Authority;

public class TestAuthentication
{
  public static final String[] SCOPES = new String[] {"User.Read"}; // Chat.Read
  public static final String TERM_CHAT_ID = "0960abf3-45f9-4ba3-8975-e2b25a5178e3";
  
  @Test
  public void auth()
  {
    String token = Authority.getUserAccessToken(TERM_CHAT_ID, SCOPES);
    System.out.println(token);
    assertThat(token).isNotEmpty();
  }
  
}
