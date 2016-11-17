import os
import random

from wheels import *

def gmap(height=3, width=3):

	def __randsym(syms):
		return syms[random.randint(0, len(syms) - 1)]

	def __randy():
		return random.randint(0, height - 1)
	
	def __randx():
		return random.randint(0, width - 1)

	retmap = []
	for y in range(height):
		retmap.append([])
		for x in range(width):
			retmap[y].append( __randsym(['.', 'X', '.', '.']))

	start_y, start_x = __randy(), __randx()
	while True:
		end_y, end_x = __randy(), __randx()
		if end_y != start_y or end_x != start_x:
			break

	retmap[start_y][start_x] = 'S'
	retmap[end_y][end_x] = 'D'

	return retmap, random.randint(0, height * width)

def ginput(inmap, time):
	height = len(inmap)
	width = len(inmap[0])

	infile = open(DEFAULT_INPUT_FILE, "w")
	infile.write("%d %d %d\n"%( height, width, time)) 
	for line in inmap:
		for sym in line:
			infile.write("%c"%sym)
		infile.write("\n")
	infile.close()

if __name__ == "__main__":
	os.chdir("..")

	def ginput2():
		inmap, time = gmap()
		ginput(inmap, time)

	simple_test_2(
		"cat %s | ./b.out"%DEFAULT_INPUT_FILE,
		"cat %s | ./a.out"%DEFAULT_INPUT_FILE,
		ginput2);
