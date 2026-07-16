package com.lumenglover.miapicturebackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lumenglover.miapicturebackend.model.entity.AppVersion;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppVersionMapper extends BaseMapper<AppVersion> {
}
