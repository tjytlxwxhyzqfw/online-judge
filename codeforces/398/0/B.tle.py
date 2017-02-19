import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def pos(x, a, n):
	x += .5

	p = n
	i, j = 0, n-1
	while i <= j:
		k = (i+j)/2
		if x < a[k]:
			p = k
			j = k - 1
		elif x > a[k]:
			i = k + 1
		else:
			assert False

	return p

def solve():
	ts, tf, t = read(int)
	n = int(read())
	if n <= 0:
		print ts
		return
	a = read(int)

	ends = [ts]
	for i, x in enumerate(a):
		start = max(ends[-1], x)
		end = start+t
		ends.append(end)
	ends = ends[1:]
	#print ends

	a1 = [x for x in a]
	a2 = [x-1 for x in a]
	a3 = [x+1 for x in a]
	#print a1
	#print a2
	#print a3


	top = 1000000000001
	b = list(set(a1+a2+a3+ends))
	b = filter(lambda x: 0 <= x and x < top, b)
	b = sorted(b)
	#print b

	minclk = b[0]
	if minclk > ts:
		#print "just be the first"
		print ts
		return
	minwait = ts - minclk
	if minclk == 0:
		p = pos(minclk, a, n)
		minwait = ts + t*p
	#print "minwait: %d"%minwait

	#print b
	for clk in b:
		p = pos(clk, a, n)
		wait = ts - clk
		if p > 0:
			wait = ends[p-1] - clk
		#print "clk: %3d, p: %3d, wait: %3d"%(clk, p, wait)
		if clk + wait + t > tf:
			continue
		if wait < minwait:
			minwait = wait
			minclk = clk
	print minclk
		

if __name__ == "__main__":
	solve()
