package cc.starxy.tsbot.mirai.dao.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 力扣每日一题记录(LcDailyQuestion)表实体类
 *
 * @author DONG Jixing
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuppressWarnings("serial")
public class LcDailyQuestion extends Model<LcDailyQuestion> implements Serializable {
    private static final long serialVersionUID = 111696931913221814L;

    /**
     * 主键
     */
    private String id;

    /**
     * 题目编码
     */
    private String questionId;

    /**
     * 题目名称 显示名
     */
    private String title;

    /**
     * 题目名称 用于查询
     */
    private String titleSlug;

    /**
     * 题目名称 翻译名称
     */
    private String translatedTitle;

    /**
     * 题目难度
     */
    private String difficulty;

    /**
     * 日期
     */
    private Date date;
}