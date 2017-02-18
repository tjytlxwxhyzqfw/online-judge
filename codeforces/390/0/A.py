import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def firstnz(a, b):
	length = len(a)

	i = b
	while i < length:
		if a[i] != 0:
			break
		i += 1
	return i

def solve():
	n = int(read())
	a = read(int)

	res = []

	left = firstnz(a, 0)
	if  left == n:
		print "NO"
		return

	while True:
		rght = firstnz(a, left+1)
		#print "[%3d, %3d]"%(left, rght)
		res.append([left, rght-1])
		if rght == n:
			break
		left = rght

	res[0][0] = 0

	print "YES"
	print len(res)
	for left, rght in res:
		print left+1, rght+1
		
if __name__ == "__main__":
	solve()
