package com.carl.exportexcle.dao;

import com.carl.exportexcle.entity.TaskExecResultInfo;
import com.carl.exportexcle.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoDao {
    List<UserInfo> getUserCountByDateAndApp(@Param("appName")String appName, @Param("beginTime")String beginTime, @Param("endTime")String endTime);


    Integer getRightCountByDate(@Param("beginTime")String beginTime, @Param("endTime")String endTime);


    List<TaskExecResultInfo> gotTaskUserCountByDate(@Param("beginTime")String beginTime, @Param("endTime")String endTime);


    Integer gotOfferCountByDate(@Param("beginTime")String beginTime, @Param("endTime")String endTime);


}
