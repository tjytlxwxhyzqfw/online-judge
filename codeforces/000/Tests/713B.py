def check(a, b):
	if a[0] <= b[0] and a[1] <= b[1] and a[2] >= b[2] and a[3] >= b[3]:
		return 1
	return 0

if __name__ == "__main__":
	
	first = 774, 16333, 774, 16333
	second = 774, 16334, 774, 16334

	while True:
		query = [int(x) for x in raw_input().split()]
		print check(query, first) + check(query, second)
