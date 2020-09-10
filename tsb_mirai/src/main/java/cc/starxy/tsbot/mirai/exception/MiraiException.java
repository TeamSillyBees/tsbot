package cc.starxy.tsbot.mirai.exception;

import cc.starxy.tsbot.utils.exception.BaseException;

/**
 * 处理 Mirai 框架使用中出现的异常
 * @author DONG Jixing
 */
public class MiraiException {

    /**
     * 出现命令与多个正则表达式匹配时的异常
     */
    public static class MultiPattern extends BaseException{

        private static final long serialVersionUID = 1L;
        public MultiPattern(String msg){
            super(msg);
        }
    }

}
