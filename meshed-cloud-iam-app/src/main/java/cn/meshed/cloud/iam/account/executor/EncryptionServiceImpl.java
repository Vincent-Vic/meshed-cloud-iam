package cn.meshed.cloud.iam.account.executor;

import cn.meshed.cloud.iam.domain.account.ability.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <h1>密码领域服务</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class EncryptionServiceImpl implements EncryptionService {

    private final PasswordEncoder passwordEncoder;

    /**
     * 加密字符串
     *
     * @param unencrypted 明文
     * @return 密文
     */
    @Override
    public String encode(String unencrypted) {
        return passwordEncoder.encode(unencrypted);
    }

    /**
     * 匹配密码是否正确
     *
     * @param unencrypted 明文
     * @param encrypted   密文
     * @return 匹配结果
     */
    @Override
    public boolean matches(String unencrypted, String encrypted) {
        return passwordEncoder.matches(unencrypted, encrypted);
    }
}
