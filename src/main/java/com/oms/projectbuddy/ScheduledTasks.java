//package com.oms.projectbuddy;
//
//import com.oms.projectbuddy.services.ISkillMatrixService;
//import io.micronaut.websocket.annotation.WebSocketComponent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import io.micronaut.http.annotation.*;
//import java.text.SimpleDateFormat;
//
//@WebSocketComponent
//public class ScheduledTasks {
//
//    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//    @Inject
//    private ISkillMatrixService iSkillMatrixService;
//
////    //        @Scheduled(cron = "0 0/15 * * * *")
////    @Scheduled(fixedDelay = 1000)
////    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void scheduleTaskForUpdateTheLevels() {
//        try {
////            iSkillMatrixService.updateLevelsHearichy("LEVEL-1");
////            iSkillMatrixService.updateLevelsHearichy("LEVEL-2");
//            iSkillMatrixService.updateLevelsHearichy("LEVEL-3");
////            iSkillMatrixService.updateLevelsHearichy("LEVEL-4");
////            iSkillMatrixService.updateLevelsHearichy("LEVEL-5");
////            iSkillMatrixService.updateLevelsHearichy("LEVEL-6");
////            iSkillMatrixService.updateLevelsHearichy("LEVEL-7");
//        } catch (
//                Exception error) {
//            error.printStackTrace();
//        }
//    }
//}
