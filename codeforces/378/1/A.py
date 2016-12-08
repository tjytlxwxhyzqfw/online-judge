import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	string = read()
	string = re.split(r"[AEIOUY]+", string)
	#print string
	string = [len(x) for x in string]
	print max(string)+1

if __name__ == "__main__":
	solve()
