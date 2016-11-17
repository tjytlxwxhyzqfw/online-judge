if __name__ == "__main__":
	mod = int(raw_input())
	h, m = [list(x) for x in raw_input().split(":")]

	if mod == 24:
		if h[0] == "2":	
			if h[1] not in "0123":
				h[1] = 0
		elif h[0] not in "01":
			h[0] = "0"
	if mod == 12:
		if h[0] == "0":
			if h[1] == "0":
				h[1] = "1"
		elif h[0] == "1":
			if h[1] not in "012":
				h[1] = "1"
		elif h[0] not in "01":
			if h[1] == "0":
				h[0] = "1"
			else:
				h[0] = "0"

	if m[0] not in "012345":
		m[0] = "0"

	print h[0] + h[1] + ":" + m[0] + m[1]
