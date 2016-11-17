import sys

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def grtor(i, up):
	while True:
		yield i

		if i == 0:
			up = True
		elif i== 15:
			up = False

		if up:
			i += 1
		else:
			i -= 1

def sync(a, g):
	for x in a:
		if x != next(g):
			return False
	return True

if __name__ == "__main__":
	n = int(read())
	a = read(int)

	s = sync(a, grtor(a[0], True))
	t = sync(a, grtor(a[0], False))

	if not s and not t:
		print -1
		sys.exit(0)

	if a[n-1] == 0:
		print "UP"
		sys.exit(0)
	if a[n-1] == 15:
		print "DOWN"
		sys.exit(0)

	if n == 1:
		print -1
		sys.exit(0)

	print "UP" if a[n-1] > a[n-2] else "DOWN"
