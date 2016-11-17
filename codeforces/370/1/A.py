def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def printl(l):
	for x in l:
		print x,
	print
	
if __name__ == "__main__":
	n = read(int)
	nums = read(int)
	nums2 = nums[1:]
	nums2.append(0)
	res = [(x+y) for (x, y) in zip(nums, nums2)]
	printl(res)
	
