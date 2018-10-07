/*
Problem: Vegetable Baskets 
ProblemID: vbasket 

Sami is the boss of a vegetables company (Sami Vegetables). He wants to introduce a new product – vegetable baskets. 
They will contain all possible combinations of vegetables to satisfy different customer needs,
but at most one piece of each type of vegetables. He doesn’t want to have small vegetable baskets,
so all baskets must contain at least 200 grams of vegetables. 

You have tolerated his ideas before, but you can’t put up with this one. You realized that the company
might end up with 2^(n?1) different baskets, where n is the number of types of vegetables the company sells.
You know that Sami will not understand this argument, so you are going to convince him in a different way. 
You want to tell him the total weight of vegetables of all possible baskets, to clarify that he will need
a lot of trucks just to carry them. 

For simplicity, you assume that all vegetables of the same kind weigh the same.
You also know that all vegetables weigh at least 50 grams.

Input:
The first line of input contains an integer N,1?N?40. The second line contains N integers – weights of available
vegetables types in grams. All weights are integers between 50 and 1000.

Output:
Output one line with one integer – the total weight of all vegetables in the baskets that have 
at least 200 grams of vegetables.

Sample Input 1:
4
50 60 70 120

Sample Output 1:
1020

*/
import java.util.*;


public class Vbasket
{
    static ArrayList<Integer> printSubsets(int n, int set[]) 
    { 
        ArrayList<Integer> validBaskets = new ArrayList<>();
        int sum = 0;
        // Run a loop for printing all 2^n 
        // subsets one by one 
        for (int i = 0; i < (1<<n); i++) 
        { 
            sum = 0;
            // Print current subset 
            for (int j = 0; j < n; j++) 
            {
                // (1<<j) is a number with jth bit 1 
                // so when we 'and' them with the 
                // subset number we get which numbers 
                // are present in the subset and which 
                // are not 
                if ((i & (1 << j)) > 0) 
                    sum += set[j];   
            }
            if (sum >= 200) validBaskets.add(sum); //save the baket weigth ofer 200
        }
        return validBaskets;
    } 
    public static void main(String[] args)
    {
 //Get the values from input
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] veggies = new int[n];
        for (int i = 0; i < n; i++) veggies[i] = sc.nextInt();
           
        ArrayList<Integer> basketsWeights = printSubsets(n,veggies);
        int total = 0;
        //calculate the total weight
        for (int i = 0; i < basketsWeights.size(); i++) total += basketsWeights.get(i);

        System.out.println(total);
        sc.close();
            
        
    }
}
