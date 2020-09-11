package cc.starxy.tsbot.mirai.dao.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 力扣用户信息(LcUserInfo)表实体类
 *
 * @author DONG Jixing
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuppressWarnings("serial")
public class LcUserInfo extends Model<LcUserInfo> implements Serializable {
    private static final long serialVersionUID = 678605514005389410L;

    /**
     * 主键
     */
    private String id;

    /**
     * 力扣用户名
     */
    private String leetcodeId;

    /**
     * 完成题目数量
     */
    private Integer acQuestions;

    /**
     * 通过提交数
     */
    private Integer acSubmissions;

    /**
     * 总提交数
     */
    private Integer totalSubmissions;
}