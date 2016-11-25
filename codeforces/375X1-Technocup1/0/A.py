import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	x, y = read(int)
	a  = [y]
	while y != x:
		#print a
		#time.sleep(1)
		if y % 2 == 0:
			y /= 2
			a.append(y)
		else:
			y -= 1
			if y != 0 and y % 10 == 0:
				y /= 10
				a.append(y)
			else:
				print "NO"
				return
	print 'YES'
	n = len(a)
	print n
	for i in range(n-1, -1, -1):
		print a[i],
	print
	
	

if __name__ == "__main__":
	solve()
