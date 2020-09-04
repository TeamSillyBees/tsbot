package cc.starxy.tsbot.tsb_mirai;

import cc.starxy.tsbot.tsb_mirai.core.MiraiBot;
import cc.starxy.tsbot.tsb_plugin.leetcode.session.Login;

import java.io.IOException;

/**
 * Mirai Bot 启动类
 *
 * @author DONG Jixing
 */
public class MiraiStart {

    public static void start(String lcUsername, String lcPassword, Long miraiUsername, String miraiPassword) {

        // leetcode login
        Login login = new Login(lcUsername, lcPassword);
        try {
            login.doLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MiraiBot bot = MiraiBot.getInstance();
        // Mirai login
        bot.getBot(miraiUsername, miraiPassword).login();
    }
}
