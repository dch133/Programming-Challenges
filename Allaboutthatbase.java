/*
https://open.kattis.com/problems/allaboutthatbase
*/
import java.util.*;

public class Allaboutthatbase
{
    static String convertFromBaseToBase10(String str, int fromBase)
    {
        return Integer.toString(Integer.parseInt(str, fromBase), 10);
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<String> operatorList = new ArrayList<String>();
        List<String> operandList = new ArrayList<String>();
        
        //loop through all equations
        for (int i = 0; i < n; i++)
        {
            String input = sc.nextLine();
            input = input.replaceAll("\\s+",""); //remove spaces
            operandList.clear();
            operatorList.clear();
            boolean existsBase = false;
            boolean isConvertedToBase = true;
            //Check if the left hand side is equal to right hand side in every base
            for (int j = 1; j < 37; j++)
            {
                operandList.clear();
                operatorList.clear();
                StringTokenizer st = new StringTokenizer(input, "+-*/=", true);
                isConvertedToBase = true;
                //separate X op Y = Z in to [X,Y,Z] and [op]
                while (st.hasMoreTokens())
                {
                    String token = st.nextToken(); 
                    //System.out.println(token+1);
                    if ("+-/*=".contains(token))
                        operatorList.add(token);
                    else
                    {
                        try {
                            if (j > 1) operandList.add(convertFromBaseToBase10(token,j));
                            else operandList.add(token);
                        }
                        catch (Exception e)
                        {
                            isConvertedToBase = false;
                            break;
                        }
                    }
                }
                //If we can't even convert input into some base, try a different one
                if (!isConvertedToBase) continue;

                int left1; int left2; int right;
                if (j > 1)
                {
                    left1 = Integer.parseInt(operandList.get(0));
                    left2 = Integer.parseInt(operandList.get(1));
                    right = Integer.parseInt(operandList.get(2));
                }
                else //Check validity of number in base 1 (111...) and convert to decimal
                {
                    left1 = operandList.get(0).length();
                    left2 = operandList.get(1).length();
                    right = operandList.get(2).length();
                    if (!(operandList.get(0).replaceAll("1", "").isEmpty()
                            && operandList.get(1).replaceAll("1", "").isEmpty()
                            && operandList.get(2).replaceAll("1", "").isEmpty()))
                    {
                        continue;
                    }
                }
                //Check if operation is valid in current base j
                if (   (operatorList.get(0).equals("+") && left1+left2 == right)
                    || (operatorList.get(0).equals("-") && left1-left2 == right)
                    || (operatorList.get(0).equals("*") && left1*left2 == right)
                    || (operatorList.get(0).equals("/") && left2*right == left1)
                   )
                {
                    existsBase = true;
                    if (j > 9)
                    {
                        if (j == 36) System.out.print(0);
                        else System.out.print((char)(j+87));

                    }
                    else System.out.print(j);
                }
                else if (!existsBase && j == 36)
                    System.out.print("invalid");
            }
            System.out.println();
        }
    }
}

