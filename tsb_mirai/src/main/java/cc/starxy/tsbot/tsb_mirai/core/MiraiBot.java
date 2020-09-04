package cc.starxy.tsbot.tsb_mirai.core;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;

/**
 * MiraiBot 单例模式
 * @author DONG Jixing
 */
public class MiraiBot {

    private static final MiraiBot MIRAI_BOT = new MiraiBot();
    private Bot bot = null;

    public static MiraiBot getInstance() {
        return MIRAI_BOT;
    }

    public Bot getBot(Long qq, String password) {
        if (bot == null) {
            bot = BotFactoryJvm.newBot(qq, password);
        }
        return bot;
    }

}
