package cn.meshed.cloud.iam.account.config;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;


/**
 * <h1>密码编码配置</h1>
 *
 * @author: Vincent Vic
 * @version 1.0
 */
@Configuration
public class PasswordEncoderConfig {
    /**
     * 配置多密码编码
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        val idForDefault = "bcrypt";
        Map encoders = new HashMap(1);
        encoders.put(idForDefault,new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(idForDefault,encoders);
    }

}
