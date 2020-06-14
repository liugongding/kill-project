package com.dingding.kill.dto;

import com.dingding.kill.entity.ItemKillSuccess;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author liudingding
 * @ClassName KillSuccessUserInfo
 * @description
 * @date 2020/3/31 14:42
 * Version 1.0
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class KillSuccessUserInfo extends ItemKillSuccess implements Serializable {
    private String userName;

    private String phone;

    private String email;

    private String itemName;

}
