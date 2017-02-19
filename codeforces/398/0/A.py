import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def place(x, b):
	i = x
	while i > 0 and b[i] == True:
		i -= 1
	return i

def solve():
	n = int(read())
	a = read(int)

	b = [False for i in range(n+1)]
	y = n

	for x in a:
		b[x] = True
		if x == y:
			y = place(y, b)
			for i in range(x, y, -1):
				print i,
		print "-"

if __name__ == "__main__":
	solve()
