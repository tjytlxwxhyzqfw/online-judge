from random import uniform as randfloat, randint

def symbol_gnrt(weights):
	ceil = reduce(lambda x, y: x + y, [x[1] for x in weights]);
	while True:
		thresh = randfloat(0, ceil);
		#print "tttthresh = %s"%thresh;

		x = 0;
		for t in weights:
			x += t[1];
			if (x > thresh):
				yield t[0]
				break;

def rawmap_gnrt(height, weight, sg):
	while True:
		#print "ok";
		h, w = (randint(2, x) for x in (height, weight));
		#print "%d %d"%(h,w);
		rmap = [[sg.next() for i in range(w)] for j in range(h)];
		yield h, w, rmap;

def map_gnrt(ss, es, rmg):
	while True:
		h, w, m = rmg.next();
		while True:
			#print ("h = %d, w = %d\n"%(h, w));
			(h1, w1, h2, w2) = (randint(0,h-1), randint(0,w-1), randint(0,h-1), randint(0,w-1))
			if h1 != h2 or w1 != w2:
				break;
		m[h1][w1], m[h2][w2] = (ss, es);
		map_string = "%d %d\n"%(h, w);
		for row in m:
			for s in row:
				map_string += s;
			map_string += "\n";
		yield map_string;
 
