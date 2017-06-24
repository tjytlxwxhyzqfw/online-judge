import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

table = [
	[0, 1, 2], #0
	[1, 0, 2],
	[1, 2, 0], #2
	[2, 1, 0],
	[2, 0, 1], #4
	[0, 2, 1]]

def solve():
	n = int(read())
	p = int(read())

	n %= 6
	state = table[n]
	print state[p]
	
if __name__ == "__main__":
	solve()
