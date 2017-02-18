import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

keys = "Bblsrau"

def solve():
	s = list(read())
	n = len(s)

	dic = dict([(i, 0) for i in keys])
	#print dic

	for ch in s:
		if ch in keys:
			dic[ch] += 1

	#print dic

	dic["a"] /= 2
	dic["u"] /= 2

	print min(dic.values())

if __name__ == "__main__":
	solve()
