package com.axonivy.demo;

import com.microsoft.Graph;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.User;

public class TermChat
{

  static IGraphServiceClient createClient()
  {
    String userToken = TermChatAuth.getToken();
    return Graph.clientFor(userToken);
  }

  private final IGraphServiceClient client;
  
  public TermChat()
  {
    client = createClient();
  }
  
  public User whoami()
  {
    return client.me().buildRequest().get();
  }
  
//  public Channel readMessages(String channel)
//  {
//    User user = client.me().buildRequest().get();
//    Channel chan = client.teams(user.id).channels("19:d317ba3f313146a589811dcb4bb946e7").buildRequest().get();
//    return chan;
//  }
  
}
