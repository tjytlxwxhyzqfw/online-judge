import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	s, b, e = read(int)
	x, y = read(int)
	p, bus_d = read(int)

	if y <= x:
		#print "--> man faster than bus: ",
		print abs(e-b) * y
		return

	# man should always get on the bus

	man_d = (e-b) / abs(e-b)

	#print "man_d: %2d, bus_d: %2d"%(man_d, bus_d)

	if man_d == 1:
		if bus_d == 1:
			delta = b-p
			if delta < 0:
				delta = s-p+s+b
		else:
			delta = p+b
	else:
		if bus_d == 1:
			delta = s-p + s-b
		else:
			delta = p-b
			if delta < 0:
				delta = p+s+s-b

	walk = 1.0 * (x*delta) / (y-x)
	dist = abs(e-b)

	#print "delta: %f"%delta
	#print "time: %f"%time
	#print "walk: %f"%walk
	#print "dist: %f"%dist

	if walk >= dist:
		#print "--> forget the bus: ",
		print dist * y
		return
	else:
		#print "--> use bus: ",
		#ans = time+(dist-walk)*x
		#print "%g"%ans
		print x * (delta + dist)

if __name__ == "__main__":
	solve()
