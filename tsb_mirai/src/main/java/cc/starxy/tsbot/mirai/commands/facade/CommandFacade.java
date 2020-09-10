package cc.starxy.tsbot.mirai.commands.facade;

import cc.starxy.tsbot.mirai.enums.CmdEnum;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;


/**
 * 门面模式接口
 *
 * @author DONG Jixing
 */
public interface CommandFacade {

    /**
     * 返回当前命令标识
     *
     * @return KeyWord
     */
    CmdEnum cmd();

    /**
     * 返回用于匹配当前命令的正则表达式
     *
     * @return 构建好的正则 Pattern 对象
     */
    String regex();

    /**
     * 获取根据当前正则表达式匹配到的参数列表
     *
     * @param text 请求命令
     * @return 参数列表
     */
    String[] options(String text);

    /**
     * 命令执行
     *
     * @param sender  命令调用者
     * @param group   调用者所在群组 null 表示为私聊消息
     * @param message 消息内容
     */
    void execute(Contact sender, Group group, String message);

}
