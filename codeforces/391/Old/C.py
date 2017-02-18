import re
import sys
import time

MOD = 1000000007
tag = None

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def mul(nums):
	res = 1
	for i in nums:
		res *= i
		res %= MOD

	return res

def count(mids, gymid):
	global tag
	for mid in mids:
		tag[mid].append(str(gymid))

def solve():
	global tag

	n, m = read(int)
	tag = [[] for i in range(m)]
	for i in range(n):
		li = read(int)
		nmids = li[0]
		mids = [x-1 for x in li[1:]]
		count(mids, i)

	#print tag
	tag = [".".join(x) for x in tag]
	#print tag

	keys = set(tag)
	dic = dict([(key, 0) for key in keys])
	#print dic

	for key in tag:
		dic[key] += 1
	#print dic

	factors = dic.values()
	#print factors
	factors = [mul(range(1, n+1)) for n in factors]
	#print factors
	ans = mul(factors)

	print ans


if __name__ == "__main__":
	solve()
