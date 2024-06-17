package com.ruoyi.active.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作品对象 active_works
 * 
 * @author huzhihao
 * @date 2024-06-15
 */
public class ActiveWorks extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作品ID */
    @Excel(name = "作品ID",cellType = Excel.ColumnType.NUMERIC)
    private Long workId;

    /** 作品名称 */
    @Excel(name = "作品名称")
    private String workName;

    /** 学校 */
    @Excel(name = "学校")
    private String schoolName;

    /** 参赛姓名 */
    @Excel(name = "参赛姓名")
    private String participant;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactInfo;

    /** 实时平均分 */
    @Excel(name = "实时平均分")
    private BigDecimal averageScore;

    /** 报名组别(1:思政课程组、2:公共基础课程组(不含思政)、3:专业技能课程一组、4:专业技能课程二组) */
    @Excel(name = "报名组别(1:思政课程组、2:公共基础课程组(不含思政)、3:专业技能课程一组、4:专业技能课程二组)")
    private String groupName;

    /** 浏览次数 */
    @Excel(name = "浏览次数")
    private Long viewCount;

    public void setWorkId(Long workId) 
    {
        this.workId = workId;
    }

    public Long getWorkId() 
    {
        return workId;
    }
    public void setWorkName(String workName) 
    {
        this.workName = workName;
    }

    public String getWorkName() 
    {
        return workName;
    }
    public void setSchoolName(String schoolName) 
    {
        this.schoolName = schoolName;
    }

    public String getSchoolName() 
    {
        return schoolName;
    }
    public void setParticipant(String participant) 
    {
        this.participant = participant;
    }

    public String getParticipant() 
    {
        return participant;
    }
    public void setContactInfo(String contactInfo) 
    {
        this.contactInfo = contactInfo;
    }

    public String getContactInfo() 
    {
        return contactInfo;
    }
    public void setAverageScore(BigDecimal averageScore) 
    {
        this.averageScore = averageScore;
    }

    public BigDecimal getAverageScore() 
    {
        return averageScore;
    }
    public void setGroupName(String groupName) 
    {
        this.groupName = groupName;
    }

    public String getGroupName() 
    {
        return groupName;
    }
    public void setViewCount(Long viewCount) 
    {
        this.viewCount = viewCount;
    }

    public Long getViewCount() 
    {
        return viewCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("workId", getWorkId())
            .append("workName", getWorkName())
            .append("schoolName", getSchoolName())
            .append("participant", getParticipant())
            .append("contactInfo", getContactInfo())
            .append("averageScore", getAverageScore())
            .append("groupName", getGroupName())
            .append("viewCount", getViewCount())
            .append("createTime", getCreateTime())
            .toString();
    }
}
