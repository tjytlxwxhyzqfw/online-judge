from random import *
from common.common import *

def ginput():
	ncs = 1
	n, nqs = 1600, 100
	
	motor_len = 10
	lmin, rmin = 1, 1
	lmax, rmax = 5, 5

	string = ""
	string += "%d\n"%ncs
	string += "%d %d\n"%(n, nqs)

	na = 0
	for i in range(nqs):
		cmd = chr(randint(65, 66))
		if cmd == "A":
			x, l, r = randint(1, motor_len), randint(lmin, lmax), randint(rmin, rmax)
			string += "%s %d %d %d\n"%(cmd, x, l, r)
			na += 1
		else:
			x = randint(1, na+1)
			string += "%s %d\n"%(cmd, x)

	#print string
	fwrite(string, "Inputs/3950");
	return string

def test(tid=0):
	print "Test #%d:"%tid
	ginput()
	aout = [str(x).strip() for x in output("./a.out").split()]
	bout = [str(x).strip() for x in output("./b.out").split()]
	return judge(aout, bout)

if __name__ == "__main__":
	tid = 0
	while test(tid):
		tid += 1

