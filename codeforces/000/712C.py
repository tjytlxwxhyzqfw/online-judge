# 712B
# Greedy is work for reverse order,
# But doesn't work in sequence order.

s = raw_input()
x = int(s.split()[0])
y = int(s.split()[1])

t = [y for i in range(3)]
n = 0
while True:
	t = sorted(t)
	if t[0] == x:
		break
	t[0] = t[1] + t[2] - 1
	n += 1
	if t[0] > x:
		t[0] = x
print n


