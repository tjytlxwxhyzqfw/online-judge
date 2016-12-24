import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n = int(read())

	if n == 2:
		print 1
		print 2
		return
	if n == 3:
		print 1
		print 3
		return 
	if n == 4:
		print 2
		print 2,2
		return

	if n % 2 == 0:
		x = n / 2
		print x
		for i in range(x):
			print 2,
		return

	n -= 3
	x = n / 2
	print x+1
	for i in range(x):
		print 2,
	print 3
	

if __name__ == "__main__":
	solve()
