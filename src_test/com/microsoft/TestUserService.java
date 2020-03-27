package com.microsoft;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.User;

public class TestUserService
{

  @Test
  public void user() 
  {
    IGraphServiceClient client = Graph.clientFor(System.getProperty("myToken"));
    UserService user = new UserService(client);
    User myself = user.getUser();
    assertThat(myself).isNotNull();
    assertThat(myself.mail).endsWith("@axonivy.com");
  }
  
}
