package cn.meshed.cloud.iam.execute;

import cn.meshed.cloud.iam.ProviderApplication;
import cn.meshed.cloud.iam.account.data.UserDTO;
import cn.meshed.cloud.iam.account.executor.query.UserByOneQryExe;
import cn.meshed.cloud.iam.account.query.UserByOneQry;
import com.alibaba.cola.dto.SingleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@SpringBootTest(classes = ProviderApplication.class) public class UserByOneQryExeTest {

    @Autowired private UserByOneQryExe userByOneQryExe;

    @Test public void testUserByOneQry() {
        UserByOneQry userByOneQry = new UserByOneQry();
        userByOneQry.setId(1L);
        userByOneQry.setHasGrantedAuthority(true);
        SingleResponse<UserDTO> execute = userByOneQryExe.execute(userByOneQry);
        System.out.println(execute.getData());
    }

}