package com.lumenglover.miapicturebackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lumenglover.miapicturebackend.model.entity.UserSignInRecord;
import com.lumenglover.miapicturebackend.service.UserSignInRecordService;
import com.lumenglover.miapicturebackend.mapper.UserSignInRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author 鹿梦
* @description 针对表【user_sign_in_record(用户签到记录表)】的数据库操作Service实现
* @createDate 2025-01-17 10:55:33
*/
@Service
public class UserSignInRecordServiceImpl extends ServiceImpl<UserSignInRecordMapper, UserSignInRecord>
    implements UserSignInRecordService{

}




