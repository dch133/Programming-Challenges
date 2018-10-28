
# https://open.kattis.com/problems/shortestpath2

import heapq

from collections import defaultdict


while True:
    n, m, q, s = map(int, input().split()) #get the data
    if n == m == q == s == 0:
        break
    d = defaultdict(list)
    best = {} #generate the best possible path at each node (index)
    for _ in range(m): #going through all the edges
        u, v, time, period, weight = map(int, input().split())
        d[u].append((v, time, period, weight))
    l = []
    heapq.heappush(l, (0, s)) #starting position pushed on queue
    while len(best) < n and len(l): #loop until we go through all nodes n
        cost, curr = heapq.heappop(l)
        if curr in best and best[curr] <= cost:
            continue
        best[curr] = cost
        for dest, time, period, weight in d[curr]: #dest is destination node
            nt = cost #nt = new time at which node available
            if cost < time: # if you get there before the gate opens you wait until it opens
                nt = time
            elif period == 0:    #if period is 0 impossible to get in if you're late
                if cost != time: 
                    continue
            elif (cost - time) % period != 0: #if you get there late: check if you can wait until the gate opens again
                r = period - ((cost - time) % period) # if not: get that extra 'time' required to wait time_node_opens-remainder
                nt = cost + r #add that time adjustment to get into that node
            if dest in best and best[dest] <= nt: #if we previously reached destination node in less time: use that instead
                continue
            heapq.heappush(l, (nt + weight, dest)) #add into our list the time it took to get to destination node
    for _ in range(q):
        node = int(input())
        if node not in best:
            print('Impossible')
            continue
        print(best[node])
    print()
