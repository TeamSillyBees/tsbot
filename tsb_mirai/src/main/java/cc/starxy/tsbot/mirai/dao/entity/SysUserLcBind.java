package cc.starxy.tsbot.mirai.dao.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 系统用户与力扣绑定记录(SysUserLcBind)表实体类
 *
 * @author DONG Jixing
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuppressWarnings("serial")
public class SysUserLcBind extends Model<SysUserLcBind> implements Serializable {
    private static final long serialVersionUID = -50290746656766120L;

    /**
     * 主键
     */
    private String id;

    /**
     * qq 号
     */
    private String qqId;

    /**
     * 所在群号
     */
    private String groupId;

    /**
     * 力扣id
     */
    private String leetcodeId;

    /**
     * 是否开启每日提醒
     */
    private Boolean enableNotice;
}