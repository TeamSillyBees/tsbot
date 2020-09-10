package cc.starxy.tsbot.mirai.commands;

import cc.starxy.tsbot.mirai.commands.facade.CommandFacade;
import cc.starxy.tsbot.mirai.enums.CmdEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 消息门面模式
 *
 * @author DONG Jixing
 */
@Component
public class CommandFactory {
    private final List<CommandFacade> commands;

    public CommandFactory(List<CommandFacade> commands) {
        this.commands = commands;
    }

    /**
     * 返回所有命令的匹配正则
     *
     * @return 正则集合
     */
    public List<Pattern> regx() {
        List<Pattern> patterns = new ArrayList<>();
        for (CommandFacade command : commands) {
            patterns.add(command.regex());
        }
        return patterns;
    }

    /**
     * 根据命令类型返回对应的命令对象
     *
     * @param cmdEnum 标识
     * @return 命令对象
     */
    public CommandFacade get(CmdEnum cmdEnum) {
        for (CommandFacade command : commands) {
            if (command.cmd().equals(cmdEnum)) {
                return command;
            }
        }
        return null;
    }

    /**
     * 根据正则表达式返回对应的命令对象
     *
     * @param pattern 正则表达式
     * @return 命令对象
     */
    public CommandFacade get(Pattern pattern) {
        for (CommandFacade command : commands) {
            if (command.regex().equals(pattern)) {
                return command;
            }
        }
        return null;
    }

}
