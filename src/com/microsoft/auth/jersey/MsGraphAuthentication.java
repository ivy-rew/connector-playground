package com.microsoft.auth.jersey;

import java.util.Set;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.FeatureContext;

import ch.ivyteam.ivy.environment.Ivy;

public class MsGraphAuthentication implements javax.ws.rs.core.Feature
{
  public static interface Property
  {
    String APP_ID="ms.appId";
    String SCOPES="ms.scopes";
  }

  @Override
  public boolean configure(FeatureContext context)
  {
    String appId = parseApp(context.getConfiguration());
    Set<String> scopes = parseScope(context.getConfiguration());
    
    GraphDeviceTokenFilter graphAuth = new GraphDeviceTokenFilter(appId, scopes, Ivy.log());
    context.register(graphAuth);
    
    return true;
  }
  
  private static String parseApp(Configuration config)
  {
    Object val = config.getProperty(Property.APP_ID);
    if (val instanceof String)
    {
      return ((String) val);
    }
    throw new IllegalStateException("Property '"+Property.APP_ID+"' must refer must contain a graph client id");
  }

  private static Set<String> parseScope(Configuration config)
  {
    Object val = config.getProperty(Property.SCOPES);
    if (val instanceof String)
    {
      return Set.of(((String) val).split(","));
    }
    throw new IllegalStateException("Expecting feature property '"+Property.SCOPES+"'"
            + " to contain comma separated scopes such as 'User.Read'");
  }
}
