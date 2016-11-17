from common.common import *

def ginput(n, size):
	nums = range(n, n+size)
	print "[%d, %d)"%(n, n+size)

	string = "%d\n"%size
	string += " ".join([str(x) for x in nums])
	string += "\n"

	fwrite(string, "Inputs/5878")

if __name__ == "__main__":
	step = 10000
	for i in xrange(0, 10000000000, step):
		ginput(i, 10000)
		a = [int(x) for x in output("cat Inputs/5878 | ./a.out").split()]
		b = [int(x) for x in output("cat Inputs/5878 | ./b.out").split()]
		if not judge(a, b, silence=True):
			break
