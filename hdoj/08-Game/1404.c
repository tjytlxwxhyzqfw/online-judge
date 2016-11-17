/**
 * 1404 Digital Deletions
 * JAVA: TLE
 * C: AC
 */
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define U 0
#define P 1
#define N 2

#define LMAX 6

char *queues[LMAX + 1];
int buffer1[1000000], buffer2[1000000];

int my_pow10(int n) {
	int ret = 1;
	while (n--)
		ret *= 10;
	return ret;
}

int parse_int(char *str){
	int ret = 0;

	int length;
	int i;

	length = strlen(str);
	for (i = 0; i < length; ++i)
		if (str[i] != '0')
			break;
	for (; i < length; ++i) {
		ret = ret * 10 + str[i] - '0';
	}

	//printf("%6s: %d\n", str, ret);

	return ret;
}

void queues_alloc() {
	int i;
	int base = 1;
	for (i = 1; i <= LMAX; ++i) {
		base *= 10; // base = pow(10, i);
		assert(queues[i] = malloc(sizeof(char) * base));
	}
}

char make_all_moves(int length, int value) {
	int i, j;
	int base, mask;
	int bitv, delta;
	char state;
	for (i = 0; i < length; ++i) {
		base = my_pow10(i);
		mask = base * 10;
		bitv = (value / base) % 10;
		//printf("make_all_moves(): %d, %6d\n", length, value);
		if (bitv == 0) {
			delta = length - i - 1;
			//printf("delta: %d\n", delta);
			if (delta == 0)
				return N;
			state = queues[delta][value / mask];
			if (state == P)
				return N;
			else if (state == U)
				return U;
		} else {
			for (j = 1; j <= bitv; ++j) {
				//printf("value - j * base: %d\n", value - j * base);
				state = queues[length][value - j * base];
				if (state == P)
					return N;
				else if (state == U)
					return U;
			}
		}
	}
	return P;
}

void traverse(const int length, const int cands[], const int ncands, int remains[], int *nremains) {
	int i, value;
	char  state;

	//printf("INVOKE: traverse: %d\n", length);
	
	*nremains = 0;
	for (i = 0; i < ncands; ++i) {
		value = cands[i];
		//printf("value: %d\n", value);
		state = make_all_moves(length, value);
		if (state == U)
			remains[(*nremains)++] = value;
		else {
			queues[length][value] = state;
			//printf("(%d, %6d): %c\n", length, value, state == P ? 'P' : 'N');
			//getchar();
		}
	}
	
	//printf("RETURN: traverse(): %d\n", length);
}

void queues_fill() {
	int i;
	int base, ncands, nremains, tmp_int, length;
	int *cands, *remains, *tmp_array;

	base = 1;
	cands = buffer1;
	remains = buffer2;
	for (length = 1; length <= LMAX; ++length) {
		base *= 10; // base = pow(10, length)
		for (i = 0; i < base; ++i)
			cands[i] = i;
		ncands = i;
		while (ncands > 0) {
			traverse(length, cands, ncands, remains, &nremains);

			tmp_int = ncands;
			ncands = nremains;
			nremains = tmp_int;

			tmp_array = cands;
			cands = remains;
			remains = tmp_array;
		}
	}
}

void queues_init() {
	queues_alloc();
	queues_fill();
}

int main(void)
{
	int value, length;
	char line[7];

	queues_init();
	//printf("ready\n");
	while (scanf("%s", line) != EOF) {
		length = strlen(line);
		value = parse_int(line);
		printf("%s\n", queues[length][value] == P ? "No" : "Yes");
	}

	return 0;
}
