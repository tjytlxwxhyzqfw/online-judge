/*
 * 1238 Substrings
 *
 * 不像搜索,像是水题,但是很复杂,有点难
 * 暴力过的,有点意外.然而我的代码是有问题的.
 *
 * 失败的思路(我以为是失败,其实就是这个思路过的)
 * 找到前两个字符串的所有子串,
 * 对于这些字串,凡是出现在后续字符串中的,统统保留下来,
 * 否则,删除.
 * 可能的漏洞:对于两个100长度的相同字符串:
 * AAAAA...A
 * AAAAA.AAA
 * 其公共字串的数目有100*100个,很麻烦!
 *
 * 收获:通用双向链表的实现是成功的,并且很容易就能实现;
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* 通用双向链表 */
#include "Source/list.c"

struct substring {
	char *str;
	struct list_head list;
};

#define addr(a) container_of(a,struct substring,list)

struct substring *substring_new()
{
	struct substring *ss;

	assert(ss = malloc(sizeof(struct substring)));
	ss->str = NULL;
	ss->list.next = ss->list.prev = NULL;

	return ss;
}

struct substring *substring_destroy(struct substring *ss)
{
	if (ss)
		free(ss->str);
	free(ss);
}

struct substring *list_find(char *x, struct list_head *list)
{
	struct list_head *listp;
	struct substring *ss;

	for (listp = list->next; listp != NULL; listp = listp->next) {
		ss = addr(listp);
		//printf("list_find:[%s]\n", ss->str);
		if (!strcmp(x, ss->str)) {
			//printf("equal:[%s] = [%s]\n", x, ss->str);
			return ss;
		}
	}

	return NULL;
}

void add(char const *s, int b, int len, struct list_head *list)
{
	char *substr = NULL;

	struct substring *ss;

	if (len > 0) {
		assert(substr = malloc(sizeof(char) * (len + 1)));
		strncpy(substr, s + b, len);
		substr[len] = 0;
		if (list_find(substr, list)) {
			//printf("already exists: [%s]\n", substr);
			return;
		}
		//printf("substring:[%s]\n", substr);
		ss = substring_new();
		ss->str = substr;
		list_add(&ss->list, list);
		//printf("added:[%s]\n", addr(&ss->list)->str);
		//sleep(1);
	}
}

/*
 * 求字符串s和t的所有连续字串
 *
 * @return s和t的所有连续字串构成的链表 
 *
 * @param s
 * @param t
 */
void all_substr(char const *s, char const *t, struct list_head *list)
{
	int best = 0, b = -1;

	int n, m, len, i, j, k, x, y;

	n = (s == NULL ? 0 : strlen(s));
	m = (t == NULL ? 0 : strlen(t));
	//printf("n = %d, m = %d\n", n, m);

	for (i = 0; i < n; ++i) {
		for (j = 0; j < m; ++j) {
			len = 0;
			k = x = i;
			y = j;
			while (x < n && y < m && (s[x++] == t[y++])) {
				++len;
				add(s, i, len, list);
			}
		}
	}
}

void reverse(char *s)
{
	int n, m, op, i;
	char ch;

	n = strlen(s);
	m = n / 2;

	for (i = 0; i < m; ++i) {
		op = n - i - 1;
		ch = s[i];
		s[i] = s[op];
		s[op] = ch;
	}
}

char even[101], odd[101];

int main(void) 
{
	char *last = NULL, *sub = NULL, *in = NULL;

	struct list_head *list = list_new();

	int ncas, n, i, len, best;
	struct list_head *listp;
	struct substring *ss;

	freopen("Inputs/1238", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncas);
	while (ncas--) {
		//printf("\ncase start\n");
		last = sub = NULL;
		in = even;
		scanf("%d", &n);
		for (i = 0; i < n; ++i) {
			scanf("%s", in);
			//printf("[%s]\n", in);
			if (i == 0) {
				in = odd;
			} else if (i == 1) {
				in = even;
				all_substr(odd, even, list);
				reverse(even);
				all_substr(odd, even, list);
			} else {
				//printf("i = %d\n", i);
				for (listp = list->next; listp != NULL; listp = listp->next) {
					ss = addr(listp);
					//printf("check:[%s]\n", ss->str);
					/*!!! 这一步是有问题的,你必须把ss->str或者in其中之一转置一下 !!!*/
					if (!strstr(in, ss->str)) {
						//printf("remove:[%s]\n", ss->str);
						list_unlink(&ss->list, list);
					}
				}
			}
		}
		best = 0;
		for (listp = list->next; listp != NULL; listp = listp->next) {
			ss = addr(listp);
			sub = ss->str;
			len = strlen(sub);
			if (len > best)
				best = len;
			//printf("left:[%s][len=%d]\n", sub, len);
			list_unlink(listp, list);
			substring_destroy(ss);
		}
		printf("%d\n", best);
	}

	return 0;
}
