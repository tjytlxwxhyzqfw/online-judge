from random import *
from testtools import *


def ginput():
	string = ""
	n = 100000
	string += "%d\n"%n 

	for i in range(n):
		left, right = [randint(1, n) for j in range(2)]
		if left > right:
			left, right = right, left
		string += "%d %d\n"%(left, right)
	#print string
	fwrite(string, "Inputs/1556")
	

def test():
	ginput()
	aout = [int(x) for x in output("./a.out").split()]
	bout = [int(x) for x in output("./b.out").split()]
	print aout
	print bout

	if sum([abs(x-y) for x, y in zip(aout, bout)]) != 0:
		print "Wong Answer"
		return False
	else:
		print "Ok"
		return True

if __name__ == "__main__":

	while test():
		pass
