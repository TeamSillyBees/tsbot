package cc.starxy.tsbot.tsb_mirai.commands.facade;

import cc.starxy.tsbot.tsb_mirai.enums.KeyWord;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.Message;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * 帮助命令
 *
 * @author DONG Jixing
 */
@Component
public class HelpFacade implements CommandFacade {

    @Override
    public KeyWord cmd() {
        return KeyWord.BOT_HELP;
    }

    /**
     * .h
     * help
     * .h cmd
     * help cmd
     */
    @Override
    public Pattern regex() {
        String pattern = "^(\\.h|help)(\\s(\\w+))?$";
        return Pattern.compile(pattern);
    }

    @Override
    public void execute(Contact sender, Contact group, Message message) {

    }
}
