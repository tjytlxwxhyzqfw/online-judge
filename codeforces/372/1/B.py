import sys

s, u = [], []
q = ord('?') - ord('A')
l = 0

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def loop(i, j):
	#print "loop: [%2d, %2d)"%(i, j)
	#print u
	if j - i == 26:
		return i, j
	if j == l:
		return
	if s[j] == q:
		return loop(i, j+1)
	k = u[s[j]]
	u[s[j]] = j
	if k != -1:
		for i in range(i, k):
			if s[i] != q:
				u[s[i]] = -1
		return loop(k+1, j+1)
	return loop(i, j+1)

def unused():
	for i in range(26):
		if u[i] == -1:
			yield i

if __name__ == "__main__":
	s = list(read())
	s = [ord(x)-ord('A') for x in s]
	l = len(s)

	u = [-1 for i in range(26)]
	i = 0
	for j in range(l+1):
		#print "[%2d, %2d)"%(i, j)
		if j - i == 26:
			break
		if j == l:
			break
		if s[j] == q:
			continue
		k = u[s[j]]
		u[s[j]] = j
		if k != -1:
			for i in range(i, k):
				if s[i] != q:
					u[s[i]] = -1
			i = k + 1

	if j-i != 26:
		print -1
		sys.exit(0)

	g = unused()
	for k in range(i, j):
		if s[k] == q:
			s[k] = next(g)

	s = ['A' if x == q else chr(x+ord('A')) for x in s]
	print "".join(s)
