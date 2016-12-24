import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n = int(read())
	s = list(read())

	#print s

	append = True if n % 2 != 0 else False

	txt = ""
	for x in s:
		if append:
			txt = txt + x
		else:
			txt = x + txt
		#print "\ttxt: %s"%txt
		append = not append

	print txt

if __name__ == "__main__":
	solve()
