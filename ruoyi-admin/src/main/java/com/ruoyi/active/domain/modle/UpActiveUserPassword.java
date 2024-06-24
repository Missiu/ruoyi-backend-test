package com.ruoyi.active.domain.modle;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UpActiveUserPassword {
    /**
     * 主键
     */

    @Excel(name = "编号", cellType = Excel.ColumnType.NUMERIC)
    private Long userId;

    /**
     * 密码
     */
    @Excel(name = "密码")
    private String password;

    /**
     * 确认密码
     */
    private String checkPassword;

    /**
     * 原始密码
     */
    private String oldPassword;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public String toString() {
        return "UpActiveUserPassword{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", checkPassword='" + checkPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }
}
