
import java.util.*;

public class Narrowartgallery
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int n  = sc.nextInt();
        int k  = sc.nextInt();

        //edge case: gallery is empty OR more blocked cells than available cells
        if (n == 0 && k == 0 || n == 0 || k > n) return;
      
        int[][][] dp = new int[3][200][201];
        //dp has 3d: [0] = block left ; [1] = block right [2] choose both
        int[][] gal = new int[200][2];

        //Fill gallery
        for (int i = 0; i < n; i++)
        {
            int left = sc.nextInt();
            int right = sc.nextInt();

            gal[i][0] = left;
            gal[i][1] = right;
        }

        
        
        //fill memo table with default 'null' values
        for (int[][] row: dp)
            for (int[] cell : row)
                Arrays.fill(cell, Integer.MIN_VALUE);
        
        //Set up base case
        //for k = 0 don't block left/right room
        dp[0][0][0] = Integer.MIN_VALUE;
        dp[1][0][0] = Integer.MIN_VALUE;
        dp[2][0][0] = gal[0][0] + gal[0][1];

        //for k = 1 cannot choose to unblock both rooms
        dp[0][0][1] = gal[0][1];
        dp[1][0][1] = gal[0][0];
        dp[2][0][1] = Integer.MIN_VALUE;
        
        //set up the array where nothing is blocked
        for (int i = 1; i < n; i++)
        {
            dp[2][i][0] = dp[2][i-1][0] + gal[i][0] + gal[i][1];
        }
        //calculate the max sum
        for (int j = 1; j < n; j++)//row size
        { 
            for (int i = 1; i <= j+1; i++)
            { //k (number of blocked cells allowed)
                // don't block any from this row
                int r = Math.max(dp[0][j-1][i], Math.max(dp[1][j-1][i], dp[2][j-1][i]));
                dp[2][j][i] = r + gal[j][0] + gal[j][1];

                // block left
                r = Math.max(dp[0][j-1][i-1], dp[2][j-1][i-1]);
                dp[0][j][i] = r + gal[j][1];

                // block right
                r = Math.max(dp[1][j-1][i-1], dp[2][j-1][i-1]);
                dp[1][j][i] = r + gal[j][0];
            }
        }
        //Print highest result between choosing the left/right or both unblocked
        System.out.println(Math.max(dp[0][n-1][k], Math.max(dp[1][n-1][k], dp[2][n-1][k])));



    }

}

