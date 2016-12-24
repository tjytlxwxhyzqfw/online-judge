import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def computey(x1, y1, x2, y2, x3, y3):
	top1 = (y2-y1)*(x3-x2)*y1
	top2 = (y3-y2)*(x2-x1)*y3
	top3 = (x3-x1)*(y3-y2)*(y2-y1)
	bot1 = (x3-x2)*(y2-y1)
	bot2 = (x2-x1)*(y3-y2)

	top = (top1 - top2 + top3)
	bot = (bot1 - bot2)

	#print "computey: bot: %3d, bot: %3d"%(top, bot)

	if top % bot == 0:
		return top / bot
	return None

def computex(x1, y1, x2, y2, x3, y3, y):
	top = (x3-x2)*(y-y1)
	bot = (y3-y2)
	left = x1

	if bot == 0:
		return x3+x1-x2

	if top % bot == 0:
		return top / bot + left

	return None

def compute(x1, y1, x2, y2, x3, y3):
	y = computey(x1, y1, x2, y2, x3, y3)
	x = computex(x1, y1, x2, y2, x3, y3, y)

	return x, y

def solve():
	x1, y1 = read(int)
	x2, y2 = read(int)
	x3, y3 = read(int)

	a1, b1 = compute(x1, y1, x2, y2, x3, y3)
	a2, b2 = compute(x2, y2, x3, y3, x1, y1)
	a3, b3 = compute(x3, y3, x1, y1, x2, y2)

	ans = []
	if None not in [a1, b1]:
		ans.append((a1, b1))

	if None not in [a2, b2]:
		ans.append((a2, b2))

	if None not in [a3, b3]:
		ans.append((a3, b3))

	print len(ans)
	for x, y in ans:
		print x, y
	
if __name__ == "__main__":
	solve()
