def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def printl(l):
	for x in l:
		print x,
	print
	
if __name__ == "__main__":
	string = list(read())
	d = {'L':0, 'R':0, 'U':0, 'D':0}
	for x in string:
		d[x] += 1
	delta = abs(d['L']-d['R']) + abs(d['U']-d['D'])
	print delta/2 if delta%2 == 0 else -1

