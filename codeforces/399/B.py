import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def size(x):
	if x == 1 or x == 0:
		return 1
	return 2 * size(x/2) + 1

g_left, g_rght = 0, 0

def do_solve(curr, left, rght):

	#print "do_solve: %5d [%5d, %5d]"%(curr, left, rght)

	nxt = curr/2

	length = size(curr)
	half = (length-1)/2

	a, b = 1, half
	m = half+1
	c, d = half+2, length

	#print "\t[%5d, %5d] - %5d - [%5d, %5d]"%(a, b, m, c, d)

	assert b-a+1 == half
	assert d-c+1 == half


	if a <= left and rght <= b:
		return do_solve(nxt, left, rght)
	elif m <= left and rght <= m: 
		return curr%2, 1, 1
	elif c <= left and rght <= d:
		return do_solve(nxt, left-c+1, rght-c+1)
	else:
		return curr, left, rght

def gen(curr):
	if curr == 0:
		return [0]
	if curr == 1:
		return [1]
	half = gen(curr/2)
	return half+[curr%2]+half

def solve():
	global g_left, g_rght
	n, g_left, g_rght = read(int)
	curr, left, rght = do_solve(n, g_left, g_rght)
	#print "curr: %5d, left: %5d, rght: %5d"%(curr, left, rght)
	a = gen(curr)
	#print "a: %s"%a
	b = a[left-1: rght]
	print sum(b)

if __name__ == "__main__":
	solve()
