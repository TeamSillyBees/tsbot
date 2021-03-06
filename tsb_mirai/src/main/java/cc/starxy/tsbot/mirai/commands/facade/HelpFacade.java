package cc.starxy.tsbot.mirai.commands.facade;

import cc.starxy.tsbot.mirai.enums.CmdEnum;
import cc.starxy.tsbot.mirai.exception.MiraiException;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.At;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 帮助命令
 *
 * @author DONG Jixing
 */
@Component
public class HelpFacade implements CommandFacade {

    @Override
    public CmdEnum cmd() {
        return CmdEnum.BOT_HELP;
    }

    /**
     * .h
     * help
     * .h cmd
     * help cmd
     */
    @Override
    public String regex() {
        return "^(\\.h|help)(\\s([.\\w]+))?$";
    }

    @Override
    public String[] options(String text) {
        // 命令参数在正则匹配 group 中的索引位置
        int indexGroup = 3;
        Pattern pattern = Pattern.compile(this.regex());
        Matcher matcher = pattern.matcher(text);
        if (matcher.find() && matcher.group(indexGroup) != null) {
            return matcher.group(indexGroup).split(" ");
        }
        return new String[0];
    }

    @Override
    public void execute(Contact sender, Group group, String message) {
        String[] opts = this.options(message);
        CmdEnum cmd;
        if (opts.length > 1) {
            throw new MiraiException.OptionNumNotExcept("处理 help 命令时接收预期之外的参数数量");
        } else if (opts.length == 1) {
            cmd = CmdEnum.getByWord(opts[0]);
        } else {
            // 无参数 默认返回 help
            cmd = CmdEnum.BOT_HELP;
        }
        if (cmd == null) {
            throw new MiraiException.OptionNotSupport("处理 help 命令时接收不被支持的的参数");
        }
        String argsHelp = printArgHelp(cmd);
        if (group != null) {
            // 群组消息
            At at = new At(group.get(sender.getId()));
            group.sendMessage(at.plus(argsHelp));
        } else {
            // 非群组消息
        }
    }


    /**
     * 打印指定命令帮助信息
     *
     * @param cmdEnum 命令
     * @return 帮助信息
     */
    public static String printArgHelp(CmdEnum cmdEnum) {
        String cmd;
        switch (cmdEnum) {
            default:
                cmd = "tsbot 使用指南：\n" +
                        "  @[机器人] <命令> [参数]\n" +
                        "命令:\n" +
                        "  .b, bind  <lc id>\n" +
                        "    将调用者和给定 lc id 绑定\n" +
                        "  .dn, daily_notice [lc id]\n" +
                        "    加入每日一题提醒\n" +
                        "  .i, info  [lc id]\n" +
                        "    查看指定 id 信息\n" +
                        "  .h, help [cmd]\n" +
                        "    查看命令使用帮助\n" +
                        "----------------------\n" +
                        "本项目开源维护，欢迎提交PR\n" +
                        "仓库地址：https://github.com/TeamSillyBees/tsbot";
                break;
            case USER_INFO:
                cmd = ".i, info [lc id]\n" +
                        "作用：\n" +
                        "  查询给定的 leetcode 用户的个人信息，包括连续打卡记录、每日一题完成情况、个人资料、总体试题提交及通过情况\n" +
                        "  不给出 leetcode id 则默认查询调用者绑定的账号\n" +
                        "样例：\n" +
                        "  @[机器人] info starxy\n" +
                        "  @[机器人] .i starxy\n" +
                        "  @[机器人] info \n" +
                        "----------------------\n" +
                        "当前功能未完成，欢迎提交PR\n" +
                        "仓库地址：https://github.com/TeamSillyBees/tsbot";
                break;
            case USER_BIND:
                cmd = ".b, bind [lc id]\n" +
                        "作用：\n" +
                        "  将给定的 lc id 绑定到当前调用者，重复调用以最后一次的id为准\n" +
                        "样例：\n" +
                        "  @[机器人] .b starxy\n" +
                        "  @[机器人] bind starxy\n";
                break;
            case DAILY_NOTICE:
                cmd = ".dn, daily_notice [lc id]\n" +
                        "作用：\n" +
                        "  每日晚6点半，检测用户是否完成每日一题，未完成则提醒\n" +
                        "  如果传入 leetcode id 则将给定 id 与调用者绑定并每日提醒每日一题的完成情况\n" +
                        "  如果未传入 lc id，则需要提前使用 bind 命令绑定\n" +
                        "样例：\n" +
                        "  @[机器人] .dn starxy\n" +
                        "  @[机器人] daily_notice starxy\n" +
                        "  @[机器人] daily_notice\n";
                break;
        }
        return cmd;
    }
}
