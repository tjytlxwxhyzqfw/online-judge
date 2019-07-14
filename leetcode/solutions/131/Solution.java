import java.util.*;

/**
 * 131 Palindrome Partitioning
 *
 * 动态规划
 *
 * (i)表示从一定使用s[i:]的回文划分方式
 * 这样的话, [i] = s[i:x] X [x:], for all s[i:x] is palindrome
 *
 * 所有问题(j>i)都解决了, 看能不能解决(i)
 * 这个动态规划的形式有点特别?
 *
 */
public class Solution {
    private List<List<List<String>>> results = new ArrayList<>();
    private String string;
    private Integer length;

    public List<List<String>> partition(String s) {
        if (s==null || s.length()==0)
            return new ArrayList<>();

        string = s;
        length = s.length();

        for (int i = 0; i < length; ++i)
            results.add(null);

        results.set(length-1, Arrays.asList(Arrays.asList(""+s.charAt(length-1))));
        for (int i = length-2; i >= 0; --i) {
            List result = getStartsWith(i);
            results.set(i, result);
        }

        return results.get(0);
    }

    private List<List<String>> getStartsWith(Integer begin) {
        // string[begin:]的回文拆分结果
        List<List<String>> result = new ArrayList<>();

        for (int end = begin+1; end <= length; ++end) {
            String substr = string.substring(begin, end);
            // string[begin:end]是回文吗?
            if (ispal(substr)) {
                // 若是, [substr] X string[end:]的回文拆分结果
                // 注意, 如果end越界了, 那么string[begin:]的此次回文拆分结果就只有一个
                if (end < length) {
                    for (List<String> subparts : results.get(end)) {
                        List<String> parts = new ArrayList<>();
                        parts.add(substr);
                        parts.addAll(subparts);
                        result.add(parts);
                    }
                } else {
                    result.add(Arrays.asList(substr));
                }
            }
        }
        return result;
    }

    private boolean ispal(String s) {
        int length = s.length();
        int half = length/2;
        for (int i = 0;  i < half; ++i)
            if  (s.charAt(i) != s.charAt(length-1-i))
                return false;
        return true;
    }

    public static void main(String args[]) {
        List<List<String>> result = new Solution().partition("");
        for (List list : result)
            System.out.printf("%s\n", list.toString());
    }
}
