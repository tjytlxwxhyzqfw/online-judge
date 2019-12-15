import os
import random

def main():
	cands = os.listdir('..')
	while True:
		x = int(random.random() * len(cands))
		if 0 < int(x) < 200:
			print(cands[x])
			break

if __name__ == '__main__':
	main()
