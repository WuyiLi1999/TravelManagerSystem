package com.example.springboottest.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Smile
 */
@Data
@Getter
@Setter
public class PageInfo {
    private int pageNum=1;
    private int pageSize=10;

}
