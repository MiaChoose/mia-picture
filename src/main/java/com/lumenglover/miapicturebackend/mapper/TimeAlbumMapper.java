package com.lumenglover.miapicturebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lumenglover.miapicturebackend.model.entity.TimeAlbum;
import org.apache.ibatis.annotations.Mapper;

/**
 * 时光相册数据库操作
 */
@Mapper
public interface TimeAlbumMapper extends BaseMapper<TimeAlbum> {
}
