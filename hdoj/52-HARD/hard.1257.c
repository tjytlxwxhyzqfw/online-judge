/*
 * 1257 最少拦截系统
 *
 * Is this a dp problem ?
 *
 * [06-27 09:53] WA: I am stupid. 4 5 10 4 6 is 2 but I will output 3.
 * [06-28 10:59] tow dp
 * [06-28 12:23] heap and two dp are wrong, I give up.
 */

#include <stdio.h>

#define N 100000

int heights[N];

int main(void)
{
	freopen("Inputs/1257", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d", &n) != EOF) {
		for (i = 0; i < n; ++i)
			scanf("%d", heights + i);
		heap_insert(heights + n - 1, h);
		minh = heights[n - 1];
		systems = 1;
		for (i = n - 2; i >= 0; --i) {
			height = heights[i];
			minh = h->cell[1];

			heap_insert(height + i, h);
			/* h[i] */
			if (height < minh) {
				/* new system */
				++systems;

				/* new initial height */
				minh = h;
			} else {
				minh = h->cell[1];
			}

				
			
	}

	return 0;
}
