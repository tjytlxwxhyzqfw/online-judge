import re
import sys
import time

N = 100001

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n, x = read(int)

	a = read(int)

	cnt = [0 for i in range(N)]

	for i in a:
		cnt[i] += 1

	#print a

	ans = 0
	for i in a:
		j = i ^ x
		if j >= N:
			continue
		if cnt[j] == 0:
			continue
		#print "%3d xor %3d = %3d"%(i, j, x)
		if i == j:
			ans += cnt[j] - 1
		else:
			ans += cnt[j]

	print ans/2

if __name__ == "__main__":
	solve()
