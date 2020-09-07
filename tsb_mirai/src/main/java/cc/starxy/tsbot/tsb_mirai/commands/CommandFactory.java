package cc.starxy.tsbot.tsb_mirai.commands;

import cc.starxy.tsbot.tsb_mirai.enums.KeyWord;
import cc.starxy.tsbot.tsb_mirai.commands.facade.CommandFacade;
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
    public List<Pattern> cmdRegex() {
        List<Pattern> patterns = new ArrayList<>();
        for (CommandFacade command : commands) {
            patterns.add(command.regex());
        }
        return patterns;
    }

    /**
     * 根据命令类型返回对应的命令对象
     *
     * @param keyWord 标识
     * @return 命令对象
     */
    public CommandFacade get(KeyWord keyWord) {
        for (CommandFacade command : commands) {
            if (command.cmd().equals(keyWord)) {
                return command;
            }
        }
        return null;
    }
}
