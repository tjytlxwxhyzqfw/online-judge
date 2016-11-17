def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n = int(read())
	a = list(read())

	x = [1 if i == 'A' else 0 for i in a]
	y = [1 if i == 'D' else 0 for i in a]

	#print x
	#print y

	x = sum(x)
	y = sum(y)

	if x > y:
		print "Anton"
	elif x < y:
		print 'Danik'
	else:
		print "Friendship"

if __name__ == "__main__":
	solve()
