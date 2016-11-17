/*
 * 1181 变形课
 */

#include <assert.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct queue {
	int size, first, last;
	void **cell;
};

struct queue * queue_new(int size) {
	struct queue *q;

	assert(q = malloc(sizeof(struct queue)));
	q->size = size;
	q->first = q->last = 0;
	assert(q->cell = malloc(sizeof(void*) * q->size));
	
	return q;
}

void queue_destroy(struct queue *q) {
	if (q)
		free(q->cell);
	free(q);
}

void queue_append(void *x, struct queue *q) {
	q->cell[q->last++] = x;
	q->last %= q->size;
}

void *queue_pop(struct queue *q) {
	void *ret;
	
	ret = q->cell[q->first++];
	q->first %= q->size;

	return ret;
}

char queue_isempty(struct queue *q) {
	return q->first == q->last;
}

/*
 * letters: {0, 1, ..., 25}
 */
char map[26][26], letters[26];
int discover[26];

/*
 * map[b][x]
 * 这个函数需要从map[b][a] - map[b][z]分别调用一次
 *
 * @param x - 单次的序号,a-0, b-1, ..., z-25
 * @return 1 - 可以从b->m, 0 - 不可以从b->m
 */
char bfs(void)
{
	char ret = 0;

	char *line, i, *lp;
	struct queue *q;

	q = queue_new(26);
	memset(discover, 0, sizeof(discover));

	/* append b in queue */
	queue_append(letters + 1,  q);

	check:
	queue_append(NULL, q);
	while ((lp = (char*)queue_pop(q)) != NULL) {
		//printf("current letter: %c\n", *lp + 97);
		line = map[*lp];
		for (i = 0; i < 26; ++i) {
			if (line[i] && !discover[i]) {
				if (letters[i] == 'm' - 97) {
					ret = 1;
					goto exit;
				}
				discover[i] = 1;
				queue_append(letters + i, q);
				//printf("discover & enqueue: %c(%d,%d)\n", i + 97, q->first, q->last);
			}
		}
	}
	if (!queue_isempty(q))
		goto check; 
	exit:
	queue_destroy(q);
	return ret;
}

int main(void)
{
	char last = -1, first = -1;

	char ch;

	freopen("Inputs/1181", "r", stdin);
	setbuf(stdout, NULL);

	memset(map, 0, sizeof(map));
	for (ch = 0; ch < 26; ++ch)
		letters[ch] = ch;

	while ((ch = getchar()) != EOF) {
		if (ch == '0') {
			printf("%s.\n", bfs() ? "Yes" : "No");
			memset(map, 0, sizeof(map));
			last = first = -1;
		} else if (ch == '\n' && last != -1 && first != -1) {
			//printf("map[%c][%c] = 1\n", first + 97, last + 97);
			map[first][last] = 1;
			first = last = -1;
		} else if (islower(ch)) {
			last = ch - 97;
			if (first == -1)
				first = last;
		}
	}

	return 0;
}
	
