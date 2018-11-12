n = int(input())
total = 0
curr_mult_of_3 = 1
row_in_bin = bin(n)[2:] #convert to binary
for i in row_in_bin[::-1]: #loop through each bit (only consider 1s)
    if (i is not "0"):
        total *= 2
        total += curr_mult_of_3
    curr_mult_of_3 *= 3 #get the next multiple of 3

print (total)