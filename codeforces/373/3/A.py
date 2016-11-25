import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

t = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1]

def syncwith(b, a):
	i = b 
	for x in a:
		if x != t[i]:
			return False
		i = (i+1)%30
	return True

def solve():
	n = int(read())
	a = read(int)

	first = a[0]
	last = a[n-1]

	if n == 1:
		if first == 15:
			print 'DOWN'
		elif first == 0:
			print 'UP'
		else:
			print '-1'
		return

	res = False

	if first == 15:
		res = syncwith(15, a)
	elif first == 0:
		res = syncwith(0, a)
	else:
		res = syncwith(first, a) or syncwith(30-first, a)

	if res == False:
		print '-1'
	else:
		if last == 0:
			print 'UP'
		elif last == 15:
			print 'DOWN'
		else:
			pre = a[n-2]
			if last > pre:
				print 'UP'
			else:
				print 'DOWN'

if __name__ == "__main__":
	solve()
