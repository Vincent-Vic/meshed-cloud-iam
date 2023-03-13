package cn.meshed.cloud.iam.gatewayimpl;

import cn.meshed.cloud.iam.ProviderApplication;
import cn.meshed.cloud.iam.domain.rbac.gateway.RoleGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;


/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@SpringBootTest(classes = ProviderApplication.class)
public class RoleDOGatewayImplTest {

    @Autowired
    private RoleGateway roleGateway;

    @Test
    public void delete() {
    }

    @Test
    public void searchPageList() {
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void grantPermission() {
        Set<Long> set = new HashSet<>();
        set.add(11L);
        set.add(12L);
        set.add(13L);
        roleGateway.grantPermission(1L,set);
    }
}