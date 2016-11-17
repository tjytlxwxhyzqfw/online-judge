import sys

t = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1]

n = 0
datas = None

def test(i):
	global n, datas, t
	for j in range(n):
		#print "datas[%2d]: %2d, t[%2d]: %2d"%(j, datas[j], i, t[i])
		if datas[j] != t[i]:
			return False
		i = (i+1) % 30
	return True

if __name__ == "__main__":
	n = int(raw_input())	
	datas = [int(x) for x in raw_input().split()]
	if n == 1:
		if datas[0] == 15:
			print "DOWN"
		elif datas[0] == 0:
			print "UP"
		else:
			print "-1"
		sys.exit(0)

	i = datas[0]
	j = 0
	for j in range(29, -1, -1):
		if t[j] == datas[0]:
			break
	#print "datas[0]: %2d, t[i]: %2d, t[j]: %2d"%(datas[0], t[i], t[j])

	if not test(i) and not test(j):
		print "-1"
		sys.exit(0)

	if datas[n-1] == 0:
		print "UP"
		sys.exit(0)

	if datas[n-1] == 15:
		print "DOWN"
		sys.exit(0)

	delta = datas[n-1] - datas[n-2];
	print "UP" if delta > 0 else "DOWN"
