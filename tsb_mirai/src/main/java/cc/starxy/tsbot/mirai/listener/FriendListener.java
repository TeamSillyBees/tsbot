package cc.starxy.tsbot.mirai.listener;

import cc.starxy.tsbot.mirai.utils.MessageUtils;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.FriendMessageEvent;
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
        MessageUtils.sendErrorMessage("监听到好友消息，处理失败",exception);
    }
}
