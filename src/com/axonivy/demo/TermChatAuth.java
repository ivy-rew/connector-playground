package com.axonivy.demo;

import com.microsoft.auth.Authority;
import com.microsoft.auth.TmpFileToken;

public class TermChatAuth
{
  private static final String APP_ID = "0960abf3-45f9-4ba3-8975-e2b25a5178e3";
  
  private static String token = null;
  private static TmpFileToken store = new TmpFileToken();
  
  public static String getToken()
  {
    if (token == null)
    {
      if (!store.exists())
      {
        token = resolveTermChatToken();
        store.write(token);
      }
      token = store.read();
    }
    return token;
  }
  
  private static String resolveTermChatToken()
  {
    // https://portal.azure.com/#blade/Microsoft_AAD_RegisteredApps/ApplicationMenuBlade/CallAnAPI/appId/0960abf3-45f9-4ba3-8975-e2b25a5178e3/objectId/df261ada-ba24-4a5a-bf26-51e47721fdde/isMSAApp//defaultBlade/Overview/servicePrincipalCreated/true
    return Authority.getUserAccessToken(APP_ID, 
      new String[] {
          "User.Read", "User.ReadBasic.All", 
          "Chat.ReadWrite", "ChannelMessage.Read.All"
      });
  }
  
}
