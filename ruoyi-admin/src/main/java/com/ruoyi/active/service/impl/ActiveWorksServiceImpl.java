package com.ruoyi.active.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.ruoyi.active.domain.entity.ActiveEvaluation;
import com.ruoyi.active.mapper.ActiveEvaluationMapper;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.active.mapper.ActiveWorksMapper;
import com.ruoyi.active.domain.entity.ActiveWorks;
import com.ruoyi.active.service.IActiveWorksService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 作品Service业务层处理
 *
 * @author huzhihao
 * @date 2024-06-15
 */
@Service
public class ActiveWorksServiceImpl implements IActiveWorksService {
    @Autowired
    private ActiveWorksMapper activeWorksMapper;

    @Autowired
    private ActiveEvaluationMapper activeEvaluationMapper;

    /**
     * 查询作品
     *
     * @param workId 作品主键
     * @return 作品
     */
    @Override
    public ActiveWorks selectActiveWorksByWorkId(Long workId) {
        return activeWorksMapper.selectActiveWorksByWorkId(workId);
    }

    /**
     * 查询作品列表
     *
     * @param activeWorks 作品
     * @return 作品
     */
    @Override
    public List<ActiveWorks> selectActiveWorksList(ActiveWorks activeWorks) {
        List<ActiveWorks> activeWorksList = activeWorksMapper.selectActiveWorksList(activeWorks);
        activeWorksList.forEach(activeWorksTemp -> {
            // 获取作品id
            Long workId = activeWorksTemp.getWorkId();
            // 获取评分表
            ActiveEvaluation activeEvaluation = activeEvaluationMapper.selectActiveEvaluationByWorkId(workId);
            // 计算平均分
            BigDecimal all = activeEvaluation.getScoreCourseStandard()
                    .add(activeEvaluation.getScoreLessonPlan())
                    .add(activeEvaluation.getScoreTeachingMaterial())
                    .add(activeEvaluation.getScoreTalentTraining())
                    .add(activeEvaluation.getScoreTeachingImplementation())
                    .add(activeEvaluation.getScoreVideoMaterial());
            activeWorksTemp.setAverageScore(all.divide(new BigDecimal(6), 2, RoundingMode.HALF_UP));
        });
        return activeWorksList;
    }

    /**
     * 新增作品
     *
     * @param activeWorks 作品
     * @return 结果
     */
    @Override
    @Transactional
    public int insertActiveWorks(ActiveWorks activeWorks) {
        activeWorks.setCreateTime(DateUtils.getNowDate());
        return activeWorksMapper.insertActiveWorks(activeWorks);
    }

    /**
     * 修改作品
     *
     * @param activeWorks 作品
     * @return 结果
     */
    @Override
    public int updateActiveWorks(ActiveWorks activeWorks) {
        return activeWorksMapper.updateActiveWorks(activeWorks);
    }

    /**
     * 批量删除作品
     *
     * @param workIds 需要删除的作品主键
     * @return 结果
     */
    @Override
    public int deleteActiveWorksByWorkIds(Long[] workIds) {
        return activeWorksMapper.deleteActiveWorksByWorkIds(workIds);
    }

    /**
     * 删除作品信息
     *
     * @param workId 作品主键
     * @return 结果
     */
    @Override
    public int deleteActiveWorksByWorkId(Long workId) {
        return activeWorksMapper.deleteActiveWorksByWorkId(workId);
    }
}
