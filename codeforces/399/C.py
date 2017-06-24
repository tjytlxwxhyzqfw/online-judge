import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

table = None
bgn, end, idx = None, None, None
loop = None

def doop(a, n, magic):
	#print "sorted: %s"%["%3d"%x for x in a]
	for i in range(n):
		if i % 2 == 0:
			a[i] ^= magic
	#print "xor   : %s"%["%3d"%x for x in a]

	a = sorted(a)
	for i in range(n):
		if loop[i]:
			idx[i] += 1
			if idx[i] >= end[i]:
				idx[i] = bgn[i]
			continue
		if a[i] in table[i]:
			loop[i] = True
			bgn[i] = table[i].index(a[i])
			end[i] = len(table[i])
			#print "new loop %5d: [%3d,%3d)"%((i+1), bgn[i], end[i])
		else:
			table[i].append(a[i])
	return a

def solve():
	n, k, x = read(int)
	a = read(int)

	global table, loop, bgn, end, idx
	table = [[] for i in range(n)]
	loop = [False for i in range(n)]
	bgn = [0 for i in range(n)]
	end = [0 for i in range(n)]
	idx = [0 for i in range(n)]

	a = sorted(a)
	for i in range(k):
		a = doop(a, n, x)
		#print "idx: %s"%idx
		if k >= 1024 and all(loop):
			break

	a = sorted(a)
	if k < 1024:
		print a[-1], a[0]
		return

	#print "i: %d"%i
	remain = k - i - 1
	for i in range(n):
		length = end[i] - bgn[i]
		nsteps = remain % length
		idx[i] += nsteps
		if idx[i] >= end[i]:
			idx[i] -= length

	for i in range(n):
		#print table[i]
		a[i] = table[i][idx[i]]
	#print a
	print a[-1], a[0]

	

if __name__ == "__main__":
	solve()
