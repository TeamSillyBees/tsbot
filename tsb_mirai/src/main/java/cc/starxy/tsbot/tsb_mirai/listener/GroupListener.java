package cc.starxy.tsbot.tsb_mirai.listener;

import cc.starxy.tsbot.tsb_mirai.core.MiraiCore;
import cc.starxy.tsbot.tsb_mirai.enums.KeyWord;
import cc.starxy.tsbot.tsb_mirai.handler.CommandHandler;
import cc.starxy.tsbot.tsb_mirai.handler.MessageHandler;
import com.sun.org.apache.xpath.internal.operations.Bool;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;
import net.sf.jsqlparser.statement.select.First;
import org.jetbrains.annotations.NotNull;


/**
 * 群组消息监听器
 *
 * @author DONG Jixing
 */
public class GroupListener extends SimpleListenerHost {

    /**
     * 消息监听
     *
     * @param event 消息事件
     */
    @EventHandler
    public void onMessage(GroupMessageEvent event) {
        //todo 处理群组消息
        Bot bot = MiraiCore.getInstance().getBot();
        MessageChain messages = event.getMessage();
        Boolean atFlag = messages.first(At.Key) != null && messages.first(At.Key).getTarget() == bot.getId();
        Boolean startWithTsbotFlag = messages.first(PlainText.Key).getContent().startsWith(KeyWord.TSBOT.getDescription());
        event.getGroup().sendMessage(new PlainText(CommandHandler.printArgs(KeyWord.BOT_HELP)));
    }


    /**
     * 消息事件处理异常回调
     *
     * @param context   上下文调度器
     * @param exception 异常
     */
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        // todo 收到异常后给开发者发送消息
    }
}
