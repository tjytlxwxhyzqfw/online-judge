import sys

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def nxtd(frm, inc):
	nxt = frm
	while True:
		if nxt == 0:
			nxt = 1
			inc = True
		elif nxt == 15:
			nxt = 14
			inc = False
		else:
			nxt += 1 if inc else -1 
		yield nxt

def sync(src, a):
	for x in a[1:]:
		if x != next(src):
			return False
	return True
		
if __name__ == "__main__":
	n = int(read())
	a = read(int)

	inc = nxtd(a[0], True)
	dec = nxtd(a[0], False)

	if n == 1:
		if n == 0:
			print 'UP'
		elif n == 15:
			print 'DOWN'
		else:
			print -1
		sys.exit(0)

	if sync(inc, a) or sync(dec, a):
		if a[n-1] == 0:
			print 'UP'
		elif a[n-1] == 15:
			print 'DOWN'
		else:
			print 'DOWN' if a[n-1] < a[n-2] else 'UP'
	else:
		print -1

