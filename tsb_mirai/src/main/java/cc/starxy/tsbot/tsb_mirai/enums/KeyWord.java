package cc.starxy.tsbot.tsb_mirai.enums;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 关键词
 *
 * @author DONG Jixing
 */

public enum KeyWord {

    /**
     * 帮助
     */
    BOT_HELP("帮助", ".h", "help"),

    /**
     * 帮助
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

    KeyWord(String description, String... words) {
        this.description = description;
        this.words = Arrays.stream(words).collect(Collectors.toList());
    }

    /**
     * 指令描述
     */
    private String description;
    /**
     * 操作指令关键字
     */
    private List<String> words;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
