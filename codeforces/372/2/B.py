import sys

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

query = ord('?')
al = [ord(x) for x in "ABCDEFGHIJKLMNOPQRSTUVWXYZ"]

if __name__ == "__main__":
	s = list(read())
	s = [ord(x) for x in s]
	n = len(s)

	d = {}
	for x in s:
		d[x] = -1

	i, j = 0, 0
	while (j < n):
		if j-i == 26:
			break
		x = s[j]
		raw_input()
		if x == query:
			continue
		raw_input()
		if d[x] != -1:
			print "\trepeat: %s: (%2d, %2d)"%(x, d[x], j)
			for k in range(i, d[x]+1):
				d[s[k]] = -1
			i = d[x]+1
		raw_input()
		d[x] = j
		raw_input()
		j += 1
		raw_input()

	if j-i != 26:
		print -1
		sys.exit(0)

	used = []
	cands = al
	for k in range(i, j):
		if s[k] != query:
			used.append(s[k])
			cands.remove(s[k])

	print "used: %s"%[chr(x) for x in used]
	print "cands: %s"%[chr(x) for x in cands]

	for k in range(i, j):
		if s[k] == query:
			s[k] = cands.pop()

	for k in range(n):
		if s[k] == query:
			s[k] = ord('A')

	print "".join([chr(x) for x in s])
