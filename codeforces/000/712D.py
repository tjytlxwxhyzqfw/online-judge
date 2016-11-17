#
# 712D
# TLE
#

M = 1000000007

a, b, k, t = None, None, None, None
dp = None
offset = None
idx_max = None
delta_min, delta_max = None, None

def idx(x):
	return x + offset

def dpfill(i, j):
	if j == idx_max - k:
		dp[i][j] = reduce(lambda x, y: x + y, dp[i+1][j-k : j+k+1]) % M
	else:
		dp[i][j] = (dp[i+1][j-k] + dp[i][j+1] - dp[i+1][j+k+1]) % M
	#print "fill: dp[%2d][%2d]: %d"%(i, j - offset, dp[i][j])

if __name__ == "__main__":
	a, b, k, t = map(lambda x: int(x), raw_input().split())
	#a, b, k, t = 1, 2, 2, 1
	#a, b, k, t = 1, 1, 1, 2
	#a, b, k, t = 2, 7, 3, 1

	t *= 2
	delta = a - b
	delta_max = delta + t * k
	delta_min = delta - t * k
	offset = -1 * delta_min
	idx_max = delta_max + offset

	#print "offset: ", offset

	dp = [[0 for j in range(idx_max + 1)] for i in range(t + 1)]

	for j in range(0, idx_max + 1):
		dp[t][j] = k - offset + j
		if dp[t][j] < 0:
			dp[t][j] = 0
		if dp[t][j] > 2 * k + 1:
			dp[t][j] = 2 * k + 1
		dp[t][j] %= M
		#print "dp[%2d][%2d]: %d"%(t, j - offset, dp[t][j])

	for i in range(t - 1, 0, -1):
		for j in range(delta_max - k, delta_min + k - 1, -1):
			dpfill(i, idx(j))

	print dp[1][delta + offset]
