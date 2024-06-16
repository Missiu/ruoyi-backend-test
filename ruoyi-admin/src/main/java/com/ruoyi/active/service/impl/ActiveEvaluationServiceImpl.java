package com.ruoyi.active.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.active.mapper.ActiveEvaluationMapper;
import com.ruoyi.active.domain.ActiveEvaluation;
import com.ruoyi.active.service.IActiveEvaluationService;

/**
 * 作品评价Service业务层处理
 * 
 * @author huzhihao
 * @date 2024-06-16
 */
@Service
public class ActiveEvaluationServiceImpl implements IActiveEvaluationService 
{
    @Autowired
    private ActiveEvaluationMapper activeEvaluationMapper;

    /**
     * 查询作品评价
     * 
     * @param evaId 作品评价主键
     * @return 作品评价
     */
    @Override
    public ActiveEvaluation selectActiveEvaluationByEvaId(Long evaId)
    {
        return activeEvaluationMapper.selectActiveEvaluationByEvaId(evaId);
    }

    /**
     * 查询作品评价列表
     * 
     * @param activeEvaluation 作品评价
     * @return 作品评价
     */
    @Override
    public List<ActiveEvaluation> selectActiveEvaluationList(ActiveEvaluation activeEvaluation)
    {
        return activeEvaluationMapper.selectActiveEvaluationList(activeEvaluation);
    }

    /**
     * 新增作品评价
     * 
     * @param activeEvaluation 作品评价
     * @return 结果
     */
    @Override
    public int insertActiveEvaluation(ActiveEvaluation activeEvaluation)
    {
        return activeEvaluationMapper.insertActiveEvaluation(activeEvaluation);
    }

    /**
     * 修改作品评价
     * 
     * @param activeEvaluation 作品评价
     * @return 结果
     */
    @Override
    public int updateActiveEvaluation(ActiveEvaluation activeEvaluation)
    {
        return activeEvaluationMapper.updateActiveEvaluation(activeEvaluation);
    }

    /**
     * 批量删除作品评价
     * 
     * @param evaIds 需要删除的作品评价主键
     * @return 结果
     */
    @Override
    public int deleteActiveEvaluationByEvaIds(Long[] evaIds)
    {
        return activeEvaluationMapper.deleteActiveEvaluationByEvaIds(evaIds);
    }

    /**
     * 删除作品评价信息
     * 
     * @param evaId 作品评价主键
     * @return 结果
     */
    @Override
    public int deleteActiveEvaluationByEvaId(Long evaId)
    {
        return activeEvaluationMapper.deleteActiveEvaluationByEvaId(evaId);
    }
}
