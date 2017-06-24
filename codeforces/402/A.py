import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n = int(read())
	a = read(int)
	b = read(int)

	dica = [0, 0, 0, 0, 0, 0]
	dicb = [0, 0, 0, 0, 0, 0]

	for i in range(n):
		dica[a[i]] += 1
		dicb[b[i]] += 1

	#print dica
	#print dicb

	delta = [0, 0, 0, 0, 0, 0]
	for i in range(6):
		sumi = dica[i] + dicb[i]
		if sumi % 2 == 1:
			print "-1"
			return
		delta[i] = dica[i] - sumi/2

	#print delta
	assert sum(delta) == 0

	ans = 0
	for x in delta:
		if x > 0:
			ans += x
	print ans

if __name__ == "__main__":
	solve()
