package Main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    //爬楼梯问题
    //返回爬楼梯的方法数
    public static int climbStairs(int n) {
        //建立dp数组
        int[] arr = new int[n];
        if (n<=2){
            return n;
        }
        //初始化dp数组
        arr[0]=1;
        arr[1]=2;
        //开始状态转移
        for (int i=2;i<n;i++){
            arr[i]=arr[i-1]+arr[i-2];
        }
        return arr[n-1];
    }

    //爬楼梯方法2
    public static int climbStairs_2(int n) {
        if (n<=2){
            return n;
        }
        int a = 1;
        int b = 2;
        n = n - 2;
        while (n-->0){
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    //不同路径
    public static int uniquePaths(int m, int n) {
        //创建dp数组
        int[][] dp = new int[m][n];
        //初始化dp数组
        for (int i=0;i<m;i++){
            dp[i][0]=1;
        }
        for (int j=0;j<n;j++){
            dp[0][j]=1;
        }

        //进行dp数组的状态转移
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                //该数据只能由上面或者左边获取
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }


    //不同路径II—存在障碍物的不同路径-障碍物用1进行表示
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        //建立dp数组
        int[][] dp = new int[n][m];
        //进行状态转移
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                //判断是否存在障碍物
                if (obstacleGrid[i][j]==1){
                    //存在障碍物
                    dp[i][j]=0;
                }else{
                    if (i==0&&j==0){
                        dp[i][j]=1;
                        continue;
                    }
                    //第一行和第一列
                    if (i==0){
                        dp[i][j]=dp[i][j-1];
                        continue;
                    }
                    if (j==0){
                        dp[i][j]=dp[i-1][j];
                        continue;
                    }
                    //不存在障碍物-即为左边和上边相加
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[n-1][m-1];
    }


    //最小路径和
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        //建立dp数组
        int[][] dp = new int[n][m];
        //开始进行状态转移
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (i==0&&j==0){
                    dp[i][j]=grid[i][j];
                }else if (i==0){
                    dp[i][j]=dp[i][j-1]+grid[i][j];
                }else if (j==0){
                    dp[i][j]=dp[i-1][j]+grid[i][j];
                }else{
                    dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
                }
            }
        }
        return dp[n-1][m-1];
    }

    //三角形最小路径和
    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int m = triangle.get(n-1).size();
        //建立dp数组
        int[][] dp = new int[n][m];
        int res = Integer.MAX_VALUE;
        //开始进行状态转移
        for (int i=0;i<n;i++){
            for (int j=0;j<=i;j++){
                if (i==0&&j==0){
                    //第一个节点
                    dp[i][j]=triangle.get(i).get(j);
                }else if (j==0){
                    //第一列
                    dp[i][j]=dp[i-1][j]+triangle.get(i).get(j);
                }else if (i==j){
                    dp[i][j]=dp[i-1][j-1]+triangle.get(i).get(j);
                }else{
                    //其它部分
                    dp[i][j]=Math.min(dp[i-1][j],dp[i-1][j-1])+triangle.get(i).get(j);
                }
                if (i==n-1){
                    res = Math.min(res,dp[i][j]);
                }
            }
        }
        return res;
    }


    //买卖股票问题-卖还是买的问题
    public static int maxProfit(int[] prices) {
        int m = prices.length;
        //建立dp数组 dp[0][]表示卖获得的最大利润
        //dp[1][]表示买获得的最大利润
        int[][] dp= new int[2][m];
        int res = 0;
        //状态初始化
        dp[0][0]=0;
        dp[1][0]=-prices[0];
        //开始状态转移
        for (int i=1;i<m;i++){
            //卖
            dp[0][i]=Math.max(dp[0][i-1],dp[1][i-1]+prices[i]);
            //买
            dp[1][i]=Math.max(dp[1][i-1],-prices[i]);
            res = Math.max(Math.max(dp[0][i],dp[1][i]),res);
        }
        return res;
    }


    //买卖股票问题-卖还是买的问题
    public static int maxProfit_fun(int[] prices) {
        int n = prices.length;
        //卖出
        int a = 0;
        int b = -prices[0];
        int res = 0;
        for (int i=1;i<n;i++){
            //卖出
            a = Math.max(a, b+prices[i]);
            //买入
            b = Math.max(b,-prices[i]);
            res = Math.max(a,b);
        }
        return res;
    }

    //买卖股票II-可以多次买入卖出 但是只能有一只股票
    public static int maxProfitII(int[] prices) {
        int n = prices.length;
        //建立dp数组
        int[][] dp = new int[n][2];
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        int res = 0;
        //进行状态转移
        for (int i=1;i<n;i++){
            //卖出
            dp[i][0]=Math.max(dp[i-1][1]+prices[i],dp[i-1][0]);
            //买入
            dp[i][1]=Math.max(dp[i-1][0]-prices[i],dp[i-1][1]);
            res = Math.max(dp[i][0],dp[i][1]);
        }
        return res;
    }

    //买卖股票III——可以有两笔交易
    public static int maxProfitIII(int[] prices) {
        int n = prices.length;
        //
        int[][] dp=new int[5][n];
        int res = 0;
        //初始化
        dp[0][0]=-0;
        dp[1][0]=-prices[0];
        dp[2][0]=0;
        dp[3][0]=-prices[0];
        dp[4][0]=0;

        for (int i=1;i<n;i++){
            dp[0][i]=dp[0][i-1];
            //买入一笔还未卖出
            dp[1][i]=Math.max(dp[1][i-1],dp[0][i]-prices[i]);
            //买入一笔卖出一笔
            dp[2][i]=Math.max(dp[2][i-1],dp[1][i]+prices[i]);
            //买入两笔卖出一笔
            dp[3][i]=Math.max(dp[3][i-1],dp[2][i]-prices[i]);
            //买入两笔卖出两笔
            dp[4][i]=Math.max(dp[4][i-1],dp[3][i]+prices[i]);
            res = Math.max(dp[0][i],Math.max(dp[1][i],Math.max(dp[2][i],Math.max(dp[3][i],dp[4][i]))));
        }
        return res;
    }


    //买卖股票四-最多k笔交易
    public static int maxProfitIV(int k, int[] prices) {
        int n = prices.length;

        int[][] dp = new int[2 * k + 1][n];
        //初始化
        for (int m=0;m<=2*k;m++){
            if ((m&1)==1){
                dp[m][0]=-prices[0];
            }
        }
        //开始状态转移
        int res = 0;
        //从i=1开始
        for (int i=1;i<n;i++){
            //总共k维度
            for (int j=0;j<=2*k;j++){
                //当j==0时
                if (j==0){
                    dp[j][i] = dp[j][i-1];
                    continue;
                }
                dp[j][i]=Math.max(dp[j][i-1],dp[j-1][i]+(int)Math.pow(-1,j)*prices[i]);
                res = Math.max(res, dp[j][i]);
            }
        }
        return res;
    }


    //打家劫舍问题
    //1 2 3 1
    public static int rob(int[] nums) {
        int n = nums.length;
        if (nums.length==1){
            return nums[0];
        }
        //建立dp数组
        int[] dp = new int[n];
        //初始化dp数组
        dp[0]=nums[0];
        dp[1]=Math.max(dp[0],nums[1]);
        int max = dp[1];
        //进行状态转移
        for (int i=2;i<n;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
            max = Math.max(dp[i],max);
        }
        return max;
    }

    //打家劫舍II
    public static int robII(int[] nums) {
        if (nums.length==1){
            return nums[0];
        }
        int rob_1 = rob(Arrays.copyOfRange(nums, 0, nums.length - 1));
        int rob_2 = rob(Arrays.copyOfRange(nums, 1, nums.length));
        return Math.max(rob_1,rob_2);
    }

    //打家劫舍III
    public int rob(TreeNode<Integer> root) {
        int[] res = robFun(root);
        return Math.max(res[0],res[1]);
    }

    private int[] robFun(TreeNode<Integer> root) {
        if (root==null){
            return new int[]{0,0};
        }
        int[] leftArr = robFun(root.left);
        int[] rightArr = robFun(root.right);
        // 不偷当前节点：左右孩子可以偷也可以不偷，取最大值之和
        int noSelect = Math.max(leftArr[0], leftArr[1]) + Math.max(rightArr[0], rightArr[1]);
        int select = leftArr[0]+rightArr[0]+root.data;
        return new int[]{noSelect,select};
    }

    //回文子串
    public int countSubstrings(String s) {
        int n = s.length();
        int count = 0;
        for (int i=0;i<n;i++){
            for (int j=i;j<n;j++){
                if (isSubstrings(s,i,j)){
                    count++;
                }
            }
        }
        return count;
    }
    public static boolean isSubstrings(String s, int start, int end){
        while (start<end){
            if (s.charAt(start)!=s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    //  a b c d e
    //a
    //b
    //c
    //d
    //e

    public static int countSubstringsII(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int count = 0;
        //这是层次-从第0层开始进行遍历
        for (int k=0;k<n;k++){
            int i=0;
            int j=i+k;
            while (j<n){
                //第一层
                if (k==0){
                    count++;
                    dp[i][j]=1;
                    i++;
                    j++;
                    continue;
                }
                //第二层
                if (k==1){
                    if (s.charAt(i)==s.charAt(j)){
                        count++;
                        dp[i][j]=1;
                    }
                    i++;
                    j++;
                    continue;
                }
                //第三层++
                if (s.charAt(i)==s.charAt(j)&&dp[i+1][j-1]==1){
                    count++;
                    dp[i][j]=1;
                }
                i++;
                j++;
            }
        }
        return count;
    }
    //  b a b a d
    //b
    //a
    //b
    //a
    //d

    //最长回文子串-找出最长的回文子串 babad
    //判断是不是回文子串
    //是 则更新
    //否 则判断左边和右边最长
    public static String longestPalindrome(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int max = 1;
        int resI = 0;
        int resJ = 0;
        for (int k=0;k<n;k++){
            int i=0;
            int j=i+k;
            while (j<n){
                if (k==0){
                    dp[i][i]=1;
                    if (dp[i][j]>max){
                        resI=i;
                        resJ=j;
                        max=dp[i][j];
                    }
                    i++;
                    j++;
                    continue;
                }
                if (k==1){
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j]=2;
                        if (dp[i][j]>max){
                            resI=i;
                            resJ=j;
                            max=dp[i][j];
                        }
                    }
                    i++;
                    j++;
                    continue;
                }
                if (isSubstrings(s,i,j)){
                    dp[i][j]=j-i+2;
                }else{
                    //不是回文子串
                    dp[i][j]=Math.max(dp[i][j-1],dp[i+1][j]);
                }
                if (dp[i][j]>max){
                    resI=i;
                    resJ=j;
                    max=dp[i][j];
                }
                i++;
                j++;
            }
        }
        return s.substring(resI,resJ+1);
    }

    //最长回文子序列-不需要连续
    //看是否相等 不相等 则看左右
    //相等 则看中间
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int max = 1;
        for (int k=0;k<n;k++){
            int i=0;
            int j=i+k;
            while (j<n){
                //第0层
                if (k==0){
                    dp[i][j]=1;
                    max = Math.max(dp[i][j],max);
                    i++;
                    j++;
                    continue;
                }
                if (k==1){
                    if (s.charAt(i)==s.charAt(j)){
                        dp[i][j]=2;
                    }else{
                        dp[i][j]=1;
                    }
                    max = Math.max(dp[i][j],max);
                    i++;
                    j++;
                    continue;
                }
                //相等
                if (s.charAt(i) == s.charAt(j)) {
                    // 字符相等，长度为内部子串长度+2
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 字符不相等，取左右子串的最大值
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                max=Math.max(dp[i][j],max);
                i++;
                j++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int bbbab = longestPalindromeSubseq("abcabcabcabc");
        System.out.println(bbbab);
    }
}
