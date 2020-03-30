package com.microsoft.auth.jersey;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import org.junit.jupiter.api.Test;

import com.microsoft.TestAuthentication;
import com.microsoft.graph.models.extensions.User;

import ch.ivyteam.ivy.application.IProcessModelVersion;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest
public class TestJerseyMsAuth
{
  
  @Test
  public void authWithJersey(IProcessModelVersion pmv)
  {
    WebTarget webTarget = ivyRestClient(pmv, "msGraph")
            .resolveTemplate("api.version", "v1.0");
    assertThat(webTarget).isNotNull();
    Builder graphRequest = webTarget.path("me").request();
    User profile = graphRequest.get(User.class);
    assertThat(profile).isNotNull();
    assertThat(profile.mail).endsWith("@axonivy.com");
  }

  /**
   * hack to simulate: Ivy.rest().client("msGraph");
   */
  @SuppressWarnings("all")
  private static WebTarget ivyRestClient(IProcessModelVersion pmv, String clientName)
  {
    ch.ivyteam.ivy.application.restricted.di.ProcessModelVersionContext.push(pmv);
    ch.ivyteam.ivy.rest.client.internal.RestWebServiceApplicationContext appContxt = 
            new ch.ivyteam.ivy.rest.client.internal.RestWebServiceApplicationContext(pmv.getApplication());
    WebTarget webTarget = appContxt.getRestWebService(clientName).createCall().getWebTarget();
    return webTarget;
  }
  
  @Test
  public void jerseyHttpClientForMSAL4j()
  {
    String token = JerseyAuthority.getUserAccessToken(
            TestAuthentication.TERM_CHAT_ID, 
            TestAuthentication.SCOPES);
    System.out.println(token);
    assertThat(token).isNotNull();
  }
  
}
