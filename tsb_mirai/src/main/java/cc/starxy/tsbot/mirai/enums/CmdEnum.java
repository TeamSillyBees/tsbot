package cc.starxy.tsbot.mirai.enums;

/**
 * 关键词
 *
 * @author DONG Jixing
 */

public enum CmdEnum {

    /**
     * 帮助
     */
    BOT_HELP("帮助", ".h", "help"),

    /**
     * 绑定
     */
    USER_BIND("绑定", ".b", "bind"),

    /**
     * 添加每日一题提醒
     */
    DAILY_NOTICE("每日一题提醒", ".dn", "daily_notice"),

    /**
     * 查看用户信息
     */
    USER_INFO("查看用户信息", ".i", "info");

    CmdEnum(String description, String... words) {
        this.description = description;
        this.words = words;
    }

    /**
     * 指令描述
     */
    private final String description;
    /**
     * 操作指令关键字
     */
    private final String[] words;

    /**
     * 根据字符判断命令
     * @param word 提供字符
     * @return 命令
     */
    public static CmdEnum getByWord(String word) {
        for (CmdEnum cmd : CmdEnum.values()) {
            for (String cmdWord : cmd.getWords()) {
                if (word.equals(cmdWord)) {
                    return cmd;
                }
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public String[] getWords() {
        return words;
    }
}
