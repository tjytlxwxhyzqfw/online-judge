from random import *
from common.common import *

def ginput():
	ncases = 1
	n, m = 100000, 100000
	pts = [randint(0, 1) for i in range(n)]
	tpls = []
	for i in range(m):
		c, x, y = randint(0, 4), randint(0, n-1), randint(0, n-1)
		if x > y:
			x, y = y, x
		tpls.append((c, x, y))

	string = ""
	string += "%d\n%d %d\n"%(ncases, n, m)
	string += reduce(lambda x, y: str(x)+" "+str(y), pts)
	string += "\n"
	for tp in tpls:
		string += "%d %d %d\n"%(tp[0], tp[1], tp[2])
	#print string
	fwrite(string, "Inputs/3397")

def test(tid):
	print "Test #%d:"%tid,
	ginput()
	aout = [int(x) for x in output("./a.out").split()]
	bout = [int(x) for x in output("./b.out").split()]
	#print aout
	#print bout
	correct = aout == bout
	print "Ok" if correct else "Wrong Answer"
	return correct

if __name__ == "__main__":
	tid = 0
	while test(tid):
		tid += 1
	
