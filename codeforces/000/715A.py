import sys

if __name__ == "__main__":
	n = int(raw_input())
	print 2
	if n == 1:
		sys.exit(0)
	for i in range(2, n+1):
		print i*pow(i+1, 2) - (i-1)
	
