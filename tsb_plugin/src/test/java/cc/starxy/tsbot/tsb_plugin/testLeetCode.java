package cc.starxy.tsbot.tsb_plugin;

import cc.starxy.tsbot.tsb_plugin.leetcode.bean.QuestionVO;
import cc.starxy.tsbot.tsb_plugin.leetcode.bean.TodayRecordVO;
import cc.starxy.tsbot.tsb_plugin.leetcode.nosession.QuestionInfo;
import cc.starxy.tsbot.tsb_plugin.leetcode.nosession.UserInfo;
import cc.starxy.tsbot.tsb_plugin.leetcode.session.Login;

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
