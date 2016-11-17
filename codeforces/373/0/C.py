
n = None
t = None
string = None

def roudat(i):
	global n, t, string
	t -= 1
	carry = 1
	j = 0
	for j in range(i-1, -1, -1):
		if string[j] == ".":
			continue
		res = int(string[j]) + carry
		string[j] = "" + res % 10
		carry = res / 10
		if carry == 0:
			break;
	return carry, j

if __name__ == "__main__":
	[n, t] = [int(x) for x in raw_input().split()]
	string = list(raw_input().strip())
	for start in range(n):
		if string[start] == ".":
			break
	start += 1

	for i in range(start, n):
		
