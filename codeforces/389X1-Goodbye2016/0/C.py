import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

leftval, rghtval = None, None

impsb = "Impossible"

def update(div, change):
	global leftval, rghtval

	if div == 1:
		if rghtval != None and rghtval < 1900:
			print impsb
			return False
		if leftval is None or leftval < 1900:
			leftval = 1900

	if div == 2:
		if leftval != None and leftval >= 1900:
			print impsb
			return False
		if rghtval is None or rghtval >= 1900:
			rghtval = 1899

	if leftval != None:
		leftval += change
	if rghtval != None:
		rghtval += change

	return True

def solve():
	n = int(read())
	for i in xrange(n):
		change, div = read(int)
		res = update(div, change)
		if not res:
			return
		#print "[%8s, %8s]"%(leftval, rghtval)

	print "Infinity" if rghtval is None else rghtval

if __name__ == "__main__":
	solve()
