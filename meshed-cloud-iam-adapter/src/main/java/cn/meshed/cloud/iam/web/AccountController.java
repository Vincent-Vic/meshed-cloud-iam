package cn.meshed.cloud.iam.web;

import cn.meshed.cloud.iam.account.command.AccountAddCmd;
import cn.meshed.cloud.iam.account.command.AccountGrantRoleCmd;
import cn.meshed.cloud.iam.account.command.AccountLockCmd;
import cn.meshed.cloud.iam.account.data.AccountDTO;
import cn.meshed.cloud.iam.account.query.AccountByIdQry;
import cn.meshed.cloud.iam.account.query.AccountQry;
import cn.meshed.cloud.iam.domain.account.ability.AccountService;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    /**
     * 列表
     *
     * @param accountQry
     * @return
     */
    @GetMapping("list")
    public PageResponse<AccountDTO> list(AccountQry accountQry){
        return accountService.searchPageList(accountQry);
    }

    @PostMapping("save")
    public Response save(@Validated @RequestBody AccountAddCmd accountAddCmd){
        return accountService.save(accountAddCmd);
    }

    @DeleteMapping("delete/{id}")
    public Response delete(@PathVariable("id") Long id){
        return accountService.delete(id);
    }

    @GetMapping("details")
    public SingleResponse<AccountDTO> details(@Validated @RequestBody AccountByIdQry accountByIdQry){
        return accountService.query(accountByIdQry);
    }

    @GetMapping("grant/roles")
    public Response getAccountRoleIds(@Validated @RequestBody AccountByIdQry accountByIdQry){
        return accountService.getAccountRoles(accountByIdQry);
    }

    @PostMapping("grant/roles")
    public Response grantAccount(@Validated @RequestBody AccountGrantRoleCmd accountGrantRoleCmd){
        return accountService.grantAccount(accountGrantRoleCmd);
    }


    @PostMapping("lock")
    public Response lock(@Validated @RequestBody AccountLockCmd accountLockCmd){
        accountLockCmd.setOperate(true);
        return accountService.operateLock(accountLockCmd);
    }


    @PostMapping("unlock")
    public Response unlock(@Validated @RequestBody AccountLockCmd accountLockCmd){
        accountLockCmd.setOperate(false);
        return accountService.operateLock(accountLockCmd);
    }


}
