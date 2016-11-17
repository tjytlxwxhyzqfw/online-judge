import sys

a = []
n = 0

def check(target, delta):
	b = [0 if x == target else abs(abs(x-target)-delta) for x in a]
	return sum(b) == 0

if __name__ == "__main__":
	n = int(raw_input())
	a = list(set([int(x) for x in raw_input().split()]))

	if len(a) <= 2:
		print "YES"
		sys.exit(0)

	l, m, r = a[0:3]
	if check(l, abs(m-l)) or check(m, abs(r-m)) or check(r, abs(r-l)):
		print "YES"
		sys.exit(0)

	print "NO"
