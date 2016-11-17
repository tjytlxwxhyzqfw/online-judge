import random

from wheels import *

def ginput(scale=300):

	def randfloat():
		return 1.0 * random.randint(0, 100000) / 10000

	input_file = open("Inputs/1077", "w")
	input_file.write("%d\n\n%d\n"%(1, scale))
	for i in range(scale):
		x, y = randfloat(), randfloat()
		input_file.write("%7.5f %7.5f\n"%(x, y))
	input_file.close()

def ginput2():

	def randint2(already):
		while True:
			i = random.randint(0, 9)
			if i not in already:
				return i

	specious = [(9.89990, 1.24710), (6.21930, 0.98470),
		(6.45950, 9.37110), (4.39200, 0.77010),
		(5.42150, 2.25890), (6.76070, 0.61780),
		(9.86490, 8.46780), (7.37000, 8.99920),
		(8.29580, 1.27630), (8.07610, 6.65800)]

	i1 = randint2([])
	i2 = randint2([i1])
	i3 = randint2([i1, i2])
	input_file = open("Inputs/1077", "w")
	input_file.write("%d\n\n%d\n"%(1, 3))
	for i in [i1, i2, i3]:
		input_file.write("%7.5f %7.5f\n"%specious[i])
	input_file.close()

if __name__ == "__main__":
	os.chdir(HDOJ_DIR)

	p1, p2 = "./a.out", "java Main"
	while True:
		ginput()
		pinput(1077)
		outs = get_output([p1, p2])
		print "outs0: %s, outs1: %s"%(outs[0], outs[1])
		if outs[0] != outs[1]:
			print "GOT YOU !!!"
			break
