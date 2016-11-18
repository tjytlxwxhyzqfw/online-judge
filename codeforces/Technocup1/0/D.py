def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

a, n = [], 0
demands = []

sizeno = {'S':0, 'M':1, 'L':2, 'XL':3, 'XXL':4, 'XXXL':5}
sizenm = ['S', 'M', 'L', 'XL', 'XXL', 'XXXL']

class demand(object):
	def __init__(self, arr):
		self.one = True
		self.x = sizeno[arr[0]]
		self.y = -1
		self.sizenm = None

		if len(arr) == 2:
			self.one = False
			self.y = sizeno[arr[1]]

	def __str__(self):
		return "(%s, %s)"%(sizenm[self.x], -1 if self.y == -1 else sizenm[self.y])

def distribute(i):
	global a
	for d in demands:
		x, y, one = d.x, d.y, d.one
		if x != i or one:
			continue
		#print "\t\tdistribut for: %s"%d
		#print "\t\ta: %s"%a
		if a[x] > 0:
			a[x] -= 1
			d.sizenm = sizenm[x]
		elif a[y] > 0:
			a[y] -= 1
			d.sizenm = sizenm[y]
		else:
			#print "\t\tFailed"
			return False
		#print "\t\tdistribute: %s: %s"%(d, d.sizenm)

	return True

def solve():
	global a, n, demands

	a = read(int)
	n = int(read())

	for i in xrange(n):
		arr = read().split(',')
		#print "arr: %s"%arr
		demands.append(demand(arr))

	for d in demands:
		x, one = d.x, d.one
		if one:
			d.sizenm = sizenm[x]
			a[x] -= 1
	for x in a:
		if x < 0:
			print 'NO'
			return

	#print "--->"
	#for d in demands:
		#print d
	#print "--->"

	for i in range(5):
		res = distribute(i)
		if not res:
			print 'NO'
			return

	print 'YES'
	for d in demands:
		print d.sizenm

if __name__ == "__main__":
	solve()
