import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	left, rght = read().split()
	n = int(read())

	pairs = []
	for i in range(n):
		x, y = read().split()
		pairs.append([x, y])
	#print pairs

	print left, rght
	for i in range(n):
		vic = pairs[i][0]
		#print "---- vic: %s"%vic
		if vic == left:
			left = pairs[i][1]
		elif vic == rght:
			rght = pairs[i][1]
		print left, rght
		

if __name__ == "__main__":
	solve()
