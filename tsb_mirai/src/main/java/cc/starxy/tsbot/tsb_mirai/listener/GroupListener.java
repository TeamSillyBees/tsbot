package cc.starxy.tsbot.tsb_mirai.listener;

import cc.starxy.tsbot.tsb_mirai.commands.CommandFactory;
import cc.starxy.tsbot.tsb_mirai.core.MiraiCore;
import cc.starxy.tsbot.tsb_mirai.enums.KeyWord;
import cc.starxy.tsbot.tsb_mirai.utils.CommandHandler;
import cc.starxy.tsbot.tsb_mirai.utils.MessageUtils;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 群组消息监听器
 *
 * @author DONG Jixing
 */
public class GroupListener extends SimpleListenerHost {

    @Autowired
    private CommandFactory commandFactory;

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
        boolean atFlag = messages.first(At.Key) != null && messages.first(At.Key).getTarget() == bot.getId();
        At at = new At(event.getGroup().get(1L));
        if(atFlag){

        }else {
            // 忽略消息
            return;
        }
        event.getGroup().sendMessage(new PlainText(CommandHandler.printArgHelp(KeyWord.BOT_HELP)));
    }


    /**
     * 消息事件处理异常回调
     *
     * @param context   上下文调度器
     * @param exception 异常
     */
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        MessageUtils.sendErrorMessage("监听到群组消息，处理失败",exception);
    }
}
