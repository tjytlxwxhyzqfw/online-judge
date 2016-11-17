n = 0
a = None
left, rght = -1, -1

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def swap(i):
	global left, rght
	l = left - a[i][0] + a[i][1]
	r = rght - a[i][1] + a[i][0]
	b = abs(l-r)

	#print "swap: %2d: %3d(%3d, %3d)"%(i+1, b, l, r)
	return b

if __name__ == "__main__":
	n = int(read())
	a = []
	for i in xrange(n):
		a.append(read(int))
	#print a

	left, rght = 0, 0
	for i in xrange(n):
		left += a[i][0]
		rght += a[i][1]

	bt = abs(left-rght)
	idx = -1
	#print "raw: %2d: %2d, %2d"%(bt, left, rght)

	for i in xrange(n):
		b = swap(i)
		if b > bt:
			#print "idx: %d ---> %d"%(idx+1, i+1)
			bt = b
			idx = i

	print idx+1
	
