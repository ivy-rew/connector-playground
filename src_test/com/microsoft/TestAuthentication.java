package com.microsoft;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.microsoft.auth.Authority;

public class TestAuthentication
{
  private static final String[] SCOPES = new String[] {"User.Read"}; // Chat.Read

  @Test
  public void auth()
  {
    String termChatId = "0960abf3-45f9-4ba3-8975-e2b25a5178e3";
    String token = Authority.getUserAccessToken(termChatId, SCOPES);
    System.out.println(token);
    assertThat(token).isNotEmpty();
  }
  
}
