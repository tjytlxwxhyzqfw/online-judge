if __name__ == "__main__":
	n, b, d = [int(x) for x in raw_input().split()]
	os = [int (x) for x in raw_input().split()]

	os = filter(lambda x: x <= b, os)
	#print "left: %s"%os

	n = len(os);
	total, ans = 0, 0
	for i in range(n):
		total += os[i]
		if (total > d):
			total = 0
			ans += 1

	print ans
		
