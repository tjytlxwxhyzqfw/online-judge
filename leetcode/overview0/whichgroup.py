import datetime

def main():
	base = datetime.datetime(1, 1, 1)
	delta = datetime.datetime.now() - base
	print(delta.days % 20)

if __name__ == '__main__':
	main()
