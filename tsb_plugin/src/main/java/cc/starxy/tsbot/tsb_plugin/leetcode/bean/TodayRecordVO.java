package cc.starxy.tsbot.tsb_plugin.leetcode.bean;

import lombok.Data;

import java.util.Date;

/**
 * 每日一题
 *
 * @author DONG Jixing
 */
@Data
public class TodayRecordVO {

    private QuestionVO question;

    private Date date;

    private String lastSubmission;

    private String userStatus;
}
