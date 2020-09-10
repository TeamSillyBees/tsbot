package cc.starxy.tsbot.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBoot 启动类
 * @author DONG Jixing
 */
@EnableScheduling
@ComponentScan(basePackages = "cc.starxy.tsbot")
@SpringBootApplication
public class TsbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TsbotApplication.class, args);
    }
}
