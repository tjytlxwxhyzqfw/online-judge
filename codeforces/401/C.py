import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve_1(n, a, b):
	c = [[x, False] for x in b]

	ans = 0
	for x in a:
		idx = -1
		minval = 10
		for i, (y, used) in enumerate(c):
			if y < x or used == True:
				continue
			if y < minval:
				minval = y
				idx = i
		#print "x: %d, idx: %2d, y: %d"%(x, idx, c[idx][0])
		if idx != -1:
			c[idx][1] = True
		else:
			ans += 1
	return ans

def solve_2(n, a, b):
	c = [[x, False] for x in b]
	#print("2")
	ans = 0
	for x in a:
		idx = -1
		minval = 10
		for i, (y, used) in enumerate(c):
			if y <= x or used == True:
				continue
			if y < minval:
				minval = y
				idx = i
		#print "x: %d, idx: %2d, y: %d"%(x, idx, c[idx][0])
		if idx != -1:
			ans += 1
			c[idx][1] = True
	return ans


def solve():
	n = int(read())
	a = [int(x) for x in read()]
	b = [int(x) for x in read()]

	#print a
	#print b

	x = solve_1(n, a, b)
	y = solve_2(n, a, b)

	print x
	print y
if __name__ == "__main__":
	solve()
