import sys

string = None
f = None

def test(i):
	global f
	f = [0 for x in range(26)]
	e = 0
	for j in range(i, i+26):
		if string[j] == '?':
			e += 1
		else:
			f[ord(string[j])-ord('A')] = 1
	used = sum(f)
	if used + e == 26:
		return True
	return False

def fillstring(i):
	global f
	for j in range(i, i+26):
		if string[j] == '?':
			for k in range(26):
				if f[k] == 0:
					f[k] = 1
					string[j] = chr(ord('A')+k)
					break


if __name__ == "__main__":
	string = list(raw_input().strip())
	#string = list("ABC??FGHIJK???OPQR?TUVWXY?")
	#string = list("??????????????????????????")
	#string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	#string = "AACDEFGHIJKLMNOPQRSTUVWXYZ"
	#string = list("AAAABCDEFGHIJKLMNOPQRSTUVWXY?")
	n = len(string)
	if n < 26:
		print -1
		sys.exit(0)
	for i in range(n-25):
		if test(i):
			fillstring(i)
			for j in range(n):
				if string[j] == '?':
					string[j] = 'A'
			print reduce(lambda x, y: x + y, string)
			sys.exit(0)
	print -1
			
