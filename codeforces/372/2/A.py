def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

if __name__ == "__main__":
	n, m = read(int)
	a = read(int)

	cnt = 1
	for i in range(1, n):
		delta = a[i]-a[i-1]
		if delta > m:
			#print "\terase: i: %2d, ai: %2d"%(i, a[i])
			cnt = 1
		else:
			cnt += 1

	print cnt
