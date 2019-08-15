package com.carl.exportexcle.entity;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class TaskExecResultInfo implements Serializable {

    private static final long serialVersionUID = 8339965085366142506L;
    private int mIndex;
    // 任务流水号，与任务Id不同
    private String mTaskFlowId;
    private String mUserId;
    private String mAppName;
    private String mTaskId;
    private String mPhoneNum;
    private String mChanId;
    private String mClickURL;
    // 任务执行状态
    private int mState;
    // 状态变更时间
    private String mChangeTime;

    /**
     * @return the mIndex
     */
    public int getIndex() {
        return mIndex;
    }

    /**
     * @param
     */
    public void setIndex(int index) {
        this.mIndex = index;
    }

    /**
     * @return the mUserId
     */
    public String getUserId() {
        return mUserId;
    }

    /**
     * @param
     */
    public void setUserId(String userId) {
        this.mUserId = userId;
    }

    /**
     * @return the mAppName
     */
    public String getAppName() {
        return mAppName;
    }

    /**
     * @param
     */
    public void setAppName(String appName) {
        this.mAppName = appName;
    }

    /**
     * @return the mTaskId
     */
    public String getTaskId() {
        return mTaskId;
    }

    /**
     * @param
     */
    public void setTaskId(String taskId) {
        this.mTaskId = taskId;
    }

    /**
     * @return the mPhoneNum
     */
    public String getPhoneNum() {
        return mPhoneNum;
    }

    /**
     * @param
     */
    public void setPhoneNum(String phoneNum) {
        this.mPhoneNum = phoneNum;
    }

    /**
     * @return the mChanId
     */
    public String getChanId() {
        return mChanId;
    }
    /**
     * @param
     */
    public void setChanId(String chanId) {
        this.mChanId = chanId;
    }

    /**
     * @return the mSucceed
     */
    public int getTaskRunningState() {
        return mState;
    }

    /**
     * @param
     */
    public void setTaskRunningState(int state) {
        this.mState = state;
    }

    public String getStateChangedTime() {
        return mChangeTime;
    }

    public void setStateChangedTime(String ts) {
        mChangeTime = ts;
    }

    public String getTaskFlowId() {
        return mTaskFlowId;
    }

    public void setTaskFlowId(String id) {
        mTaskFlowId = id;
    }

    public String getClickURL() {
        return mClickURL;
    }

    public void setClickURL(String curl) {
        mClickURL = curl;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(null == obj) {
            return false;
        }

        if(!(obj instanceof TaskExecResultInfo)) {
            return false;
        }

        TaskExecResultInfo other = (TaskExecResultInfo) obj;

        if(mIndex != other.mIndex) {
            return false;
        }

        if(!StringUtils.equals(mTaskFlowId, other.mTaskFlowId)) {
            return false;
        }

        if(!StringUtils.equals(mUserId, other.mUserId)) {
            return false;
        }

        if(!StringUtils.equals(mAppName, other.mAppName)) {
            return false;
        }

        if(!StringUtils.equals(mTaskId, other.mTaskId)) {
            return false;
        }

        if(!StringUtils.equals(mPhoneNum, other.mPhoneNum)) {
            return false;
        }

        if(!StringUtils.equals(mChanId, other.mChanId)) {
            return false;
        }

        if(mState != other.mState) {
            return false;
        }

        if(!StringUtils.equals(mClickURL, other.mClickURL)) {
            return false;
        }

        return true;
    }

}

