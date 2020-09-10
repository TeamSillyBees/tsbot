package cc.starxy.tsbot.starter;

import cc.starxy.tsbot.mirai.MiraiStart;
import cc.starxy.tsbot.mirai.listener.FriendListener;
import cc.starxy.tsbot.mirai.listener.GroupListener;
import cc.starxy.tsbot.starter.config.MiraiConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Mirai Bot start bean
 *
 * @author DONG Jixing
 */
@Component
public class MiraiBotApplication implements ApplicationRunner {

    private final MiraiConfig mirai;

    private final GroupListener groupListener;

    private final FriendListener friendListener;

    public MiraiBotApplication(MiraiConfig mirai, GroupListener groupListener, FriendListener friendListener) {
        this.mirai = mirai;
        this.groupListener = groupListener;
        this.friendListener = friendListener;
    }

    @Override
    public void run(ApplicationArguments args) {
        MiraiStart.start(mirai.getBotId(), mirai.getPassword(), mirai.getNotifyGroupId(), groupListener, friendListener);
    }
}
