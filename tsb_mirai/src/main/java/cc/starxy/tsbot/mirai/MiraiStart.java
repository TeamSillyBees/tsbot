package cc.starxy.tsbot.mirai;

import cc.starxy.tsbot.mirai.core.MiraiCore;
import cc.starxy.tsbot.mirai.listener.FriendListener;
import cc.starxy.tsbot.mirai.listener.GroupListener;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.Events;


/**
 * Mirai Bot 启动类
 *
 * @author DONG Jixing
 */
public class MiraiStart {

    /**
     * mirai 启动
     * @param miraiUsername bot qq
     * @param miraiPassword bot pwd
     * @param notifyGroup 通知群号
     * @param groupListener 群组消息监听器
     * @param friendListener 私聊消息监听器
     */
    public static void start(Long miraiUsername, String miraiPassword, Long notifyGroup,
                             GroupListener groupListener, FriendListener friendListener) {


        // 设置报错群组
        MiraiCore.getInstance().setErrorNotifyGroup(notifyGroup);
        // Mirai login
        Bot bot = MiraiCore.getInstance().login(miraiUsername, miraiPassword);
        Events.registerEvents(bot, groupListener);
        Events.registerEvents(bot, friendListener);

        // 挂载协程
        bot.join();
    }
}
