if __name__ == "__main__":
	[l1, r1, l2, r2, k] = [int(x) for x in raw_input().split()]
	if l1 > l2:
		l1, l2 = l2, l1
		r1, r2 = r2, r1
	left = l2
	right = min(r2, r1)
	if left > right:
		print 0
	elif k >= left and k <= right:
		print right - left
	else:
		print right - left + 1
