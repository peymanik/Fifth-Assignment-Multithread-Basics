package sbu.cs;

import java.awt.desktop.ScreenSleepEvent;
import java.util.*;

public class TaskScheduler
{
    public static class Task implements Runnable
    {
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */
        String taskName;
        int processingTime;

        public Task(String taskName, int processingTime) {
            this.taskName = taskName;
            this.processingTime = processingTime;
        }
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */

        @Override
        public void run() {
            /*
            TODO
                Simulate utilizing CPU by sleeping the thread for the specified processingTime
             */
            try{ Thread.sleep(processingTime); }
            catch(InterruptedException e) { System.out.println("Thread intrrupted"); }
        }
    }

    public static ArrayList<String> doTasks(ArrayList<Task> tasks)
    {
        ArrayList<String> finishedTasks = new ArrayList<>();
        /*
        TODO
            Create a thread for each given task, And then start them based on which task has the highest priority
            (highest priority belongs to the tasks that take more time to be completed).
            You have to wait for each task to get done and then start the next task.
            Don't forget to add each task's name to the finishedTasks after it's completely finished.
         */
        ArrayList<Task> tasksCopy = new ArrayList<>();
        for (int i=0 ; i< tasks.size() ; i++)
            tasksCopy.add(tasks.get(i));

        for (int i=0 ; i < tasks.size() ; i++){
            int maxIndex = 0;
            for (int j=0 ; j<tasksCopy.size() ; j++)
                if (tasksCopy.get(j).processingTime > tasksCopy.get(maxIndex).processingTime)
                    maxIndex = j;

            Thread thread = new Thread(tasksCopy.get(maxIndex));
            thread.start();
            try { thread.join(); }
            catch (Exception e){ System.out.println("Error"); }

            finishedTasks.add(tasksCopy.get(maxIndex).taskName);
            tasksCopy.remove(maxIndex);
        }
        return finishedTasks;
    }

    public static void main(String[] args) {
        // Test your code here
    }
}
