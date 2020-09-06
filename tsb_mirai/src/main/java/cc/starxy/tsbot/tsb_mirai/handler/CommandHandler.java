package cc.starxy.tsbot.tsb_mirai.handler;

import cc.starxy.tsbot.tsb_mirai.enums.KeyWord;

/**
 * 指令处理
 * @author DONG Jixing
 */
public class CommandHandler {
    public static String printArgs(KeyWord keyWord){
        String cmd = "";
        switch (keyWord){
            case BOT_HELP:
                cmd = "tsbot 使用指南：\n" +
                        "  指令\n" +
                        "    .j, join  [lc id]\n" +
                        "         加入每日提醒\n" +
                        "    .i, info  [lc id]\n" +
                        "         查看指定id信息\n" +
                        "     .h, help\n" +
                        "         使用帮助\n" +
                        "----------------------\n" +
                        "本项目开源维护，欢迎提交PR\n" +
                        "仓库地址：https://github.com/TeamSillyBees/tsbot";
                break;
            case USER_INFO:
                break;
            case DAILY_QUESTION_JOIN:
                break;
            default:
                break;
        }
        return cmd;
    }
}
