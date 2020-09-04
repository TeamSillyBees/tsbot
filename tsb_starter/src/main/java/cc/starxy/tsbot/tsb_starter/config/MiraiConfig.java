package cc.starxy.tsbot.tsb_starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Mirai 配置类
 * @author DONG Jixing
 */
@ConfigurationProperties(prefix = "mirai")
@Component
public class MiraiConfig {
    Long username;
    String password;

    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
