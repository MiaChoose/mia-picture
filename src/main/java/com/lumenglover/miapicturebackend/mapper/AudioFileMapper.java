package com.lumenglover.miapicturebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lumenglover.miapicturebackend.model.entity.AudioFile;
import org.apache.ibatis.annotations.Mapper;

/**
 * 音频文件Mapper接口
 */
@Mapper
public interface AudioFileMapper extends BaseMapper<AudioFile> {
}
