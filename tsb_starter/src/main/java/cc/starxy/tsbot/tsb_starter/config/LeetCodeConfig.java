package cc.starxy.tsbot.tsb_starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * LeetCode 配置类
 * @author DONG Jixing
 */
@ConfigurationProperties(prefix = "leetcode")
@Component
public class LeetCodeConfig {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
