from random import *
from common.common import *

def ginput():
	n = randint(2, 19)
	k = randint(1, n/2)

	w = []
	for i in range(n):
		w.append(randint(1, 10))

	string = "%d %d\n"%(n, k)
	string += reduce(lambda x, y: "%s %s"%(str(x), str(y)), w)
	string += "\n"

	print string
	fwrite(string, "Inputs/1421")

def test():
	ginput()
	return judge(output("./a.out"), output("java P1421"))

if __name__ == "__main__":
	while test():
		pass
	

		
