def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def check(tar, li):
	#print "tar: %s, li: %s"%(tar, li)
	dlt = [abs(x-tar) for x in li]
	#print "dlt: %s\n"%dlt

	dlt = filter(lambda x: x, dlt)

	if not dlt:
		return True

	t0 = dlt[0]
	dlt = [x-t0 for x in dlt]
	return not any(dlt)

def solve():
	n = int(read())
	a = read(int)

	a = list(set(a))
	n = len(a)

	if n <= 2:
		print 'YES'
		return

	res = [check(x, a) for x in a[0:3]]
	print 'YES' if any(res) else 'NO'

if __name__ == '__main__':
	solve()
