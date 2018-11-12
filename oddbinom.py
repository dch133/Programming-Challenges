
n = int(input())
total = 0
mybin = bin
#loop through all rows until n and add 2^(#1s in binary form) (theorem)
for i in range (n):
    numOdd = 2**(mybin(i).count("1"))
    total+=numOdd
    
print (total)
