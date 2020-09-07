package cc.starxy.tsbot.tsb_mirai.listener;

import cc.starxy.tsbot.tsb_mirai.enums.KeyWord;
import cc.starxy.tsbot.tsb_mirai.handler.CommandHandler;
import cc.starxy.tsbot.tsb_mirai.handler.MessageHandler;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.PlainText;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * @author nanke
 * @date 2020/6/28 下午2:22
 */
@Service
public class FriendListener extends SimpleListenerHost {

    /**
     * Java方法级别注解,标注一个方法为事件监听器
     *
     * @param event
     */
    @EventHandler
    public void onMessage(FriendMessageEvent event) {

    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        MessageHandler.sendErrorMessage("监听到好友消息，处理失败",exception);
    }
}
