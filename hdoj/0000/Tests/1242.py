from random import randfloat

def random_symbol(weights):
	ceil = reduce(lambda x, y: x + y, [x[1] for x in weights]);
	while True:
		thresh = randfloat(0, ceil);
		print "thresh = %s"%thresh;
		s = reduce(lambda x, y: (x[1] + y[1]) if isinstance(x, int) and x < ceil else x, weights);
		yield s
		  

#def generate_map(hmax, wmax, weights):
#	h, w = randint(1,hmax), randint(1, wmax)
	
