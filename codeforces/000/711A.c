/*
 * 711A
 */
#include <stdio.h>


char lines[1000][6];
//FIXME
//#define ok(x) ((lines[(x)] == 'O' && lines[x + 1] == 'O'))
#define ok(i, x) ((lines[(i)][(x)] == 'O' && lines[(i)][(x) + 1] == 'O'))
#define op(i, x) ((lines[(i)][(x)] = lines[(i)][(x) + 1] = '+'))

int main(void)
{
	int n, i;
	int result;

	//FIXME: don't be nervous
	freopen("Inputs/711A", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &n);
	for (i = 0; i < n; ++i)
		scanf("%s", lines + i);

	result = 0;
	for (i = 0; i < n; ++i) {
		//FIXME
		if (ok(i, 0)) {
			result = 1;
			op(i, 0);
			break;
		} else if (ok(i, 3)) {
			result = 1;
			op(i, 3);
			break;
		}
	}

	if (result) {
		printf("%s\n", "YES");
		for (i = 0; i < n; ++i)
			printf("%s\n", lines[i]);
	} else {
		printf("%s\n", "NO");
	}

	return 0;
}
