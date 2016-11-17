a, n = None, 0
s = None

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def even(x):
	return x % 2 == 0

def odd(x):
	return x % 2 == 1

def prints(i, j):
	global s
	print "s[%2d][%2d]: %d"%(i, j, s[i][j])

if __name__ == "__main__":
	n = int(read())
	a = read(int)

	s = [[False, False] for i in xrange(n)]

	s[n-1][0] = True if even(a[n-1]) else False
	s[n-1][1] = True if odd(a[n-1]) else False
	#prints(n-1, 0)
	#prints(n-1, 1)

	for i in xrange(n-2, -1, -1):
		s[i][0] = s[i+1][0] if even(a[i]) else s[i+1][1]
		#prints(i, 0)
		s[i][1] = s[i+1][0] if odd(a[i]) else s[i+1][1]
		if a[i]-1 < 0:
			s[i][1] = False
		#prints(i, 1)

	print "YES" if s[0][0] else "NO"
