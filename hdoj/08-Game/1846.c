/*
 * 1846 Brave Game
 *
 * 这是可以直接提交的
 *
 * 直接找P态和N态的规律: 本题等
 * 直接找SG值: 往桶里放石头等
 * 这都是很好很好的方法,有时还是必须的方法
 */

#include <stdio.h>

int main(void)
{
	int ncases, cas, i;
	int n, m;

	scanf("%d", &ncases);
	for (cas = 0; cas < ncases; ++cas) {
		scanf("%d%d", &n, &m);
		for (i = 0; i * m + i < n; ++i){}
		printf("%s\n", i * m + i == n ? "second" : "first");
	}

	return 0;
}
