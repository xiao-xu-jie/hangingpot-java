package com.origin.hangingpot.port.control.strategy.context;

import com.origin.hangingpot.port.control.strategy.factory.SyncFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @Author: YourName
 * @Date: 2024/6/5 18:00
 * @Description:
 **/
@Service
@RequiredArgsConstructor
public class SyncContext {
    public void SyncData(Long sourceId, Long destId, String startTime, String endTime,String type,Long projectId,String runType){
        try {
            SyncFactory.getSyncStrategy(type).SyncData(sourceId,destId,startTime,endTime,runType,projectId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
