import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def valiad(brd, x, y):
	if not (0 <= x and x < 4):
		return False
	if not (0 <= y and y < 4):
		return False
	if not brd[x][y] == "x":
		return False
	return True

def check(brd, x, y):
	if not valiad(brd, x, y):
		return False

	if valiad(brd, x, y-1) and valiad(brd, x, y+1):
		#print "Win -"
		return True
	if valiad(brd, x-1, y) and valiad(brd, x+1, y):
		#print "Win |"
		return True
	if valiad(brd, x-1, y-1) and valiad(brd, x+1, y+1):
		#print "Win \\"
		return True
	if valiad(brd, x+1, y-1) and valiad(brd, x-1, y+1):
		#print "Win /"
		return True

	return False

def checkboard(brd):
	for i in range(4):
		for j in range(4):
			if check(brd, i, j):
				return True
	return False

def solve():
	brd = [] 
	for i in range(4):
		brd.append(list(read()))
	#print brd

	for i in range(4):
		for j in range(4):
			if brd[i][j] != ".":
				continue
			#print "empty: [%3d, %3d]"%(i+1, j+1)
			brd[i][j] = "x"
			if checkboard(brd):
				print "YES"
				return
			brd[i][j] = "."
	print "NO"

if __name__ == "__main__":
	solve()
