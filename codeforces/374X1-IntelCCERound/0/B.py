import sys

vs = "aeiouy"
n, patterns = 0, []

def num(word):
	global vs
	word = map(lambda x: 1 if x in vs else 0, word)
	return sum(word)

if __name__ == "__main__":
	n = int(raw_input())
	patterns = [int(x) for x in raw_input().split()]

	for i in range(n):
		words = raw_input().split()
		#print "%s"%words

		words = map(num, words)
		#print "words(mapped): %s"%words

		if sum(words) != patterns[i]:
			print "NO"
			sys.exit(0)

	print "YES"
	sys.exit(0)
