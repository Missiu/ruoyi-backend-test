package com.ruoyi.active.service.impl;

import com.ruoyi.active.domain.entity.ActiveFile;
import com.ruoyi.active.mapper.ActiveFileMapper;
import com.ruoyi.active.service.IActiveFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作品相关文件Service业务层处理
 * 
 * @author huzhihao
 * @date 2024-06-16
 */
@Service
public class ActiveFileServiceImpl implements IActiveFileService 
{
    @Autowired
    private ActiveFileMapper activeFileMapper;

    /**
     * 查询作品相关文件
     * 
     * @param fileId 作品相关文件主键
     * @return 作品相关文件
     */
    @Override
    public ActiveFile selectActiveFileByFileId(Long fileId)
    {
        return activeFileMapper.selectActiveFileByFileId(fileId);
    }

    /**
     * 查询作品相关文件列表
     * 
     * @param activeFile 作品相关文件
     * @return 作品相关文件
     */
    @Override
    public List<ActiveFile> selectActiveFileList(ActiveFile activeFile)
    {
        return activeFileMapper.selectActiveFileList(activeFile);
    }

    /**
     * 新增作品相关文件
     * 
     * @param activeFile 作品相关文件
     * @return 结果
     */
    @Override
    public int insertActiveFile(ActiveFile activeFile)
    {
        return activeFileMapper.insertActiveFile(activeFile);
    }

    /**
     * 修改作品相关文件
     * 
     * @param activeFile 作品相关文件
     * @return 结果
     */
    @Override
    public int updateActiveFile(ActiveFile activeFile)
    {
        return activeFileMapper.updateActiveFile(activeFile);
    }

    /**
     * 批量删除作品相关文件
     * 
     * @param fileIds 需要删除的作品相关文件主键
     * @return 结果
     */
    @Override
    public int deleteActiveFileByFileIds(Long[] fileIds)
    {
        return activeFileMapper.deleteActiveFileByFileIds(fileIds);
    }

    /**
     * 删除作品相关文件信息
     * 
     * @param fileId 作品相关文件主键
     * @return 结果
     */
    @Override
    public int deleteActiveFileByFileId(Long fileId)
    {
        return activeFileMapper.deleteActiveFileByFileId(fileId);
    }
}
