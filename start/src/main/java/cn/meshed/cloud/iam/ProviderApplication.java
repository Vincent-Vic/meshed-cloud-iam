package cn.meshed.cloud.iam;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h1>启动类</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@EnableDubbo(scanBasePackages = {"cn.meshed.cloud.iam.remote"})
@SpringBootApplication(scanBasePackages = {"cn.meshed.cloud", "com.alibaba.cola"})
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
