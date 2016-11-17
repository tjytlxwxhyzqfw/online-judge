import time
from random import *
from common.common import *

def ginput():
	string = ""

	n = 100000
	string += "%d\n"%n

	a = [randint(0, 1) for i in range(n)]
	string += "%s\n"%reduce(lambda x, y: str(x)+" "+str(y), a)

	m = 100000
	string += "%d\n"%m

	for i in range(m):
		c, b, e = [randint(1, n) for j in range(3)]
		if b > e:
			b, e = e, b
		string += "%d %d %d\n"%(c%2, b, e)
	fwrite(string, "Inputs/3911")

def test(testid):
	print "Test #%d:"%testid
	ginput()
	aout = [int(x) for x in output("./a.out").split()]
	bout = [int(x) for x in output("./b.out").split()]
	verdict = (aout == bout)
	print "Ok" if verdict else "Wrong Answer"
	return verdict

if __name__ == "__main__":
	tid = 0
	while test(tid):
		tid += 1
