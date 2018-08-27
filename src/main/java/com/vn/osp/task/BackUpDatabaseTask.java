package com.vn.osp.task;

/**
 * Created by VIETDUNG on 25/04/2017.
 */
import com.vn.osp.modelview.BackupEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;


/**
 * @author SONY
 *
 */
public class BackUpDatabaseTask extends TimerTask {
    private static final Logger logger = LoggerFactory.getLogger(BackUpDatabaseTask.class);
    @Override
    public void run() {
        logger.debug("vào BackUpDatabaseTask ------------------------------------!");
        try {
            BackupEngine engine = BackupEngine.instance();
            engine.BackupDatabase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
