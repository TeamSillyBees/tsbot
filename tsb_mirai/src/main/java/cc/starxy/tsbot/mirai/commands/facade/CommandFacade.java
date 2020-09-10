package cc.starxy.tsbot.mirai.commands.facade;

import cc.starxy.tsbot.mirai.enums.CmdEnum;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.Message;

import java.util.regex.Pattern;

/**
 * 门面模式接口
 * @author DONG Jixing
 */
public interface CommandFacade {

    /**
     * 返回当前命令标识
     * @return KeyWord
     */
    CmdEnum cmd();

    /**
     * 返回用于匹配当前命令的正则表达式
     * @return 构建好的正则 Pattern 对象
     */
    Pattern regex();

    /**
     * 命令执行
     * @param sender 命令调用者
     * @param group 调用者所在群组 null 表示为私聊消息
     * @param message 消息内容
     */
    void execute(Contact sender, Contact group, Message message);
}
