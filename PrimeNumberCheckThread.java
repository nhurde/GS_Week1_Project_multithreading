package com.company;
import java.util.concurrent.ThreadLocalRandom;

public class PrimeNumberCheckThread extends Thread
{
    private final int numberFinal;

    public PrimeNumberCheckThread(int numberParam)
    {
        super();
        this.numberFinal = numberParam;
    }

    //a getter just in case we set variable elsewhere...
    public int get_number()
    {
        return numberFinal;
    }

    public static void main(String[] args )
    {
        PrimeNumberCheckThread threadNumber1 = new PrimeNumberCheckThread( ThreadLocalRandom.current().nextInt(500) );
        PrimeNumberCheckThread threadNumber2 = new PrimeNumberCheckThread( ThreadLocalRandom.current().nextInt(1000) );
        Thread t = new Thread( threadNumber1 );
        Thread t2 = new Thread( threadNumber2 );
        t.start();
        t2.start();
    }

    @Override
    public void run()
    {
        run_prime_check( false );
    }

    //A slower function, as in 2022 it is assumed a faster method exists, but here is a general for-loop and ifs
    public void run_prime_check( boolean acceptNegativeNumbers )
    {
        int actualNumber = this.numberFinal;

        //Fixes any negative integer values.
        if( actualNumber < 0 )
        {
            if( acceptNegativeNumbers )
            {
                actualNumber *= -1;
            }
            else
            {
                System.out.println("Warning negative number used, but not accepted ["+ actualNumber +"]." );
                return;
            }
        }

        if( actualNumber <= 3 )
        {
            System.out.println(" ["+ actualNumber +"] IS a prime number." );
            return;
        }
        for (int iNum = 2; iNum < actualNumber; iNum++)
        {
            if (actualNumber % iNum == 0)
            {
                System.out.println(" ["+ actualNumber +"] IS NOT a prime number(Divided by " + iNum +")." );
                return;
            }
        }

        System.out.println(" ["+ actualNumber +"] is a prime number." );
    }
}
