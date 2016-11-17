/*
 * 1789 Doing Homework Again
 *
 * Primary DP, DFS-DP
 */

int fill_s(int day, int hid)
{
	total = 0;
	if (day == deadmax) {
		for (i = 0; i < hids; ++i) {
			if (i == hid)
				continue;
			total += fine[i];
		}
		if (deadline[i] != deadmax)
			total += fine[hid]
		goto complete;
	}

	for (i = 0; i < total; ++i) {
		base = s[day + 1][i];
		

	

	complete;
	s[day][hid] = total;
	return total;
}
		

int main(void)
{
	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d", &hids);
		deadmax = 0;
		for (i = 0; i < hids; ++i) {
			scanf("%d", deadline + i);
			if (deadline[i] > deadmax)
				deadmax = deadline[i];
		}
		for (i = 0; i < hids; ++i)
			scanf("%d", fine + i);

		for (i = deadmax; i >= 1; --i)
			for (j = 0; j < hids; ++j)
				fill_s(i, j);
	}

	return 0;
}
