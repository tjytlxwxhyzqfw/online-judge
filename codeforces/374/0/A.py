n, string = 0, ""

def solve_old():
	global n, string
	result, count = [], 0
	length = len(string)
	for i in range(length):
		if string[i] == 'B':
			count += 1
		if string[i] == 'W':
			result.append(count)
			count = 0
	result.append(count)

	result = filter(lambda x: x > 0, result)

	print len(result)
	for x in result:
		print x,

def solve():
	global n, string
	string = string.split("W")
	string = filter(lambda x: x, string)
	result = [len(s) for s in string]
	print len(result)
	for r in result:
		print r,

if __name__ == "__main__":
	n = int(raw_input().strip())
	string = raw_input().strip()
	solve()
