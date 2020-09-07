package cc.starxy.tsbot.tsb_mirai.core;

import lombok.Getter;
import lombok.Setter;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;

/**
 * MiraiBot 单例模式
 *
 * @author DONG Jixing
 */
public class MiraiCore {

    private static final MiraiCore MIRAI_BOT = new MiraiCore();
    /**
     * 错误通知群
     */
    @Getter @Setter
    private long errorNotifyGroup;
    @Getter
    private Bot bot = null;

    public static MiraiCore getInstance() {
        return MIRAI_BOT;
    }

    public Bot login(Long qq, String password) {
        if (bot == null) {
            bot = BotFactoryJvm.newBot(qq, password);
            bot.login();
        }
        return bot;
    }
}
