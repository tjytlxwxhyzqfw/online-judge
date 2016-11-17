def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

if __name__ == "__main__":
	n = int(read())
	s = read()

	i, o = "_", "_"
	embraced = False
	for x in s:
		if x == "(" or x == ")":
			i += "_"
			o += "_"
			embraced = not embraced
			continue
		if embraced:
			i += x
			continue
		if not embraced:
			o += x
			continue
		assert False

	o = "".join(o).split("_")
	#print o
	o = [len(x) for x in o]
	print max(o)

	i = "".join(i).split("_")
	#print i
	i = filter(lambda x: x, i)
	print len(i)
