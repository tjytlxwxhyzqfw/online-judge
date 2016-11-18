def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

if __name__ == "__main__":
	s = list(read())
	d = {'L':0, 'R':0, 'U':0, 'D':0}
	for x in s:
		d[x] += 1

	lr = abs(d['L']-d['R'])
	ud = abs(d['U']-d['D'])

	nchanges = min(lr, ud)
	nleftovers = abs(lr-ud)

	if nleftovers % 2 == 0:
		print nchanges + nleftovers/2
	else:
		print -1
