import java.util.*;

/**
 * 130 Surrounded Regions
 *
 * 从没一个在边界上的O开始, 向里面深度优先搜索
 * NO!是广度优先搜索.
 * DFS会栈溢出的!!!!!!!!
 */
public class Solution {
    private static final int direc[] = {1, -1, 0, 0, 0, 0, 1, -1};
    private char board[][];
    private int height, width;
    private int ndfs1s = 0;

    public void solve(char board[][]) {
        if (board == null || board.length==0 || board[0]==null || board[0].length==0)
            return;


        this.board = board;
        height = board.length;
        width = board[0].length;

        for (int y = 0; y < height; ++y) {
            for (int x= 0; x < width; ++x) {
                if (onbounder(y, x) && board[y][x] == 'O')
                    bfs(y, x);
            }
        }

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                if (board[y][x] == '.')
                    board[y][x] = 'O';
                else if (board[y][x] == 'O')
                    board[y][x] = 'X';
            }
        }
    }

    /*
     * DFS会在递归深度达到5000(五千)时 StackOverflowException
     * java的递归深度还是太浅!!!!!
     */
    private void dfs1(int startY, int startX) {
        System.out.printf("dfs%d: (%2d, %2d), total:%d\n", ++ndfs1s, startY+1, startX+1, height*width);
        board[startY][startX] = '.';
        for (int d = 0; d < 4; ++d) {
            int y = startY + direc[d];
            int x = startX + direc[d+4];
            if (inboard(y, x) && board[y][x] == 'O')
                dfs1(y, x);
        }
    }

    /* BFS还是稳! */
    private void bfs(int startY, int startX) {
        List<Integer> start = Arrays.asList(startY, startX);
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(start);
        board[startY][startX] = '.';
        while (!queue.isEmpty()) {
            List<Integer> current = queue.remove();
            Integer curY = current.get(0), curX = current.get(1);
            System.out.printf("bfs pop: (%d, %d)\n", curY, curX);
            for (int d = 0; d < 4; ++d) {
                Integer y=curY+direc[d], x=curX+direc[d+4];
                if (inboard(y, x) && board[y][x]=='O') {
                    queue.add(Arrays.asList(y, x));
                    board[y][x] = '.';
                }
            }
        }
    }

    private boolean inboard(int y, int x) {
        return (y >= 0 && y < height && x >= 0 && x < width);
    }

    private boolean onbounder(int y, int x) {
        return y==height-1 || x==width-1 || x==0 || y==0;
    }

    public static void main(String args[]) {
        String strings[] = {"XXXX", "XOOX", "XOOX", "XOXX"};
        int height = strings.length;
        int width = strings[0].length();
        char board[][] = new char[height][width];
        for (int y = 0; y < height; ++y)
            for (int x = 0; x < width; ++x)
                board[y][x] = strings[y].charAt(x);
        new Solution().solve(board);

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x)
                System.out.printf("%c", board[y][x]);
            System.out.printf("\n");
        }
    }
}
