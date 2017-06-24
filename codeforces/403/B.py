import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def f(left, rght, v_left, v_rght):
	t = 1.0*(rght-left)/(v_left+v_rght)
	x = 0.0 + left + v_left*t
	print "t: %f"%t
	print "x: %f"%x
	return t, x
	
def solve():
	
	

if __name__ == "__main__":
	solve()
