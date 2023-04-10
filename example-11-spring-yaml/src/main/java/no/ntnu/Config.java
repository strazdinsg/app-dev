package no.ntnu;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration object. The values will be injected automatically from the application.yml file.
 * This represents all the config properties with prefix myapp: myapp.port, myapp.servers, etc.
 */
@Component
@ConfigurationProperties(prefix = "myapp")
public class Config {
  private List<String> servers;
  private boolean useAuth;
  private int port;

  public int getPort() {
    return port;
  }

  public List<String> getServers() {
    return servers;
  }

  public void setServers(List<String> servers) {
    this.servers = servers;
  }

  public boolean isUseAuth() {
    return useAuth;
  }

  public void setUseAuth(boolean useAuth) {
    this.useAuth = useAuth;
  }

  public void setPort(int port) {
    this.port = port;
  }
}
