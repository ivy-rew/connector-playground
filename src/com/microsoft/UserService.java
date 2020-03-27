package com.microsoft;

import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.User;

public class UserService
{
  private final IGraphServiceClient client;

  public UserService(IGraphServiceClient client)
  {
    this.client = client;
  }
  
  public User getUser() {
    User me = client.me().buildRequest().get();
    return me;
  }
}
