if __name__ == "__main__":
	[n, c] = [int(x) for x in raw_input().split()]
	nums = [int(x) for x in raw_input().split()]
	r, x = 0, c
	#[0, i) has been read
	for i in range(len(nums)):
		if nums[i] > x:
			r = 1
		else:
			r += 1
		x = nums[i] + c
	print r
