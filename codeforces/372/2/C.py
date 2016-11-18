def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n = int(read())
	print 2
	if n == 1:
		return
	for i in range(2, n+1):
		print (i+1)*(i+1)*i-i+1

if __name__ == "__main__":
	solve()
