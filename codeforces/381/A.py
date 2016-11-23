import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	minc = 10000000000000000
	n, a, b, c = read(int)
	for i in range(0, 5):
		for j in range(0, 5):
			for k in range(0, 5):
				cost = i * a + j * b + k * c
				num = n + i + 2 * j + 3 * k
				if num % 4 == 0:
					minc = min(minc, cost)
	print minc
				

if __name__ == "__main__":
	solve()
