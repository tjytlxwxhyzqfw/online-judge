import sys
import time
import re

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n = int(read())
	s = read()

	res = re.sub(r'ogo(go)*', '***', s)

	print res

if __name__ == "__main__":
	solve()
