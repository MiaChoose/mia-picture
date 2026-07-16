package com.lumenglover.miapicturebackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lumenglover.miapicturebackend.model.entity.PexelsCrawlRecord;

/**
 * Pexels 抓取记录服务
 */
public interface PexelsCrawlRecordService extends IService<PexelsCrawlRecord> {

    /**
     * 检查 Pexels 图片是否已存在
     *
     * @param pexelsPhotoId Pexels 图片ID
     * @return 是否存在
     */
    boolean existsByPexelsPhotoId(Long pexelsPhotoId);
}
