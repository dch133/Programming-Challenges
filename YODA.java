/* https://open.kattis.com/problems/yoda */

import java.util.*;


public class YODA 
{    

    public static void main(String[] args)
    {         
        //Get the 2 numbers
        Scanner sc = new Scanner(System.in);
        String s1 = new StringBuilder(sc.nextLine()).reverse().toString();
        String s2 = new StringBuilder(sc.nextLine()).reverse().toString();
        sc.close();

        //Convert to char array
        char[] n1 = s1.toCharArray();
        char[] n2 = s2.toCharArray();
        
        //Get the smaller length (only compare till then)
        int length = 0;
        if(n1.length > n2.length) length = n2.length; else length = n1.length;
        
        //Compare values (Collision)
        for (int i = 0; i < length; i++)
        {
            if(n1[i] > n2[i]) n2[i] = '\0'; 
            else if (n1[i] == n2[i]) continue;
            else n1[i] = '\0';
        }
        // print the result
        s1 = ""; s2 = "";
        for (int i = 0; i < n1.length; i++) 
        {
            if (n1[i]=='\0') continue;
            s1 += n1[i];
        }
        for (int i = 0; i < n2.length; i++) 
        {
            if (n2[i]=='\0') continue;
            s2 += n2[i];
        }
        
        
        if (s1.equals("")) System.out.println("YODA");
        else if (s1.matches("[0]+")) System.out.println(0);
        else System.out.println(new StringBuilder(s1).reverse().toString());

        if (s2.equals("")) System.out.println("YODA"); 
        else if (s2.matches("[0]+")) System.out.println(0);
        else System.out.println(new StringBuilder(s2).reverse().toString());


    }
}
