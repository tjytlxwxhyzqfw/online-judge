import random

if __name__ == "__main__":
	n, m, L, s, t = 989, 9828, 218192, 0, 988
	for i in range(m):
		u = random.randint(0, n-1)
		v = random.randint(0, n-1)
		if u == v:
			u = (u+1) % n
		d = random.randint(0, 100)
		print u, v, d
