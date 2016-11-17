def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def printl(l):
	for x in l:
		print x,
	print
	
if __name__ == "__main__":
	a, b = read(int)
	t = [b for i in range(3)]
	n = 0
	while t[2] != a:
		#print t
		t[0] = min(t[1]+t[2]-1, a)
		n += 1
		t = sorted(t)
	print n+2 if n != 0 else 0
	
	
