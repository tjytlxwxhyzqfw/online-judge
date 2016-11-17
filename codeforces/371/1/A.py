if __name__ == "__main__":
	l1, r1, l2, r2, k = [int(x) for x in raw_input().split()]
	l, r = max(l1, l2), min(r1, r2)
	length = max(0, r-l+1)
	if l <= k and k <= r:
		length -= 1
	print length
