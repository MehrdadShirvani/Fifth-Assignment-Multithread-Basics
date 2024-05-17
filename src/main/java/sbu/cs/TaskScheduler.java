package sbu.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskScheduler
{
    public static class Task implements Runnable
    {

        String taskName;
        int processingTime;

        public Task(String taskName, int processingTime) {
            this.taskName = taskName;
            this.processingTime = processingTime;
        }


        @Override
        public void run() {

        }
    }

    public static ArrayList<String> doTasks(ArrayList<Task> tasks)
    {
        ArrayList<String> finishedTasks = new ArrayList<>();
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return Integer.compare(o2.processingTime, o1.processingTime);
            }
        });
        for(Task task : tasks)
        {
            Thread newThread = new Thread(task);
            newThread.run();
            try {
                newThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finishedTasks.add(task.taskName);

        }

        return finishedTasks;
    }

    public static void main(String[] args) {
    }
}
