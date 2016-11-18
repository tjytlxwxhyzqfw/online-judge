def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def cal(n, k):
	time = n + n/k*5
	if n >= k and  n % k  == 0:
		time -= 5
	return time

def solve():
	n, k = read(int)
	ps = []
	for i in xrange(n):
		ps.append(read())
	crt = read()

	#print ps
	#print crt

	ps = [len(x) for x in ps]
	crt = len(crt)

	nlt = sum([1 if x < crt else 0 for x in ps])
	nlte = sum([1 if x <= crt else 0 for x in ps])

	best = cal(nlt+1, k)
	worst = cal(nlte, k)

	print best, worst

if __name__ == "__main__":
	solve()
