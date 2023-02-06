package no.ntnu;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Configuration object. The values will be injected automatically from the application.yml file
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
