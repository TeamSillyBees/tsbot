package cc.starxy.tsbot.tsb_mirai.utils;

import cc.starxy.tsbot.tsb_mirai.core.MiraiCore;
import net.mamoe.mirai.Bot;

import java.util.Date;

/**
 * 消息工具类
 *
 * @author DONG Jixing
 */
public class MessageUtils {

    /**
     * 处理报错信息
     *
     * @param errorMsg 报错提示
     * @param e        错误堆栈
     */
    public static void sendErrorMessage(String errorMsg, Throwable e) {
        MiraiCore miraiCore = MiraiCore.getInstance();
        Bot bot = miraiCore.getBot();
        String msg = new Date() + "\n" +
                "----------------------\n" +
                errorMsg;
        if (e != null) {
            msg += "\n" +
                    "----------------------\n" +
                    e.getMessage();
        }
        bot.getGroup(miraiCore.getErrorNotifyGroup()).sendMessage(msg);
    }

}
