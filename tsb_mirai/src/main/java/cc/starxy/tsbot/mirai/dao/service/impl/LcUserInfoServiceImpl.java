package cc.starxy.tsbot.mirai.dao.service.impl;

import cc.starxy.tsbot.mirai.dao.entity.LcUserInfo;
import cc.starxy.tsbot.mirai.dao.mapper.LcUserInfoMapper;
import cc.starxy.tsbot.mirai.dao.service.LcUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 力扣用户信息(LcUserInfo)表服务实现类
 *
 * @author DONG Jixing
 */
@Service
public class LcUserInfoServiceImpl extends ServiceImpl<LcUserInfoMapper, LcUserInfo> implements LcUserInfoService {

}