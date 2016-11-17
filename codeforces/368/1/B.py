infinity = 1000000001

n, m, k = 0, 0, 0
flower = []
egs = []

def valiad(left, right):
	global flower
	if flower[left] and not flower[right]:
		return True
	if flower[right] and not flower[left]:
		return True
	return False

if __name__ == "__main__":
	n, m, k = [int(x) for x in raw_input().split()]	
	flower = [False for i in range(n)]
	for i in range(m):
		left, right, cost = [int(x) for x in raw_input().split()]
		egs.append((left-1, right-1, cost))

	if k > 0:
		flowers = [int(x) for x in raw_input().split()]
		for flw in flowers:
			flower[flw-1] = True

	ans = infinity
	for left, right, cost in egs:
		if not valiad(left, right):
			continue
		if ans > cost:
			ans = cost

	print ans if ans != infinity else -1
		
