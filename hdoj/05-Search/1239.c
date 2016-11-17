/*
 * 1239 Calling Extraterrestrial Intelligence Again
 *
 * 算法 - 枚举
 * 类型 - 水题
 *
 * 给定m,a,b 求素数w,h,使得 a/b <= w/h <= 1 且 w*h <= m 且w*h的值最大
 *
 * 被自己的数学计算坑了:
 * 1. 该用 > 的时候用了 >=
 * 2. 放缩失败: 1<=a<=b<=1000 => b < a <= 1 ...
 * 
 * 未发现的优化:
 * 只要求10000个素数就好了,不需要100000个素数
 *
 * 知识点:
 * 1.浮点数与整数的相互转化
 *     浮点数强制类型转换成整数的规则:[向下取整]
 *     浮点数和整数可以直接比较大小,相等
 * 2. 素数的快速生成:[素数筛选法]
 * 3. [sizeof]不能判断数组常量的大小!最好与malloc搭配使用
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int primes[100000], npm;

void fill_primes(int *primes)
{
	int i, base, step;

	memset(primes, 1, sizeof(int) * 100000);

	primes[0] = primes[1] = 0;

	for (base = 2; base < 100000; ++base) {
		if (!primes[base])
			continue;
		/* printf("base:%d\n", base); */
		for (i = 2 * base; i < 100000; i += base)
			primes[i] = 0;
	}
}

int nextprime(int n)
{
	int i;

	for (i = n + 1; i < 100000; ++i)
		if (primes[i])
			return i;
	return 100001;
}

int main(void)
{
	int s, h, w, best, wbest, hbest, wmin;
	int m, a, b;
	float f;

	freopen("Inputs/1239", "r", stdin);
	setbuf(stdout, NULL);

	fill_primes(primes);

	while (scanf("%d%d%d", &m, &a, &b) != EOF && m) {
		best = 0;
		for (h = 2; h <= 10000; h = nextprime(h)) {
			f = 1.0 * a * h / b;
			wmin = ((int)f >= f ? (int)f : (int)f + 1);
			//printf("wmin:%d (=%d*%d/%d)\n", wmin, h, a, b);
			wmin = primes[wmin] ? wmin : nextprime(wmin);
			//printf("wmin(prime) = %d\n", wmin);
			sleep(1);
			if (h * wmin > m)
				break;
			for (w = wmin; w <= h; w = nextprime(w)) {
				s = w * h;
				//printf("s:%d(=%d * %d), best:%d\n", s, w, h, best);
				if (s <= m && s > best) {
					//printf("best:%d (=%d*%d)\n", s, w, h);
					best = s;
					wbest = w;
					hbest = h;
				}
			}
		}
		printf("%d %d\n", wbest, hbest);
		//sleep(3);
	}

	return 0;
}
