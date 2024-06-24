package com.ruoyi.active.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 作品评价对象 active_evaluation
 *
 * @author huzhihao
 * @date 2024-06-16
 */
public class ActiveEvaluation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 评价ID
     */
    @Excel(name = "作品ID", cellType = Excel.ColumnType.NUMERIC, width = 20, needMerge = true)
    private Long evaId;

    /**
     * 作品ID
     */
    private Long workId;

    /**
     * 评委姓名
     */
    @Excel(name = "评委姓名")
    private String judgeName;

    /**
     * 教学实施得分
     */
    @Excel(name = "教学实施得分")
    private BigDecimal scoreTeachingImplementation;

    /**
     * 教案
     */
    @Excel(name = "教案")
    private BigDecimal scoreLessonPlan;

    /**
     * 视频材料得分
     */
    @Excel(name = "视频材料得分")
    private BigDecimal scoreVideoMaterial;

    /**
     * 人才培养得分
     */
    @Excel(name = "人才培养得分")
    private BigDecimal scoreTalentTraining;

    /**
     * 课程标准得分
     */
    @Excel(name = "课程标准得分")
    private BigDecimal scoreCourseStandard;

    /**
     * 教学材料得分
     */
    @Excel(name = "教学材料得分")
    private BigDecimal scoreTeachingMaterial;

    @Excel(name = "状态")
    private String status;

    @Excel(name = "评委评论")
    private String comment;
    /**
     * 总得分
     */
    @Excel(name = "总得分")
    private BigDecimal finalScore;

    /**
     * 作品信息
     */
    @Excels(
            {
                    @Excel(name = "作品名称", type = Excel.Type.EXPORT, targetAttr = "workName"),
                    @Excel(name = "学校", type = Excel.Type.EXPORT, targetAttr = "schoolName"),
                    @Excel(name = "参赛姓名", type = Excel.Type.EXPORT, targetAttr = "participant"),
                    @Excel(name = "联系方式", type = Excel.Type.EXPORT, targetAttr = "contactInfo"),
                    @Excel(name = "报名组别", type = Excel.Type.EXPORT, targetAttr = "groupName"),
                    @Excel(name = "浏览次数", type = Excel.Type.EXPORT, targetAttr = "viewCount")
            }
    )
    private ActiveWorks activeWorks;

    /**
     * 评分时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "评分时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date evaluatedAt;

    public void setEvaId(Long evaId) {
        this.evaId = evaId;
    }

    public Long getEvaId() {
        return evaId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setScoreTeachingImplementation(BigDecimal scoreTeachingImplementation) {
        this.scoreTeachingImplementation = scoreTeachingImplementation;
    }

    public BigDecimal getScoreTeachingImplementation() {
        return scoreTeachingImplementation;
    }

    public void setScoreLessonPlan(BigDecimal scoreLessonPlan) {
        this.scoreLessonPlan = scoreLessonPlan;
    }

    public BigDecimal getScoreLessonPlan() {
        return scoreLessonPlan;
    }

    public void setScoreVideoMaterial(BigDecimal scoreVideoMaterial) {
        this.scoreVideoMaterial = scoreVideoMaterial;
    }

    public BigDecimal getScoreVideoMaterial() {
        return scoreVideoMaterial;
    }

    public void setScoreTalentTraining(BigDecimal scoreTalentTraining) {
        this.scoreTalentTraining = scoreTalentTraining;
    }

    public BigDecimal getScoreTalentTraining() {
        return scoreTalentTraining;
    }

    public void setScoreCourseStandard(BigDecimal scoreCourseStandard) {
        this.scoreCourseStandard = scoreCourseStandard;
    }

    public BigDecimal getScoreCourseStandard() {
        return scoreCourseStandard;
    }

    public void setScoreTeachingMaterial(BigDecimal scoreTeachingMaterial) {
        this.scoreTeachingMaterial = scoreTeachingMaterial;
    }

    public BigDecimal getScoreTeachingMaterial() {
        return scoreTeachingMaterial;
    }

    public void setFinalScore(BigDecimal finalScore) {
        this.finalScore = finalScore;
    }

    public BigDecimal getFinalScore() {
        return finalScore;
    }

    public void setEvaluatedAt(Date evaluatedAt) {
        this.evaluatedAt = evaluatedAt;
    }

    public Date getEvaluatedAt() {
        return evaluatedAt;
    }

    public ActiveWorks getActiveWorks() {
        return activeWorks;
    }

    public void setActiveWorks(ActiveWorks activeWorks) {
        this.activeWorks = activeWorks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ActiveEvaluation{" +
                "evaId=" + evaId +
                ", workId=" + workId +
                ", judgeName='" + judgeName + '\'' +
                ", scoreTeachingImplementation=" + scoreTeachingImplementation +
                ", scoreLessonPlan=" + scoreLessonPlan +
                ", scoreVideoMaterial=" + scoreVideoMaterial +
                ", scoreTalentTraining=" + scoreTalentTraining +
                ", scoreCourseStandard=" + scoreCourseStandard +
                ", scoreTeachingMaterial=" + scoreTeachingMaterial +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                ", finalScore=" + finalScore +
                ", activeWorks=" + activeWorks +
                ", evaluatedAt=" + evaluatedAt +
                '}';
    }
}
