import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	num, k = read().split()
	num = num[::-1]
	k = int(k)

	#print num
	#print k

	nzeros = 0
	for i, ch in enumerate(num):
		if int(ch) == 0: 
			nzeros += 1
		if nzeros == k:
			break

	if nzeros == k:
		print i-k+1
	else:
		print len(num)-1

if __name__ == "__main__":
	solve()
