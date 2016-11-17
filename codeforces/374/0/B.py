n, k = 0, 0
def compute(x):
	global n, k
	#print "compute: k: %d"%k
	if x <= k:
		return x
	return x + (x-1) / k * 5

if __name__ == "__main__":
	[n, k] = [int(x) for x in raw_input().split()]
	#print "n: %d, k: %d"%(n, k)

	strings = []
	for i in range(n):
		string = raw_input().strip()
		strings.append(string)

	string = raw_input().strip()
	#print "string: %s"%string

	length = len(string)
	less = sum(map(lambda s: 1 if len(s) < length else 0, strings))
	equal = sum(map(lambda s: 1 if len(s) == length else 0, strings))
	#print "less: %d, equal: %d\n"%(less, equal)

	best = compute(less+1)
	worest = compute(less+equal)
	print best, worest
