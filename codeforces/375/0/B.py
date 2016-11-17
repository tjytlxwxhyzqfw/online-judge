if __name__ == "__main__":
	length = int(raw_input())
	string = list(raw_input().strip())

	inners, outers = ["_"], ["_"]
	inb = False
	for ch in string:
		if ch == "(":
			inb = True
			inners.append("_");
			outers.append("_");
			continue
		if ch == ")":
			inb = False
			inners.append("_")
			outers.append("_")
			continue
		if inb:
			inners.append(ch)
			continue
		if not inb:
			outers.append(ch)
			continue

	inner = reduce(lambda x, y: x + y, inners)
	outer = reduce(lambda x, y: x + y, outers)

	#print "inner: %s"%inner
	#print "outer: %s"%outer

	outer = outer.split("_")
	outer = map(lambda x: len(x), outer)
	maxlen = max(outer)

	inner = inner.split("_")
	inner = filter(lambda x: x, inner)
	numin = len(inner)

	print maxlen, numin
