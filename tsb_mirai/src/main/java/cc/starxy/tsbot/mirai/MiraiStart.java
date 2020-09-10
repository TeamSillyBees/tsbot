package cc.starxy.tsbot.mirai;

import cc.starxy.tsbot.mirai.core.MiraiCore;
import cc.starxy.tsbot.mirai.listener.FriendListener;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.Events;


/**
 * Mirai Bot 启动类
 *
 * @author DONG Jixing
 */
public class MiraiStart {

    public static void start(Long miraiUsername, String miraiPassword,Long notifyGroup) {


        // 设置报错群组
        MiraiCore.getInstance().setErrorNotifyGroup(notifyGroup);
        // Mirai login
        Bot bot = MiraiCore.getInstance().login(miraiUsername, miraiPassword);
//        Events.registerEvents(bot, new GroupListener());
        Events.registerEvents(bot, new FriendListener());

        // 挂载协程
        bot.join();
    }
}
