package cc.starxy.tsbot.mirai.listener;

import cc.starxy.tsbot.mirai.commands.CommandFactory;
import cc.starxy.tsbot.mirai.commands.facade.CommandFacade;
import cc.starxy.tsbot.mirai.commands.facade.HelpFacade;
import cc.starxy.tsbot.mirai.core.MiraiCore;
import cc.starxy.tsbot.mirai.enums.CmdEnum;
import cc.starxy.tsbot.mirai.exception.MiraiException;
import cc.starxy.tsbot.mirai.utils.MessageUtils;
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

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


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
        // 判断是否 At 了机器人
        boolean atBotCheck = messages.first(At.Key) != null && messages.first(At.Key).getTarget() == bot.getId();
        PlainText text = messages.first(PlainText.Key);
        if (atBotCheck && text != null) {
            // 拿字符串跟已有的命令匹配
            List<Pattern> list = commandFactory.regx().stream().filter(pattern -> {
                CharSequence input;
                Matcher matcher = pattern.matcher(text.toString());
                return matcher.find();
            }).collect(Collectors.toList());
            if (list.size() != 1) {
                CommandFacade command = commandFactory.get(list.get(0));
                command.execute(event.getSender(), event.getGroup(), messages);
            } else {
                // 文本符合不止一个命令类型 发送错误信息，以优化正则表达式
                StringBuilder msg = new StringBuilder(
                        "出现了命令文本符合多个正则表达式的异常\n" +
                                "请求文本：\n" +
                                text.toString() + "\n" +
                                "----------------------\n" +
                                "正则匹配到的命令：\n");
                for (Pattern pattern : list) {
                    msg.append(commandFactory.get(pattern).cmd().getDescription());
                    msg.append("\n");
                }
                throw new MiraiException.MultiPattern(msg.toString());
            }
        } else if (!atBotCheck) {
            // 没有 At 机器人，正常群聊消息，忽略
            return;
        } else if (text == null) {
            // At 了机器人，但是没有给出任何指令。打印默认消息
            event.getGroup().sendMessage(new PlainText(HelpFacade.printArgHelp(CmdEnum.BOT_HELP)));
        } else {
            // 其他情况
            return;
        }
    }


    /**
     * 消息事件处理异常回调
     *
     * @param context   上下文调度器
     * @param exception 异常
     */
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        MessageUtils.sendErrorMessage("监听到群组消息，处理失败", exception);
    }
}
