import sys

def color(string):
	if "C" in string:
		return True
	if "M" in string:
		return True
	if "Y" in string:
		return True
	return False

if __name__ == "__main__":
	y, x = [int(x) for x in raw_input().split()]
	for i in range(y):
		line = raw_input()
		if color(line):
			print "#Color"
			sys.exit(0)
	print "#Black&White"
