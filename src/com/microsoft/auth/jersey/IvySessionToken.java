package com.microsoft.auth.jersey;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IWorkflowSession;

public class IvySessionToken
{
  private static final String TOKEN = "token";
  private final String qualifier;
  private final IWorkflowSession session;
  
  public IvySessionToken(String service)
  {
    this.session = Ivy.session();
    this.qualifier = service+"."+TOKEN;
  }
  
  public String getToken()
  {
    return (String) session.getAttribute(qualifier);
  }
  
  public void setToken(String token)
  {
    if (token == null)
    {
      session.removeAttribute(qualifier);
    }
    session.setAttribute(qualifier, token);
  }
}