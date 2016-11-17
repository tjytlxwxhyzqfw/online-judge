import random

if __name__ == "__main__":
	ncases, n, m = [1, 30000, 100000]
	print ncases
	print n
	for i in range(n):
		print random.randint(900000000, 1000000000)
	print m
	for i in range(m):
		left, right = [random.randint(1, n) for i in range(2)]
		if left > right:
			left, right = right, left
		print left, right
	
	
