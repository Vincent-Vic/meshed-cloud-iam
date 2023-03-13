package cn.meshed.cloud.iam.gatewayimpl;

import cn.meshed.cloud.iam.ProviderApplication;
import cn.meshed.cloud.iam.account.query.AccountQry;
import cn.meshed.cloud.iam.domain.account.Account;
import cn.meshed.cloud.iam.domain.account.gateway.AccountGateway;
import com.alibaba.cola.dto.PageResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@SpringBootTest(classes = ProviderApplication.class)
public class AccountDOGatewayImplTest {

    @Autowired
    private AccountGateway accountGateway;

    @Test
    public void testGetAccountByLoginId() {
    }

    @Test
    public void testGrantRole() {
    }

    @Test
    public void testGetGrantedAuthority() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testSearchPageList() {
        AccountQry accountQry = new AccountQry();
        PageResponse<Account> response = accountGateway.searchPageList(accountQry);
    }

    @Test
    public void testSave() {
    }

    @Test
    public void testUpdate() {
    }
}