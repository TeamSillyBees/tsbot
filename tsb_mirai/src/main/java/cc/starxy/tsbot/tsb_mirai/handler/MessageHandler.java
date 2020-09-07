package cc.starxy.tsbot.tsb_mirai.handler;

import cc.starxy.tsbot.tsb_mirai.core.MiraiCore;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.message.data.PlainText;

import java.util.Date;

/**
 * 机器人消息处理
 *
 * @author DONG Jixing
 */
public class MessageHandler {

    public static String response(PlainText text) {
        return null;
    }

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
            msg += "----------------------\n" +
                    e.getMessage();
        }
        bot.getGroup(miraiCore.getErrorNotifyGroup()).sendMessage(msg);
    }
}
