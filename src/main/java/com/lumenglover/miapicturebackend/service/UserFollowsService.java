package com.lumenglover.miapicturebackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lumenglover.miapicturebackend.model.dto.userfollows.UserFollowsAddRequest;
import com.lumenglover.miapicturebackend.model.dto.userfollows.UserFollowsIsFollowsRequest;
import com.lumenglover.miapicturebackend.model.dto.userfollows.UserfollowsQueryRequest;
import com.lumenglover.miapicturebackend.model.entity.Userfollows;
import com.lumenglover.miapicturebackend.model.vo.FollowersAndFansVO;
import com.lumenglover.miapicturebackend.model.vo.UserVO;

import java.util.List;

/**
* @author 鹿梦
* @description 针对表【userfollows】的数据库操作Service
* @createDate 2025-01-14 20:49:17
*/
public interface UserFollowsService extends IService<Userfollows> {

    Boolean addUserFollows(UserFollowsAddRequest userFollowsAddRequest);

    Page<UserVO> getFollowOrFanList(UserfollowsQueryRequest userfollowsQueryRequest);

    Boolean findIsFollow(UserFollowsIsFollowsRequest userFollowsIsFollowsRequest);

    List<Long> getFollowList(Long id);

    FollowersAndFansVO getFollowAndFansCount(Long id);
}
