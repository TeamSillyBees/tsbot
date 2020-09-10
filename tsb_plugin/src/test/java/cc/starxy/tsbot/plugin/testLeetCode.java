package cc.starxy.tsbot.plugin;

import cc.starxy.tsbot.plugin.leetcode.nosession.UserInfo;

import java.io.IOException;

/**
 *
 */
public class testLeetCode {
    public static void main(String[] args) {
        try {
            // 输出是否完成每日一题
            System.out.println(UserInfo.dailyQuestionComplete("starxy"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
