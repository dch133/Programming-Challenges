
/*
Problem: Sofas Collection
ProblemID: sofas

Mr. Caron owns a small company, called Gazo, selling home furniture in a French village.
Mr. Caron buys the furniture from a bigger company, called Mazo, and sells it at slightly
higher costs to make a profit. Today, he found a good deal at Mazo on nice sofas. These
sofas are each characterized by a style and a color. There are 36 different styles and 36
different colors. In total, there are 1296 different sofas. However, today, not all these sofas
are available at Mazo.
To satisfy customers and still take advantage of the deal, Mr. Caron decided to get from
Mazo today all combinations of k styles and k colors, for a k value that is as large aspossible. 
However, he has discovered that determining this k for a given collection is not
always trivial. Can you help him?

Input
On the first line of the input, there is a single positive integer n, telling the number of test
scenarios to follow. Each test scenario begins with a line containing a single positive
integer m ≤ 100, the number of sofas available at Mazo. Then follow m lines, one per
sofa, each with a pair of numbers, s i and c i , separated by a single space,
where s i (0<s i ≤36) indicates the style of the ith sofa, and c i (0<c i ≤36) indicates its color.

Output
For each test scenario, output one line containing the maximum k, such that there
are k styles and k colors for which Mr. Caron’s sofas collection contain all k ⋅
k combinations of those styles and colors.

Sample Input 1:
2
5
11 13
23 5
17 36
11 5
23 13
2
23 15
15 23

Sample Output 1:
2
1
*/

import java.util.*;

public class Sofas
{

    //Generate a power set
    public static <T> Set<Set<T>> powerSet(Set<T> set)
    {
        T[] element = (T[]) set.toArray();
        final int SET_LENGTH = 1 << element.length;
        Set<Set<T>> powerSet = new HashSet<>();
        for (int binarySet = 0; binarySet < SET_LENGTH; binarySet++)
        {
            Set<T> subset = new HashSet<>();
            for (int bit = 0; bit < element.length; bit++)
            {
                int mask = 1 << bit;
                if ((binarySet & mask) != 0)
                {
                    subset.add(element[bit]);
                }
            }
            powerSet.add(subset);
        }
        return powerSet;
    }

    //update add value to a list values in hashmap given a key
    public static HashMap<Integer, HashSet<Integer>> updateHashMap (HashMap<Integer, HashSet<Integer>> database, int key, int value)
    {
        HashSet<Integer> values = new HashSet<>();

        if (database.get(key) == null)
        {
            values.add(value);
        } else
        {
            values = database.get(key);
            values.add(value);
        }
        database.put(key, values);

        return database;
    }

    public static int getNumberOfDuplicates(ArrayList<Integer> totalColorList)
    {
        Collections.sort(totalColorList);
        int dup = 0;
        int counter = 0;
        for (int i = 0; i < totalColorList.size(); i++)
        {
            int value = totalColorList.get(i);
            int j = i;
            while (totalColorList.get(j) == value)
            {
                counter++;
                j++;
                if (j == totalColorList.size()) break;
            }
            i = j-1;
            if (counter > 1) dup++;
            counter = 0;
        }
        return dup;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int m;

        HashMap<Integer, HashSet<Integer>> StylesToColorList = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> ColorsToStyleList = new HashMap<>();

        int first, second; //get the 2 numbers

        //loop through all equations
        for (int i = 0; i < n; i++)
        {
            m = sc.nextInt();
            StylesToColorList.clear();

            for (int j = 0; j < m; j++)
            {
                first = sc.nextInt();
                second = sc.nextInt();

                //For each style get the distinct list of colors associated with it and vice-versa
                StylesToColorList = updateHashMap(StylesToColorList, first, second);

            }
            int colorCounter;
            int styleCounter;
            int max = 1;

            //Generate all subsets of styles and try to match the biggest number of colors with them
            Set< Set<Integer> > combinations = powerSet( StylesToColorList.keySet() );

            //Find the size of the common subset of colors for the biggest combo of styles
            for (Set<Integer> styleSet : combinations)
            {
                ArrayList<Integer> totalColorList = new ArrayList<>(); //combine all colors and look for duplicates
                for (int style : styleSet)
                {
                    Set<Integer> colorList = StylesToColorList.get(style);
                    for (int color : colorList)
                    {
                        totalColorList.add(color);
                    }
                }
                styleCounter = styleSet.size();
                colorCounter = getNumberOfDuplicates(totalColorList);

                //Get the smallest style*color combinations and update our current max k*k value
                max = Math.max(max, Math.min(styleCounter,colorCounter));
            }

            System.out.println(max);
        }
    }
}

