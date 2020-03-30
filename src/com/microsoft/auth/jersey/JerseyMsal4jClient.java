package com.microsoft.auth.jersey;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import com.microsoft.aad.msal4j.HttpMethod;
import com.microsoft.aad.msal4j.HttpRequest;
import com.microsoft.aad.msal4j.HttpResponse;
import com.microsoft.aad.msal4j.IHttpClient;
import com.microsoft.aad.msal4j.IHttpResponse;

/**
 * Jersey JAX-RS implementation of {@link IHttpClient}.
 * 
 * Used to execute authentication requests towards Microsoft Graph
 * with minimal additional library dependencies and log/monitoring
 * support from the ivy-core.
 * 
 * @author rew
 */
public class JerseyMsal4jClient implements IHttpClient
{
  private final Client jaxRsClient;

  public JerseyMsal4jClient()
  {
    this(ClientBuilder.newClient());
  }
  
  public JerseyMsal4jClient(Client jaxRsClient)
  {
    this.jaxRsClient = jaxRsClient;
  }
  
  @Override
  public IHttpResponse send(HttpRequest httpRequest) throws Exception
  {
    Builder request = toJerseyRequest(httpRequest);
    
    Entity<?> e = Entity.text(httpRequest.body());
    if (httpRequest.body() != null && httpRequest.httpMethod() == HttpMethod.POST)
    {
      e = toFormEntity(httpRequest);
    }
    Response response = request.method(httpRequest.httpMethod().name(), e);
    
    IHttpResponse resp = toMsal4jResponse(response);
    return resp;
  }

  private Builder toJerseyRequest(HttpRequest httpRequest)
  {
    WebTarget target = jaxRsClient.target(httpRequest.url().toString());
    Builder request = target.request();
    for(Entry<String, String> header : httpRequest.headers().entrySet())
    {
      request.header(header.getKey(), header.getValue());
    }
    return request;
  }

  private static Entity<?> toFormEntity(HttpRequest httpRequest)
  {
    Form form = new Form();
    Arrays.stream(httpRequest.body().split("&"))
      .forEach(param -> {
        String[] parts = param.split("=");
        form.param(parts[0], parts[1]);
      });
    return Entity.form(form);
  }
  
  @SuppressWarnings({"unchecked", "rawtypes"})
  private static IHttpResponse toMsal4jResponse(Response jaxRS)
  {
    HttpResponse msal4j = new HttpResponse();
    msal4j.statusCode(jaxRS.getStatus());
    for(Entry<String, List<Object>> header : jaxRS.getHeaders().entrySet())
    {
      msal4j.headers().put(header.getKey(), (List) header.getValue());
    }
    msal4j.body(jaxRS.readEntity(String.class));
    return msal4j;
  }
}
