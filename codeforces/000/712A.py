n, a = raw_input(), raw_input()
n = int(n)
a = [int(x) for x in a.split()]
a.append(0)

b = [a[i] + a[i + 1] for i in range(0, n)]
for x in b:
	print x,
