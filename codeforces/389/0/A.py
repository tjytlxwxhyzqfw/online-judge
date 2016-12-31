import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	nhps, nlns, pos = read(int)

	nph = nlns * 2
	#print "nph: %d"%nph

	hpid = (pos-1)/nph + 1
	#print "hpid: %d"%hpid

	hpstart = (hpid-1) * nph + 1
	#print "hpstart: %d"%hpstart

	lnid = (pos-hpstart)/2+1
	#print "lnid: %d"%lnid

	lr = 'L' if pos % 2 == 1 else 'R'

	print hpid, lnid, lr


if __name__ == "__main__":
	solve()
