from random import *
from common.common import *

def randstr(n, a, size):
	s = [a[randint(0, size-1)] for i in range(n)]
	res = ""
	for x in s:
		res += x
	return res

def ginput():
	alpha="abc"
	alphalen = len(alpha)

	ilen = 5000
	qlen = 100
	iwlen = 10
	qwlen = 10

	string = ""

	for i in xrange(ilen):
		string += "%s\n"%randstr(randint(1, iwlen), alpha, alphalen)	

	string += "\n"

	for i in xrange(qlen):
		string += "%s\n"%randstr(randint(1, qwlen), alpha, alphalen)	

	#print string
	fwrite(string, "Inputs/1251")

def test():
	ginput()
	aout = output("./a.out").split()
	bout = output("./b.out").split()
	return judge(aout, bout)
	

if __name__ == "__main__":
	while test():
		pass
