import re

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	string = list(read())
	for i in range(len(string)):
		if string[i] in "AEIOUY":
			string[i] = " "
	string = "".join(string)
	#print string
	string = string.split(" ")
	lens = [len(x) for x in string]
	print max(lens)+1

if __name__ == "__main__":
	string = read()
	string = re.split(r"[AEIOUY]", string)
	lens = [len(x) for x in string]
	print max(lens)+1
