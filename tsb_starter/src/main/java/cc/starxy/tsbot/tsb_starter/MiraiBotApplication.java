package cc.starxy.tsbot.tsb_starter;

import cc.starxy.tsbot.tsb_mirai.MiraiStart;
import cc.starxy.tsbot.tsb_starter.config.LeetCodeConfig;
import cc.starxy.tsbot.tsb_starter.config.MiraiConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Mirai Bot start bean
 * @author DONG Jixing
 */
@Component
public class MiraiBotApplication implements ApplicationRunner {

    private final MiraiConfig mirai;

    public MiraiBotApplication(MiraiConfig mirai) {
        this.mirai = mirai;
    }

    @Override
    public void run(ApplicationArguments args) {
        MiraiStart.start(mirai.getUsername(), mirai.getPassword());
    }
}
