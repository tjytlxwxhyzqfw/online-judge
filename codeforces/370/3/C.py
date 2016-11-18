def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	tar, ini = read(int)

	a = [ini for i in range(3)]
	n = 0
	while a[0] < tar:
		a[0] = min(a[1]+a[2]-1, tar)
		n += 1
		#print "n: %2d: %s"%(n, a)
		a = sorted(a)
	print n

if __name__ == "__main__":
	solve()
