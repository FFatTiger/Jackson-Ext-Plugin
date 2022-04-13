package com.proxyy.jackson.ext.plugin.dto;

import com.proxyy.jackson.ext.plugin.annotation.DateFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author proxyy
 * @date 2022/4/13 22:42
 */
public class PayRecordRequestDTO {

    private String name;

    private BigDecimal money;

    @DateFormat(pattern = "yyyy-MM-dd")
    private Date payDate = new Date();

    public String getName() {
        return name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public Date getPayDate() {
        return payDate;
    }
}
