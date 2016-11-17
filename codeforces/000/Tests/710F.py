import random
import sys

def randstr(length):
	string = ""
	for i in range(length):
		string += 'a'
	return string

if __name__ == "__main__":
	chars = 300000
	ncases = random.randint(1, 200)
	print ncases
	for ncas in range(ncases):
		op = random.randint(1, 3)
		s = randstr(random.randint(1, 10000) if op == 3 else random.randint(1, 100))
		print "%d %s"%(op, s)
		chars -= len(s)
		if chars <= 0:
			sys.exit(1)
