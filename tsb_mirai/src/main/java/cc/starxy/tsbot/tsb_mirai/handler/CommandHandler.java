package cc.starxy.tsbot.tsb_mirai.handler;

import cc.starxy.tsbot.tsb_mirai.enums.KeyWord;

/**
 * 指令处理
 *
 * @author DONG Jixing
 */
public class CommandHandler {

    /**
     * 打印指定命令帮助信息
     * @param keyWord 命令
     * @return 帮助信息
     */
    public static String printArgHelp(KeyWord keyWord) {
        String cmd = "";
        switch (keyWord) {
            case BOT_HELP:
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
            default:
                break;
        }
        return cmd;
    }
}
