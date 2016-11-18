import re
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def realpoint(s):
	n = len(s)
	if n-3 >= 0 and s[n-3] == '.':
		return n-3
	return n

def parse(p):
	s = list(p)
	n = realpoint(p)

	i = n - 4
	while i > 0:
		if s[i] != '.':
			break
		s[i] = ''
		i -= 4

	s = "".join(s)
	#print "%s --> %s"%(p, s)

	return float(s)

def solve():
	s = read()
	s = re.split(r'[a-z]+', s)
	s = filter(lambda x: x, s)
	#print s

	total = 0.0
	for p in s:
		f = parse(p)
		total += f

	total = str(total)
	#print "total(str): %s"%total
	rtotal = total[::-1]
	if rtotal[1] == '.':
		if rtotal[0] == '0':
			total = total[:-2]
		else:
			total += '0'
	#print "total(crt): %s"%total
	n = len(total)

	i = realpoint(total)
	#print "i: %d"%i

	ans = ''
	for j in range(n-1, i, -1):
		ans += total[j]
	ans += '.'
	for j in range(i-1, -1, -1):
		#print "i-j: %d"%(i-j)
		ans += total[j]
		if (i-j) % 3 == 0:
			ans += '.'

	#print ans
	if ans[0] == '.':
		ans = ans[1:]
	ans = ans[::-1]
	if ans[0] == '.':
		ans = ans[1:]
	print ans

if __name__ == "__main__":
	solve()
