package com.example.springboottest.domain.req;

import com.example.springboottest.domain.PageInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Smile
 */
@Data
@Getter
@Setter
public class UserInformationReq extends PageInfo {
    private String userId;
    private Integer sex;
    private String countryName;

}
