package com.microsoft.auth;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class TmpFileToken
{
  private static File store = new File(System.getProperty("java.io.tmpdir"), "termChatToken.txt");
  
  public boolean exists()
  {
    return store.exists();
  }
  
  public String read()
  {
    try 
    {
      return Files.readString(store.toPath());
    } catch (IOException ex)
    {
      throw new RuntimeException("Failed to read token from "+store, ex);
    }
  }
  
  public void write(String token)
  {
    try
    {
      Files.writeString(store.toPath(), token, StandardOpenOption.CREATE_NEW);
    }
    catch (IOException ex)
    {
      throw new RuntimeException("Failed to write temp token to "+store, ex);
    }
  }
  
}
