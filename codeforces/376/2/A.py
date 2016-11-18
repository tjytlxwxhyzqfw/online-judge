def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve(a):
	a = ['a'] + a
	n = len(a)
	#print a
	a = [ord(x)-ord('a') for x in a]
	#print a

	d = 0
	for i in range(1, n):
		left = min(a[i-1], a[i])
		rght = max(a[i-1], a[i])
		#print "left, rght: %2d, %2d: %c, %c"%(left, rght, chr(left+97), chr(rght+97))
		d += min(rght-left, 26-rght+left)

	print d

if __name__ == "__main__":
	s = list(read())
	solve(s)
