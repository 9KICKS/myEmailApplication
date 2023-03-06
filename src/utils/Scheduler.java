package utils;

import controllers.EMailController;
import data.models.EMail;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    public static void runTask(EMailController eMailController) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> {
            for (Iterator<EMail> iterator = eMailController.getTrash().iterator(); iterator.hasNext();) {
                EMail eMail = iterator.next();
                if (LocalDateTime.now().isAfter(eMail.getDeletedTime().plusDays(7))) {
                    iterator.remove();
                }
            }
        };
        long initialDelay = 0;
        long period = 60 * 60 * 24;
        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
    }
}