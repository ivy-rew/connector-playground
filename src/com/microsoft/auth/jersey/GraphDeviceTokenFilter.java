package com.microsoft.auth.jersey;

import java.io.IOException;
import java.util.Set;
import java.util.function.Consumer;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import com.microsoft.aad.msal4j.DeviceCode;
import com.microsoft.aad.msal4j.DeviceCodeFlowParameters;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.microsoft.aad.msal4j.PublicClientApplication;

import ch.ivyteam.api.API;
import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.log.Logger;

class GraphDeviceTokenFilter implements ClientRequestFilter
{
  private final String appId;
  private final Set<String> scopes;
  private final Logger log;

  GraphDeviceTokenFilter(String appId, Set<String> scopes, Logger log)
  {
    API.checkParameterNotBlank(appId, "appId");
    API.checkParameterNotEmpty(scopes, "scopes");
    this.appId = appId;
    this.scopes = scopes;
    this.log = log;
  }
  
  @Override
  public void filter(ClientRequestContext context) throws IOException
  {
    PublicClientApplication app = PublicClientApplication.builder(appId)
     .httpClient(new JerseyMsal4jClient())
     .build();

    // Create consumer to receive the DeviceCode object
    // This method gets executed during the flow and provides
    // the URL the user logs into and the device code to enter
    Consumer<DeviceCode> deviceCodeConsumer = (DeviceCode deviceCode) -> {
        // Print the login information to the console
      BpmError.create("ms:graph:usertoken")
        .withAttribute("deviceCode", deviceCode)
        .throwError();
      log.info(deviceCode.message());
    };

    // Request a token, passing the requested permission scopes
    IAuthenticationResult result = app.acquireToken(
        DeviceCodeFlowParameters.builder(scopes, deviceCodeConsumer).build()
    ).exceptionally(ex -> {
        log.error("Unable to authenticate - " + ex.getMessage());
        return null;
    }).join();

    if (result != null) {
      log.info("got token.... "+result.accessToken());
      context.getHeaders().add("Authorization", "Bearer "+result.accessToken());
    }
  }
}