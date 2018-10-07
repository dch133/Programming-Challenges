/* httpsopen.kattis.comproblemsdowntime */

import java.util.*;


public class Downtime{

    public static void main(String[] args) 
    {   
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        
        int[] time = new int[n];
        int counter = 1; //consecutive process count
        for (int i = 0; i < n; i++) time[i] = sc.nextInt();
        int baseCase = 1; // at least 1 request running 
        //loop through processes and see if they fint in a 1 sec block.
        int ptr = 0;
        for (int i = 1; i < n; i++)
        {
            //Increment by 1sec until current process finishes running OR hit the next process
            while (time[ptr] + 1000 <= time[i]) 
            {
                ptr++; //shift ptr if we successfully covered our 1 sec block
                counter--; //The process that just finished & we remove it from the count
            }
            counter++; //count how many consecutive processes accumulate (fit in 1 sec block)
            //update the # requests currently running at the same time
            baseCase = Math.max(baseCase, counter); 

        }
        //do ceil since if we have no simultaneous requets running we still have 1 server
        System.out.println((int) Math.ceil(1.0*baseCase/k));

        sc.close();
    }
}
