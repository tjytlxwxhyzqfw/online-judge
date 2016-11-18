import re
import sys

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

if __name__ == "__main__":
	l1, r1, l2, r2, k = read(int)
	left = max(l1, l2)
	rght = min(r1, r2)
	inter = max(0, rght-left+1)

	if left <= k and k <= rght:
		inter -= 1

	print inter
