package cc.starxy.tsbot.tsb_plugin.leetcode.bean;

import lombok.Data;

import java.util.Objects;

/**
 * 题目节点
 *
 * @author DONG Jixing
 */
@Data
public class QuestionVO {
    /**
     * 题目编码
     */
    String questionId;
    String questionFrontendId;

    /**
     * 题目名称 显示名
     */
    String title;

    /**
     * 题目名称 用于查询
     */
    String titleSlug;

    /**
     * 题目名称 翻译名称
     */
    String translatedTitle;

    /**
     * 题目难度 Easy
     */
    String difficulty;

    /**
     * 当前题目相对于全球的提交状态
     * 是一个字符型封装的 JSON 格式
     * {\"totalAccepted\": \"65.9K\", \"totalSubmission\": \"100.2K\", \"totalAcceptedRaw\": 65900, \"totalSubmissionRaw\": 100180, \"acRate\": \"65.8%\"}
     */
    String stats;

    /**
     * 是否是每日一题
     */
    Boolean isDailyQuestion;

    /**
     * 是否完成
     */
    Boolean complete;
}
