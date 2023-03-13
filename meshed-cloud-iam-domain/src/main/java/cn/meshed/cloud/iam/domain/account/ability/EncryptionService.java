package cn.meshed.cloud.iam.domain.account.ability;

/**
 * <h1>加密领域服务</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface EncryptionService {

    /**
     * 加密字符串
     * @param unencrypted 明文
     * @return 密文
     */
    String encode(String unencrypted);

    /**
     * 匹配密码是否正确
     * @param unencrypted 明文
     * @param encrypted 密文
     * @return 匹配结果
     */
    boolean matches(String unencrypted,String encrypted);
}
