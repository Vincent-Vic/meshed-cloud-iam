package cn.meshed.cloud.iam.domain.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@AllArgsConstructor
@Getter
public enum IAMStatus {

    /**
     * 0 正常
     * 1 删除
     */
    NORMAL(0),
    DELETE(1)
    ;

    /**
     * 状态
     */
    private final int status;
}
