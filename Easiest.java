/* https://open.kattis.com/problems/easiest */

import java.util.*;

public class Easiest
{

    public static int SumDigits(String s)
    {
        int sum = 0;
        for (int i = 0; i < s.length(); i++)
            { sum += Integer.parseInt(s.charAt(i)+""); }
        return sum;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n;
        int sum;
        do
        {   
            n = sc.nextInt();
            if(n == 0) break;
            
            int sumOfNum = SumDigits(n+"");
            for (int j = 11; j < Integer.MAX_VALUE; j++)
            {
                if (SumDigits((j*n)+"")== sumOfNum)
                {
                    System.out.println(j);
                    break;
                }
            }
        }   
        while (true);
    }    

}