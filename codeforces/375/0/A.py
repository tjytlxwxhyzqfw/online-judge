if __name__ == "__main__":
	x, y, z = [int(x) for x in raw_input().split()]
	d1 = abs(y-x) + abs(z-x)
	d2 = abs(x-y) + abs(z-y)
	d3 = abs(x-z) + abs(y-z)
	print min([d1, d2, d3])
