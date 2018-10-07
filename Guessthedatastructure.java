/* https://open.kattis.com/problems/guessthedatastructure */
import java.util.*;


public class Guessthedatastructure
{


public static void main(String[] args)
{
    int n;
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext())
    {
        n = sc.nextInt();
        boolean isStack = true, isQueue = true, isPQueue = true;
        
        //Set up datastructure where the input will be tested
        Stack<Integer> usedStack = new Stack<Integer>();
        Queue<Integer> usedQueue = new LinkedList<Integer>();
        PriorityQueue<Integer> usedPriority = new PriorityQueue<Integer>((x, y) -> y - x);
        
        while (n-- > 0)
        {
            int first, second; //get the 2 numbers
            first = sc.nextInt();
            second = sc.nextInt();
            
            //Select whether to add/remove number based on type_1 or type_2
            switch (first)
            {
            case 1:
                if (isStack)
                    usedStack.push(second);
                if (isQueue)
                    usedQueue.add(second);
                if (isPQueue)
                    usedPriority.add(second);
                break;
                
            case 2:
                if (isStack)
                {
                    if (usedStack.isEmpty() || usedStack.peek().intValue() != second)
                        isStack = false;
                    else
                        usedStack.pop();
                }
                if (isQueue)
                {
                    if (usedQueue.isEmpty() || usedQueue.peek().intValue() != second)
                        isQueue = false;
                    else
                        usedQueue.remove();
                }
                if (isPQueue)
                {
                    if (usedPriority.isEmpty() || usedPriority.peek().intValue() != second)
                        isPQueue = false;
                    else
                        usedPriority.remove();
                }
                break;
            }
        }

        //Compare input and output based on how they tested with each datastructure
            if(isQueue && isStack || isQueue && isPQueue|| isStack && isPQueue)
                System.out.println("not sure");
            
            else if (isQueue)      
                System.out.println("queue");
            
            else if (isPQueue)
                System.out.println("priority queue");
            
            else if (isStack) 
                System.out.println("stack");
            else
                System.out.println("impossible");

        } 
        sc.close();
    }
}