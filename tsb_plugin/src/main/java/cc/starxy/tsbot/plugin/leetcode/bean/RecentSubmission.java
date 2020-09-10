package cc.starxy.tsbot.plugin.leetcode.bean;

import lombok.Data;

import java.util.Date;

/**
 * 最近提交记录
 * @author DONG Jixing
 */
@Data
public class RecentSubmission {

    /**
     * 提交状态
     * A_10 通过
     * A_11 解答错误
     * A_12 超出内存限制
     * A_14 超出时间限制
     * A_15 执行出错
     * A_20 编译出错
     */
    String status;

    /**
     * 题目名称
     */
    String questionTitleSlug;

    /**
     * 提交时间
     */
    Date submitTime;
}
