## 768C. Jon Snow and his Favourite Number
Set and Editorial by: vntshh

The range of strengths of any ranger at any point of time can be [0,1023]. 
This allows us to maintain a frequency array of the strengths of the rangers.

Now, the updation of the array can be done in the following way:
1. Make a copy of the frequency array.
2. If the number of rangers having strength less than a strength y is even, and there are freq[y] rangers having strength y,
	ceil(freq[y] / 2) rangers will be updated and will have strengths y^x, and the remaining will retain the same strength.
3. If the number of rangers having strength less than a strength y is odd,and there are freq[y] rangers having strength y,
	floor(freq[y] / 2) rangers will be updated and will have strengths y^x, and remaining will have the same strength.
4. This operation has to be done k times, thus the overall complexity is O(1024 * k).

Complexity: O(k * 210)

迭代过程陷入循环的其他题目: 383A
