package cc.starxy.tsbot.tsb_mirai.jobs;

import cc.starxy.tsbot.tsb_mirai.core.MiraiCore;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 每日一题相关任务类
 * @author DONG Jixing
 */

@EnableScheduling
public class DailyQuestionTask {


    /**
     * 每日一题
     * 每天上午8点半提醒
     */
    @Scheduled(cron = "0 30 8 * * ? *")
    public void dailyNotify(){
        MiraiCore miraiCore = MiraiCore.getInstance();


    }

    /**
     * 每日一题检查是否完成
     * 每天下午5点半加内存
     */
    @Scheduled(cron = "0 30 17 * * ? *")
    public void dailyCheck(){

    }
}
