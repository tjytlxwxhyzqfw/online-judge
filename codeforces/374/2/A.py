import re

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

if __name__ == "__main__":
	n = int(read())
	s = read()
	s = re.split(r'W+', s)
	s = filter(lambda x: x, s)
	#print s
	s = [len(x) for x in s]
	print len(s)
	print " ".join([str(x) for x in s])
