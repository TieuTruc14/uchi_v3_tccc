package com.vn.osp.task;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BackupDBTask extends TimerTask implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(BackupDBTask.class.getName());
    private ScheduledExecutorService scheduler;

    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable command = new BackupThread(event.getServletContext());
        // Delay 1 Minute to first execution

        long initialDelay = 1;
        TimeUnit unit = TimeUnit.MINUTES;
        // period the period between successive executions
        long period = 1;// 1 Minute!

        scheduler.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }

    @Override
    public void run() {
        logger.info("Hahahahahahahahahahaha-----------------------------------");
    }
}