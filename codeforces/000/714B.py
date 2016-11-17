import sys

def test(s, delta, avg):
	#print "delta: %d, avg: %d"%(delta, avg)
	ok = True 
	for x in s:
		if x < avg:
			x += delta
		elif x > avg:
			x -= delta
		if x != avg:
			ok = False
			break
	return ok
	
if __name__ == "__main__":
	n = int(raw_input())
	s = [int(x) for x in raw_input().split()]
	s = sorted(s)
	delta = s[n-1] - s[0]
	if delta == 0:
		print "YES"
		sys.exit(0)
	if delta % 2 == 0:
		ok = test(s, delta/2, s[0]+delta/2)
		if ok:
			print "YES"
			sys.exit(0)
	ok = test(s, delta, s[0])
	if ok:
		print "YES"
	else:
		print "NO"
