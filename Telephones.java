/* https://open.kattis.com/problems/telephones */

import java.util.ArrayList;
import java.util.Scanner;

public class Telephones
{
    int start;
    int finish;

    public Telephones(int s, int f)  {  start = s;  finish = f; }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        { //get the data
            int n, m;
            n = sc.nextInt();
            m = sc.nextInt();

            if (n == 0 && m == 0) break;
            ArrayList<Telephones> intervals = new ArrayList<>();
            //Get the telephone calls data
            while (n-- > 0)
            {
                int source = sc.nextInt();
                int destination = sc.nextInt();
                int start = sc.nextInt();
                int duration = sc.nextInt();

                Telephones callTimeInterval = new Telephones(start, start+duration);
                intervals.add(callTimeInterval);
            }
            //Get the time intervals and calculate the # calls
            while (m-- > 0)
            {
                int start = sc.nextInt();
                int duration = sc.nextInt();
                int numCalls = 0;
                for (Telephones call: intervals)
                {
                    if (call.start < start + duration && call.finish > start)
                        //call starts before interval finishes, and call ends after interval starts
                        numCalls++;
                }
                System.out.println(numCalls);
            }
        }
    }

}
