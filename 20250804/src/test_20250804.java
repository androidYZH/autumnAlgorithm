import org.omg.CORBA.MARSHAL;

import java.util.Arrays;

public class test_20250804 {
    //编辑距离
    //输入：word1 = "intention", word2 = "execution"
    //  h o u s e
    //r 1 1 1 1 1
    //o 1 1
    //s 1
    public static int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        if (n==0||m==0){
            return Math.max(n,m);
        }
        //总共有这么多
        //初始化dp数组
        int[][] dp = new int[n+1][m+1];
        dp[0][0]=0;
        //初始化dp数组
        for (int i=1;i<=n;i++){
            dp[i][0]=dp[i-1][0]+1;
        }
        for (int j=1;j<=m;j++){
            dp[0][j]=dp[0][j-1]+1;
        }
        //开始进行状态专业
        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                //相等的话
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    //不相等
                    dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                }
            }
        }
        return dp[n][m];
    }

    //不同的二叉搜索树
    public static int numTrees(int n) {
        //建立dp数组 dp[i]表示由i个节点组成的不同二叉搜索树的数量
        //dp[k]=dp[k-1]*dp[i-k]
        int[] dp = new int[n+1];
        //二叉搜索树特点 左边小 右边大
        //初始化dp数组
        dp[0]=1;
        dp[1]=1;
        //从节点数开始
        for (int i=2;i<=n;i++){
            //对于i个节点，尝试从j=1开始
            for (int j=1;j<=i;j++){
                dp[i]+=dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }

    //乘积最大子数组
    public static int maxProduct(int[] nums) {
        int n = nums.length;
        //负数
        int[] dp0 = new int[n];
        //正数
        int[] dp1 = new int[n];
        dp0[0]=nums[0];
        dp1[0]=nums[0];
        int res = dp1[0];
        for (int i=1;i<n;i++){
            if (nums[i]>0){
                //最小数
                dp0[i]=Math.min(nums[i],dp0[i-1]*nums[i]);
                //最大数
                dp1[i]=Math.max(nums[i],dp1[i-1]*nums[i]);
            }else if (nums[i]<0){
                //最小数
                dp0[i]=Math.min(nums[i],dp1[i-1]*nums[i]);
                //最大数
                dp1[i]=Math.max(nums[i],dp0[i-1]*nums[i]);
            }else{
                dp0[0]=0;
                dp1[0]=0;
            }
            res = Math.max(dp1[i],res);
        }
        return res;
    }

    //除自身以外数组的乘积
    // _ 1 2 3 4
    // dp[i]为除去自己的乘积
    //dp[0]=24
    //dp[1]=dp[0]
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        //计算每个元素左边的乘积

        res[0]=1;

        for (int i=1;i<n;i++){
            res[i]=res[i-1]*nums[i-1];
        }
        int right = 1;
        for (int i=n-1;i>=0;i--){
            res[i]=res[i]*right;
            right=right*nums[i];
        }
        return res;
    }
    public static void main(String[] args) {
        int[] ints = productExceptSelf(new int[]{1, 2, 3, 4});
        System.out.println(Arrays.toString(ints));

    }
}
