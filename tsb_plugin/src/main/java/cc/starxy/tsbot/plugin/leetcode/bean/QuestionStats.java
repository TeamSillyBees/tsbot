package cc.starxy.tsbot.plugin.leetcode.bean;

import lombok.Data;

/**
 * 题目总体完成情况
 * @author DONG Jixing
 */
@Data
public class QuestionStats {
    /**
     * 总通过次数
     */
    String totalAccepted;
    /**
     * 总提交次数
     */
    String totalSubmission;
    /**
     * 通过率
     */
    String acRate;
}
