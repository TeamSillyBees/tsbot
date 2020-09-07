package cc.starxy.tsbot.tsb_mirai.jobs;

import cc.starxy.tsbot.tsb_mirai.handler.MessageHandler;
import cc.starxy.tsbot.tsb_plugin.leetcode.bean.QuestionStats;
import cc.starxy.tsbot.tsb_plugin.leetcode.bean.QuestionVO;
import cc.starxy.tsbot.tsb_plugin.leetcode.bean.TodayRecordVO;
import cc.starxy.tsbot.tsb_plugin.leetcode.constants.LeetCodeCn;
import cc.starxy.tsbot.tsb_plugin.leetcode.nosession.QuestionInfo;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 每日一题相关任务类
 *
 * @author DONG Jixing
 */
@Component
public class DailyQuestionTask {

    /**
     * 每日一题
     * 每天上午8点半提醒
     */
    @Scheduled(cron = "0 30 8 * * ?")
    public void dailyNotify() {
        try {
            TodayRecordVO today = QuestionInfo.questionOfToday();
            if (today != null) {
                QuestionVO question = QuestionInfo.questionData(today.getQuestion().getTitleSlug());
                if (question != null) {
                    QuestionStats stats = JSON.parseObject(question.getStats(), QuestionStats.class);
                    String msg = "力扣每日一题 " + DateUtil.format(today.getDate(), "yyyy年M月d日") + "\n" +
                            "----------------------\n" +
                            "题目：" + question.getTranslatedTitle() + "\n" +
                            "难度：" + question.getDifficulty() + "\n" +
                            "总通过人数：" + stats.getTotalAccepted() + "\n" +
                            "通过率：" + stats.getAcRate() + "\n" +
                            "题目链接：" + LeetCodeCn.URL_PROBLEM + "/" + question.getTitleSlug();
                    return;
                }
            }
            MessageHandler.sendErrorMessage("获取每日一题失败", new Exception("每日一题返回数据为空"));
        } catch (IOException e) {
            MessageHandler.sendErrorMessage("获取每日一题失败", e);
        }

    }

    /**
     * 每日一题检查是否完成
     * 每天下午5点半加内存
     */
    @Scheduled(cron = "0 30 17 * * ?")
    public void dailyCheck() {

    }
}
