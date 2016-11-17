def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def dst(x, y):
	if x > y:
		x, y = y, x
	d = min(y-x, 26-y+x)
	print "%s - %s: %d"%(chr(x+97), chr(y+97), d)
	return d

if __name__ == "__main__":
	a = list(read())
	a = [ord(x) - ord("a") for x in a]
	print a

	total = 0
	curr = 0
	for x in a:
		total += dst(curr, x)
		curr = x

	print total
