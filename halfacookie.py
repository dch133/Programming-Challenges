#!/bin/python3

import math
import sys 

lines = sys.stdin.readlines()
for line in lines:
        
    r,x,y=map(float,line.split())

# miss if you are outside the range of the circle
    if (x*x + y*y >= r*r):
            print ("miss")
# calculate the 2 pieces
    else:
        theta = math.acos(math.sqrt((x*x + y*y)) / r)
        sector = r*r*theta;
        triangle = (math.sqrt(x*x + y*y)) * (math.sqrt(r*r - x*x - y*y));
        little = sector - triangle;
        big = r*r*math.pi - little;
        print ( '{0:.4f}'.format(big), end=" " )
        print ( '{0:.4f}'.format(little), end=" " )
        print ()
