package axonivy.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.demo.TermChat;
import com.microsoft.graph.models.extensions.User;

public class TestTermChat
{
  @Test
  public void read()
  {
    TermChat chat = new TermChat();
    User me = chat.whoami();
    assertThat(me.mail).endsWith("@axonivy.com");
  }
}
