package cc.starxy.tsbot.tsb_mirai.listener;

import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;


/**
 * 群组消息监听器
 * @author DONG Jixing
 */
public class GroupListener extends SimpleListenerHost {

    /**
     * 消息监听
     * @param event 消息事件
     */
    @EventHandler
    public void onMessage(GroupMessageEvent event) {
        //todo 处理群组消息
    }


    /**
     * 消息事件处理异常回调
     * @param context 上下文调度器
     * @param exception 异常
     */
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        // todo 收到异常后给开发者发送消息
    }
}
