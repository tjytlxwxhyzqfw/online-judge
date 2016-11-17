s = raw_input()
d = {'L':0, 'R':0, 'U':0, 'D':0}

for x in s:
	d[x] += 1

lr = abs(d['L'] - d['R'])
ud = abs(d['U'] - d['D'])

change_lr, left_lr = lr / 2, lr % 2
change_ud, left_ud = ud / 2, ud % 2

if (lr + ud) % 2 != 0:
	print "-1"
else:
	print "%d"%(change_lr + change_ud + (left_lr + left_ud) / 2)
	
