package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 账号对象 active_user
 *
 * @author huzhihao
 * @date 2024-06-15
 */
public class ActiveUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    @Excel(name = "编号", cellType = Excel.ColumnType.NUMERIC)
    private Long userId;

    /**
     * 姓名
     */

    @Excel(name = "姓名")
    private String realName;

    /**
     * 学校
     */

    @Excel(name = "学校")
    private String schoolName;

    /**
     * 账号身份（1评委 2教师）
     */

    @Excel(name = "账号身份", readConverterExp = "1-评委,2-教师")
    private String userType;

    /** 报名组别（1思政课程组 2公共基础课程组（不含思政） 3专业技能课程一组 4专业技能课程二组） */
    /**
     * 如果是教师， 则报名组别required = true，评选范围隐藏
     **/

    @Excel(name = "报名组别", readConverterExp = "1思政课程组 2公共基础课程组（不含思政） 3专业技能课程一组 4专业技能课程二组")
    private String groupName;

    /**
     * 评选范围（1思政课程组 2公共基础课程组（不含思政） 3专业技能课程一组 4专业技能课程二组）
     */

    @Excel(name = "评选范围", readConverterExp = "1思政课程组 2公共基础课程组（不含思政） 3专业技能课程一组 4专业技能课程二组")
    private String judgeScope;

    /**
     * 账号名
     */

    @Excel(name = "账号名")
    private String loginName;

    /**
     * 密码
     */
    @Excel(name = "密码")
    private String password;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setJudgeScope(String judgeScope) {
        this.judgeScope = judgeScope;
    }

    public String getJudgeScope() {
        return judgeScope;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("realName", getRealName())
                .append("schoolName", getSchoolName())
                .append("userType", getUserType())
                .append("groupName", getGroupName())
                .append("judgeScope", getJudgeScope())
                .append("loginName", getLoginName())
                .append("password", getPassword())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
