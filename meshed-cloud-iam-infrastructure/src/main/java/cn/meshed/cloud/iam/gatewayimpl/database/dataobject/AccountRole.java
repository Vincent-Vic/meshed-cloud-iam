package cn.meshed.cloud.iam.gatewayimpl.database.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author by Vincent Vic
 * @since 2022-10-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_account_role")
public class AccountRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号角色ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号Id
     */
    private Long accountId;

    /**
     * 角色ID
     */
    private Long roleId;


}
