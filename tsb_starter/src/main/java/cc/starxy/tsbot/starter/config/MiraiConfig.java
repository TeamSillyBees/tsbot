package cc.starxy.tsbot.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Mirai 配置类
 * @author DONG Jixing
 */
@ConfigurationProperties(prefix = "mirai")
@Component
public class MiraiConfig {
    Long botId;
    String password;
    Long notifyGroupId;

    public Long getBotId() {
        return botId;
    }

    public void setBotId(Long botId) {
        this.botId = botId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getNotifyGroupId() {
        return notifyGroupId;
    }

    public void setNotifyGroupId(Long notifyGroupId) {
        this.notifyGroupId = notifyGroupId;
    }
}
