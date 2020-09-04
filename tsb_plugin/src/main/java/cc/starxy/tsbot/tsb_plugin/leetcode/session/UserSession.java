package cc.starxy.tsbot.tsb_plugin.leetcode.session;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户 Session 存储
 *
 * @author DONG Jixing
 */
@Data
public class UserSession implements Serializable {
    private String username;
    private String csrfToken;
    private String leetCodeSession;
}
