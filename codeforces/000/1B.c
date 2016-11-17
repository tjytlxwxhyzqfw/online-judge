/*
 * 1B Spreadsheets
 */
#include <assert.h>
#include <ctype.h>
#include <stdio.h>
#include <string.h>

//#include "debug.c"

int to_int(char *s)
{
	char upper;
	int i, power;
	int result;

	result = 0;
	for (i = strlen(s), power = 1; i > 0 && (upper = s[--i]); power *= 26)
		result += power * (upper - 64);

	//printis(2, 0, "to_int: %s: %d\n", s, result); 
	return result;
}

void reverse(char *s, int n)
{
	int i;
	char ch;

	for (i = (n - 1) / 2; i >= 0; --i) {
		ch = s[i];
		s[i] = s[n - 1 - i];
		s[n - 1 - i] = ch;
	}
}


void to_str(char *istr, char *str)
{

	int i, j;
	int len, n, lowest;

	//printis(2, 0, "to_str: %s\n", istr);

	n = 0;
	len = strlen(istr);
	for (i = 0; i < len; ++i)
		n = n * 10 + istr[i] - 48;
	//printis(2, 0, "to_str: n: %d\n", n);

	i = 0;
	do {
		lowest = n % 26;
		str[i++] = lowest ? 64 + lowest : 'Z';
		//printis(3, 0, "str[%d]: %c\n", i - 1, str[i - 1]);
		n /= 26;
		if (!lowest)
			--n;
	} while (n > 0);
	str[i] = 0;

	reverse(str, strlen(str));

	//printis(2, 0, "to_str: str: %s\n", str);
}

int get_style(char *s, int n)
{
	int i;
	char ch;

	if (s[0] != 'R')
		return 1;

	if (isupper(s[1]))
		return 1;

	i = 1;
	while (ch = s[++i]) {
		if (ch == 'C')
			return 2;
		assert(isdigit(ch));
	}

	return 1;
}

int test(void)
{
	char line[10], str[10];

	while (scanf("%s", line)) {
		to_str(line, str);
		to_int(str);
	}
}

int main(void)
{
	char line[24], str[24];
	int ncases, i;
	int style, result;

	scanf("%d", &ncases);
	while (--ncases >= 0) {
		scanf("%s", line);
		style = get_style(line, strlen(line));
		//printis(0, 0, "style: %d\n", style);

		if (style == 1) {
			for (i = 0; isupper(line[i]); ++i){}

			strncpy(str, line, i);
			str[i] = 0;
			//printis(1, 0, "str: %s\n", str);

			result = to_int(str);
			//printis(1, 0, "result: %d\n", result); 

			printf("R%sC%d\n", line + i, result); 
		} else {
			for (i = 0; line[i] != 'C'; ++i){}
			line[i] = 0;

			to_str(line + i + 1, str);
			//printis(1, 0, "str: %s\n", str);

			printf("%s%s\n", str, line + 1);
		}
	}

	return 0;
}
