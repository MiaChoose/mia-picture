package com.lumenglover.miapicturebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lumenglover.miapicturebackend.model.dto.game2048.SaveGameRecordRequest;
import com.lumenglover.miapicturebackend.model.entity.Game2048Record;
import com.lumenglover.miapicturebackend.model.entity.User;
import com.lumenglover.miapicturebackend.model.vo.Game2048RecordVO;

import java.util.List;
import java.util.Map;

public interface Game2048Service extends IService<Game2048Record> {

    /**
     * 保存游戏记录
     */
    Game2048Record saveGameRecord(SaveGameRecordRequest request, User loginUser);

    /**
     * 获取用户最高分
     */
    Integer getUserHighestScore(Long userId);

    /**
     * 获取排行榜
     */
    List<Game2048RecordVO> getRankingList(Integer limit);

    /**
     * 获取用户游戏历史记录
     * @param userId 用户ID
     * @param current 当前页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    Map<String, Object> getUserGameHistory(Long userId, Integer current, Integer pageSize);
}
