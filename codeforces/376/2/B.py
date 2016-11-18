def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def odd(x):
	return x % 2 == 1
def even(x):
	return x % 2 == 0

def solve(a, n):
	s = [[False, False] for i in xrange(n)]
	s[n-1][0] = True if even(a[n-1]) else False
	if a[n-1] == 0:
		s[n-1][1] = False
	elif a[n-1] > 0:
		s[n-1][1] = True if odd(a[n-1]) else False
	else:
		assert False

	for i in xrange(n-2, -1, -1):
		s[i][0] = s[i+1][0] if even(a[i]) else s[i+1][1]
		if a[i] == 0:
			s[i][1] = False
		elif a[i] > 0:
			s[i][1] = s[i+1][0] if odd(a[i]) else s[i+1][1]
		else:
			assert False

	print 'YES' if s[0][0] else 'NO'

if __name__ == "__main__":
	n = int(read())
	a = read(int)
	solve(a, n)
