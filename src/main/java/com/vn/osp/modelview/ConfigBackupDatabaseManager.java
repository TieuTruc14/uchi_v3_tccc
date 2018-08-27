package com.vn.osp.modelview;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.SendMailUtil;
import com.vn.osp.common.util.SystemProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

/**
 * Created by VIETDUNG on 25/04/2017.
 */
public class ConfigBackupDatabaseManager {
    private static final Logger logger = LoggerFactory.getLogger(ConfigBackupDatabaseManager.class);
    private static final String file_name_bat = "backup.bat";
    private static final String CONFIG_MYSQL_FORDER = "system_mysql_mysqldump_folder";
    private static final String CONFIG_BACKUP_DATABASE_FOLDER = "system_backup_database_folder";
    private static final String TIME_BACKUP = "time_bacup";
    private static final String DATE_BACKUP = "date_backup";

    public String createBackupFileBat() {
        logger.debug("vào ConfigBackupDatabaseManager hàm createBackupFileBat ---------------------------------");
        try {
            String filebat = file_name_bat;
            if (SystemProperties.getProperty("system_backup_os").equals("1")) filebat = "backup.sh";
            File file = new File(SystemProperties.getProperty(CONFIG_BACKUP_DATABASE_FOLDER) + filebat);
            File folder = new File(SystemProperties.getProperty(CONFIG_BACKUP_DATABASE_FOLDER));
            if (file.exists()) {
                file.delete();
            } else {
                if (!folder.exists())
                    folder.mkdirs();
            }
            file.createNewFile();
            String database = SystemProperties.getProperty("system_backup_database");
            String user = SystemProperties.getProperty("system_backup_user");
            String pass = SystemProperties.getProperty("system_backup_pass");
            String host = SystemProperties.getProperty("system_backup_host");
            String port = SystemProperties.getProperty("system_backup_port");

            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println("cd " + SystemProperties.getProperty(CONFIG_MYSQL_FORDER));
            String oDia = SystemProperties.getProperty(CONFIG_MYSQL_FORDER).substring(0, 2);
            writer.println(oDia);
            Calendar cal = Calendar.getInstance();
            String filename = "backup-" + cal.get(Calendar.YEAR) + "-" + String.valueOf(cal.get(Calendar.MONDAY) + 1) + "-" + cal.get(Calendar.DATE) + "-" + cal.get(Calendar.HOUR_OF_DAY) + "-" + cal.get(Calendar.MINUTE) + "-" + cal.get(Calendar.SECOND) + ".sql \"";

            writer.println("mysqldump -u" + user + " -p" + pass + " -h" + host + " -P" + port + " " + database + " -r \"" + SystemProperties.getProperty(CONFIG_BACKUP_DATABASE_FOLDER) + filename);
            writer.println("Exit");
            writer.close();
            return filename;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void backUpAutomatic() {
        logger.debug("vào ConfigBackupDatabaseManager hàm backUpAutomatic ---------------------------------");
        try {
            createBackupFileBat();
            Runtime c = Runtime.getRuntime();
            String cmd = "cmd /c start " + SystemProperties.getProperty(CONFIG_BACKUP_DATABASE_FOLDER) + "backup.bat";
            if (SystemProperties.getProperty("system_backup_os").equals("0")) {
                c.exec(cmd, null);
            } else {
                c.exec(SystemProperties.getProperty("system_backup_database_folder") + "backup.sh",
                        null, new File(SystemProperties.getProperty("system_backup_database_folder")));

            }
            /*Process pro = c.exec(cmd , null);*/
            String[] emails = SystemProperties.getProperty(Constants.CONFIG_EMAIL_BACKUP).replaceAll("\\s+", "").split(",");
            SendMailUtil.sendGmail(emails, "Backup Database", "Backup success.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public boolean checkBackUp() {
        logger.debug("vào ConfigBackupDatabaseManager hàm checkBackUp ---------------------------------");
        boolean result = false;
        String timeBackUp = SystemProperties.getProperty(TIME_BACKUP);
        int hour = Integer.parseInt(timeBackUp.substring(0, 2));
        int minute = Integer.parseInt(timeBackUp.substring(3, 5));
        String pa = timeBackUp.substring(6);

        Calendar cal = Calendar.getInstance();
        int index = Calendar.DAY_OF_WEEK;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            index = 6;
        } else {
            index = cal.get(Calendar.DAY_OF_WEEK) - 2;
        }
        String dateBackUp = SystemProperties.getProperty(DATE_BACKUP);
        List<Boolean> listDatebackup = EditString.parseListDateBackup(dateBackUp);
        if (listDatebackup.get(index)) {
            if (pa.equals("AM")) {
                if (hour == cal.get(Calendar.HOUR_OF_DAY)) {
                    if (minute == cal.get(Calendar.MINUTE)) {
                        result = true;
                    } else {
                        result = false;
                    }
                } else {
                    result = false;
                }
            } else {
                if ((hour + 12) == cal.get(Calendar.HOUR_OF_DAY)) {
                    if (minute == cal.get(Calendar.MINUTE)) {
                        result = true;
                    } else {
                        result = false;
                    }
                } else {
                    result = false;
                }
            }
        } else {
            return false;
        }
        return result;
    }
}
