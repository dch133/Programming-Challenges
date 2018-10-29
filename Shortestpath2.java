
import java.util.*;

public class Shortestpath2
{
    int _first;
    int _second;

    public Shortestpath2(int f, int s) { _first = f; _second = s; }

    int first() { return _first; }
    int second() { return _second; }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        { //get the data
            int n, m, q, s;
            String[] input = (sc.nextLine()).split(" ");
            n = Integer.parseInt(input[0]);
            m = Integer.parseInt(input[1]);
            q = Integer.parseInt(input[2]);
            s = Integer.parseInt(input[3]);

            if (n == 0 && m == 0 && q == 0 && s == 0) break;

            Map<Integer, ArrayList<Integer>> dict = new TreeMap<>();
            //generate the best possible path
            List<Integer> best = new ArrayList<>();

            for (int i = 0; i < m; i++)//going through all the edges
            {
                int u, v, t, p, w;

                String[] edgeData = (sc.nextLine()).split(" ");
                u = Integer.parseInt(edgeData[0]);
                v = Integer.parseInt(edgeData[1]);
                t = Integer.parseInt(edgeData[2]);
                p = Integer.parseInt(edgeData[3]);
                w = Integer.parseInt(edgeData[4]);
                ArrayList<Integer> data = new ArrayList<>();
                data.add(v);
                data.add(t);
                data.add(p);
                data.add(w);
                if (dict.containsKey(u))
                {
                    ArrayList<Integer> oldData = dict.get(u);
                    oldData.addAll(data);
                    dict.put(u, oldData);
                } else
                {
                    dict.put(u, data);
                }

            }

            PriorityQueue<Shortestpath2> pq = new PriorityQueue<>(1, new Comparator<Shortestpath2>()
            { // overriding the compare method
                public int compare(Shortestpath2 i, Shortestpath2 j)
                {
                    return i.first() - j.first();
                }
            });
            pq.offer(new Shortestpath2(0, s));  //starting position pushed on queue
            while (best.size() < n || !pq.isEmpty()) //loop until we go through all nodes n
            {
                int cost, curr;
                Shortestpath2 top = pq.poll(); // greedy: pick shortest unvisited vertex
                if (top == null) break;
                cost = top.first();
                curr = top.second();

                //if (dict.get(curr).isEmpty()) continue;//No edge from this node

                if (best.contains(curr) && best.get(curr) <= cost) continue;
                //update best[]
                if (curr >= best.size()) //index not exists
                {
                    if (cost == 0) best.add(curr, -1);
                    else best.add(curr, cost);
                } else // index exists
                {
                    if (cost == 0) best.set(curr, -1);
                    else best.set(curr, cost);
                }
                List<Integer> data = dict.get(curr);
                if (data == null)
                {
                    pq.poll();
                    continue;
                }
                for (int j = 0; j < data.size(); j += 4) //check time cost for each possibility edge from curr
                {
                    int dest, time, period, weight; //dest is destination node
                    int newTime = cost;
                    dest = data.get(0 + j);
                    time = data.get(1 + j);
                    period = data.get(2 + j);
                    weight = data.get(3 + j);

                    if (cost < time) // if you get there before the gate opens you wait until it opens
                    {
                        newTime = time;
                    } else if (period == 0)
                    {
                        if (cost != time)
                            continue;
                        //if you get there late (period != 0): check if you can wait a scalar * period amount of time until the gate opens again
                    } else if ((cost - time) % period != 0) // if not: get that extra 'time' required to wait time_node_opens-remainder
                    {
                        int r = period - ((cost - time) % period);
                        newTime = cost + r; //add that time adjustment to get into that node
                    }
                    //if we previously reached destination node in less time: use that instead
                    if (best.contains(dest) && best.get(dest) <= newTime)
                        continue;
                    //add into our list the time it took to get to destination node
                    pq.offer(new Shortestpath2(newTime + weight, dest));
                }
            }

            for (int i = 0; i < q; i++)
            {
                int node = Integer.parseInt(sc.nextLine());
                if (node >= best.size() || best.get(node) == 0)
                {
                    System.out.println("Impossible");
                    continue;
                }
                if (best.get(node) == -1) System.out.println(0);
                else System.out.println(best.get(node));
            }
            System.out.println();

        }
    }
}
