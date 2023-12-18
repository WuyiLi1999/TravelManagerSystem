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
public class InternetInformationReq extends PageInfo {
    private String userId;
}
