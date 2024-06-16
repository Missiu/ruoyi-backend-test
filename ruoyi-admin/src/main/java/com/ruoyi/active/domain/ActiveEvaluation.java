package com.ruoyi.active.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作品评价对象 active_evaluation
 * 
 * @author huzhihao
 * @date 2024-06-16
 */
public class ActiveEvaluation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评价ID */
    private Long evaId;

    /** 作品ID */
    private Long workId;

    /** 评委姓名 */
    @Excel(name = "评委姓名")
    private String judgeName;

    /** 教学实施得分 */
    @Excel(name = "教学实施得分")
    private BigDecimal scoreTeachingImplementation;

    /** 教案 */
    @Excel(name = "教案")
    private BigDecimal scoreLessonPlan;

    /** 视频材料得分 */
    @Excel(name = "视频材料得分")
    private BigDecimal scoreVideoMaterial;

    /** 人才培养得分 */
    @Excel(name = "人才培养得分")
    private BigDecimal scoreTalentTraining;

    /** 课程标准得分 */
    @Excel(name = "课程标准得分")
    private BigDecimal scoreCourseStandard;

    /** 教学材料得分 */
    @Excel(name = "教学材料得分")
    private BigDecimal scoreTeachingMaterial;

    /** 总得分 */
    @Excel(name = "总得分")
    private BigDecimal finalScore;

    /** 评分时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评分时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date evaluatedAt;

    public void setEvaId(Long evaId) 
    {
        this.evaId = evaId;
    }

    public Long getEvaId() 
    {
        return evaId;
    }
    public void setWorkId(Long workId) 
    {
        this.workId = workId;
    }

    public Long getWorkId() 
    {
        return workId;
    }
    public void setJudgeName(String judgeName) 
    {
        this.judgeName = judgeName;
    }

    public String getJudgeName() 
    {
        return judgeName;
    }
    public void setScoreTeachingImplementation(BigDecimal scoreTeachingImplementation) 
    {
        this.scoreTeachingImplementation = scoreTeachingImplementation;
    }

    public BigDecimal getScoreTeachingImplementation() 
    {
        return scoreTeachingImplementation;
    }
    public void setScoreLessonPlan(BigDecimal scoreLessonPlan) 
    {
        this.scoreLessonPlan = scoreLessonPlan;
    }

    public BigDecimal getScoreLessonPlan() 
    {
        return scoreLessonPlan;
    }
    public void setScoreVideoMaterial(BigDecimal scoreVideoMaterial) 
    {
        this.scoreVideoMaterial = scoreVideoMaterial;
    }

    public BigDecimal getScoreVideoMaterial() 
    {
        return scoreVideoMaterial;
    }
    public void setScoreTalentTraining(BigDecimal scoreTalentTraining) 
    {
        this.scoreTalentTraining = scoreTalentTraining;
    }

    public BigDecimal getScoreTalentTraining() 
    {
        return scoreTalentTraining;
    }
    public void setScoreCourseStandard(BigDecimal scoreCourseStandard) 
    {
        this.scoreCourseStandard = scoreCourseStandard;
    }

    public BigDecimal getScoreCourseStandard() 
    {
        return scoreCourseStandard;
    }
    public void setScoreTeachingMaterial(BigDecimal scoreTeachingMaterial) 
    {
        this.scoreTeachingMaterial = scoreTeachingMaterial;
    }

    public BigDecimal getScoreTeachingMaterial() 
    {
        return scoreTeachingMaterial;
    }
    public void setFinalScore(BigDecimal finalScore) 
    {
        this.finalScore = finalScore;
    }

    public BigDecimal getFinalScore() 
    {
        return finalScore;
    }
    public void setEvaluatedAt(Date evaluatedAt) 
    {
        this.evaluatedAt = evaluatedAt;
    }

    public Date getEvaluatedAt() 
    {
        return evaluatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("evaId", getEvaId())
            .append("workId", getWorkId())
            .append("judgeName", getJudgeName())
            .append("scoreTeachingImplementation", getScoreTeachingImplementation())
            .append("scoreLessonPlan", getScoreLessonPlan())
            .append("scoreVideoMaterial", getScoreVideoMaterial())
            .append("scoreTalentTraining", getScoreTalentTraining())
            .append("scoreCourseStandard", getScoreCourseStandard())
            .append("scoreTeachingMaterial", getScoreTeachingMaterial())
            .append("finalScore", getFinalScore())
            .append("evaluatedAt", getEvaluatedAt())
            .toString();
    }
}