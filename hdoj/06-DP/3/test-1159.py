from random import *
from common.common import *



def ginput():
	la = randint(1, 5000)
	lb = randint(1, 5000)
	a, b = "", ""
	for i in range(la):
		a += chr(randint(97, 97+25))
	for i in range(lb):
		b += chr(randint(97, 97+25))

	string = ""
	string += "%s %s"%(a, b)
	#print string
	fwrite(string, "Inputs/1159")

def test(tid):
	print "Test #%d:"%tid
	ginput()
	aout = int(output("./a.out"))
	bout = int(output("./b.out"))
	return judge(aout, bout)

if __name__ == "__main__":
	tid = 0
	while (test(tid)):
		tid += 1
