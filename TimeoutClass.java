package com.company;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TimeoutClass extends TimerTask
{
    //Keeps track of time since past input received ( In this example handle via main/nextLine command line )
    public static boolean inputReceived = false;
    public static int timeSinceLastInput = 0;
    public static int timesBeforeRefresh = 100;
    public static int timeRequiredForInput = 5000;

    public TimeoutClass()
    {
        inputReceived = false;
        timeSinceLastInput = 0;
        timeRequiredForInput = 5000;
    }

    public void ResetTimeout()
    {
        inputReceived = true;
        timeSinceLastInput = 0;
    }

    public static void main(String[] args )
    {
        boolean exitRequested = false;

        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter input or type [exit], [esc] or [end]");
        String userInput = "";

        Timer timer = new Timer();
        TimeoutClass myTimeout = new TimeoutClass();
        TimerTask task = myTimeout;
        timer.scheduleAtFixedRate(task, 0, TimeoutClass.timesBeforeRefresh);

        while( !exitRequested )
        {
            TimeoutClass.inputReceived = false;

            userInput = myObj.nextLine();
            myTimeout.ResetTimeout();

            if( userInput.contains("exit") || userInput.contains("esc") || userInput.contains("end") )
            {
                exitRequested = true;
                System.out.println("Exiting program now... \n \n \n ");
                return;
            }
            TimeoutClass.inputReceived = true;
            System.out.println("Enter input or type [exit], [esc] or [end]");

        }
    }

    @Override
    public void run()
    {
        if( !TimeoutClass.inputReceived )
        {
            TimeoutClass.timeSinceLastInput += TimeoutClass.timesBeforeRefresh;


            if (TimeoutClass.timeSinceLastInput >= TimeoutClass.timeRequiredForInput)
            {
                System.out.println("Sorry, you've been timed out for lack of input ---");
                TimeoutClass.timeSinceLastInput = 0;
            }
        }
    }
}
