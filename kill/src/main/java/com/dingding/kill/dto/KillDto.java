package com.dingding.kill.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author liudingding
 * @ClassName KillDto
 * @description
 * @date 2020/4/2 0:16
 * Version 1.0
 */
@Data
@ToString
public class KillDto implements Serializable {

    @NotNull
    private Integer killId;

    private Integer userId;
}
