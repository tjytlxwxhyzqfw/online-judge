a = []

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def compute(b):
	global a
	b = [x-y for x,y in zip(a,b)]
	#print "b: %s"%b 

	if any([x < 0 for x in b]):
		return 2 * pow(10, 18)

	d = max(b)
	#print "d: %d"%d
	return sum([d-x for x in b])

if __name__ == "__main__":
	a = read(int)
	ans = []
	ans.append(compute([0, 0, 0]))
	ans.append(compute([1, 0, 0]))
	ans.append(compute([1, 1, 0]))
	ans.append(compute([0, 1, 0]))
	ans.append(compute([0, 1, 1]))
	ans.append(compute([0, 0, 1]))
	ans.append(compute([1, 0, 1]))

	print min(ans)
