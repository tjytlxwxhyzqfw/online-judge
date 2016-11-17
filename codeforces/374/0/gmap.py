import sys

if __name__ == "__main__":
	nout, npts = [int(x) for x in sys.argv[1:3]]
	negs = 0

	edges = []
	for i in range(4):
		routs = [i+j+1 for j in range(nout)]
		outs = filter(lambda x: x < npts, routs)
		for out in outs:
			#print i, out, 1
			edges.append((i, out, 1))
			negs += 1

	print npts, negs, 100
	for u, v, c in edges:
		print u+1, v+1, c
