import sys

point = ord('.') - ord('0')
n, m = 0, 0

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def plusone(s, i):
	carry = 1
	for i in xrange(i, -1, -1):
		#print "plusone: %d, carry: %d"%(i, carry)
		if s[i] == point:
			continue
		s[i] += carry
		carry = s[i] / 10
		s[i] %= 10
		if carry == 0:
			break
	assert carry == 0

def nextc(s, start):
	for i in xrange(start, -1, -1):
		if s[i] != point and s[i] >= 5:
			return i
	return -1

def roundat(s, p):
	#print "roundat: %d"%p
	s[p] = 0
	plusone(s, p-1)
	return nextc(s, p-1)

def printnum(s, b, e):
	#print "[%2d, %2d]"%(b, e)
	s = [chr(s[j]+ord('0')) for j in xrange(b, e)]
	if s[0] == '0':
		s = s[1:]
	print "".join(s)
		

if __name__ == "__main__":
	n, m = read(int)
	s = read()
	s = [ord(x)-ord('0') for x in s]
	s = [0] + s
	n = len(s)
	#print s

	# point position
	pidx = 0
	for i in xrange(n):
		if s[i] == point:
			break
	if s[i] == point:
		pidx = i
	else:
		printnum(s, 0, n)
		sys.exit(0)
	#print "pidx: %d"%pidx
		

	# first round position
	start = 0
	for i in xrange(pidx+1, n):
		if s[i] >= 5:
			break
	if s[i] >= 5:
		start = i
	else:
		printnum(s, 0, n)
		sys.exit(0)
	#print "start: %d"%start

	# s[start+1:n] = 0
	for i in xrange(n-1, start, -1):
		s[i] = 0

	# round
	for i in xrange(m):
		#print "round: i = %d\n"%i
		start = roundat(s, start)
		if start < pidx:
			break

	# find end position
	for i in xrange(n-1, -1, -1):
		if s[i] != 0:
			break

	# output
	if s[i] != 0:
		if s[i] == point:
			printnum(s, 0, i)
		else:
			printnum(s, 0, i+1)
	else:
		print 0
