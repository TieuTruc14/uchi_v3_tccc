package com.vn.osp.modelview;

import com.vn.osp.common.util.SystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by VIETDUNG on 25/04/2017.
 */
public class BackupEngine {
    private static final Logger logger = LoggerFactory.getLogger(BackupEngine.class);
    static private BackupEngine _instance;
    private String CHECK_BACKUP = "backup_check";

    /**
     * The constructor could be made private to prevent others from
     * instantiating this class. But this would also make it impossible to
     * create instances of Singleton subclasses.
     */

    public BackupEngine() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return The unique instance of this class.
     */
    static public BackupEngine instance() {
        if (null == _instance) {
            _instance = new BackupEngine();
        }
        return _instance;
    }

    /**
     * @method Backup Database
     */

    public void BackupDatabase(){
        logger.debug("vào BackupEngine -----------------------------------------!");
        if(Boolean.parseBoolean(SystemProperties.getProperty(CHECK_BACKUP))){
            ConfigBackupDatabaseManager cfManager = new ConfigBackupDatabaseManager();
            if(cfManager.checkBackUp()){
                cfManager.backUpAutomatic();
            }
        }
    }
}
