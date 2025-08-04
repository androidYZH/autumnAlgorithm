package algorithm_20250730;


import java.util.Arrays;

public class Main {
    //最大子序和-找出最大的连续子数组
    public static int fun(int[] nums){
        int n = nums.length;
        //建立dp数组 dp[i]表示包含其的最大子数组和
        int[] dp = new int[n];
        //初始化dp数组
        dp[0]=nums[0];
        int max = dp[0];
        //开始进行状态转移
        for (int i=1;i<n;i++){
            dp[i]= Math.max(nums[i],dp[i-1]+nums[i]);
            max = Math.max(dp[i],max);
        }
        return max;
    }
    public static int fun_2(int[] nums){
        int n = nums.length;
        int max = nums[0];
        int temp = nums[0];
        for (int i=1;i<n;i++){
            temp = Math.max(nums[i],temp+nums[i]);
            max = Math.max(temp, max);
        }
        return max;
    }

    //最长递增子序列
    public static int fun_3(int[] nums){
        int n = nums.length;
        //建立dp数组
        int[] dp = new int[n];
        //初始化dp数组
        Arrays.fill(dp,1);
        int max = 1;
        //开始进行状态转移
        for (int i=1;i<n;i++){
            //往前进行遍历
            for (int j=i-1;j>=0;j--){
                if (nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    //乘积最大的子数组
    //遍历每一个数 往前找出最大负数和正数
    public static int fun_4(int[] nums){
        int n= nums.length;

        int max = nums[0];
        int min = nums[0];
        int res = nums[0];

        for (int i=1;i<n;i++){
            int temp = max;
            max = Math.max(Math.max(min*nums[i],max*nums[i]),nums[i]);
            min = Math.min(Math.max(temp*nums[i],min*nums[i]),nums[i]);
            res = Math.max(res,max);
        }
        return res;
    }

    //最长重复子数组-找出两个最长子数组的长度
    //  3 2 1 4 7
    //1
    //2
    //3
    //2
    //1
    //建dp数组
    public static int fun_5(int[] nums1, int[] nums2){
        int n = nums1.length;
        int m = nums2.length;
        int max = 0;
        //建立dp数组
        int[][] dp = new int[n][m];
        //初始化dp数组
        for (int i=0;i<n;i++){
            if (nums1[i]==nums2[0]){
                dp[i][0]=1;
                max=1;
            }
        }
        for (int j=0;j<m;j++){
            if (nums1[0]==nums2[j]){
                dp[0][j]=1;
                max=1;
            }
        }
        //开始进行状态转移
        for (int i=1;i<n;i++){
            for (int j=1;j<m;j++){
                if (nums1[i]==nums2[j]){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    //不相等
                    dp[i][j]=Math.max(Math.max(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]);
                }
                max=Math.max(dp[i][j],max);
            }
        }
        return max;
    }

    //接雨水
    public static int fun_6(int[] height){
        int n = height.length;
        //两个dp数组
        int[] left = new int[n];
        int maxLeft=left[0];
        int[] right = new int[n];
        int maxRight=right[n-1];
        //开始进行状态转移
        for (int i=1;i<n;i++){
            maxLeft=Math.max(maxLeft,height[i-1]);
            left[i]=maxLeft;
        }
        for (int j=n-2;j>=0;j--){
            maxRight=Math.max(maxRight,height[j+1]);
            right[j]=maxRight;
        }
        //开始求值
        int res = 0;
        for (int i=0;i<n;i++){
            int w = Math.min(left[i],right[i])-height[i];
            res += Math.max(w,0);
        }
        return res;
    }

    //爬楼梯
    //3
    // 1 1 1
    // 2 1
    // 1 2
    public static int fun_7(int n){
        if (n<=1){
            return 1;

        }
        //建立dp数组
        int[] dp = new int[n];
        dp[0]=1;
        //初始化dp数组
        dp[1]=2;
        //开始进行状态转移
        for (int i=2;i<n;i++){
            //等于前两级
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n-1];
    }

    //最大正方形
    public static int fun_8(int[][] nums){
        int n = nums.length;
        //建立dp数组
        int[][] dp = new int[n][n];
        int max = 0;
        //初始化dp数组
        for (int i=0;i<n;i++){
            if (nums[i][0]==1){
                dp[i][0]=1;
                max = 1;
            }
        }
        for (int j=0;j<n;j++){
            if (nums[0][j]==1){
                dp[0][j]=1;
                max = 1;
            }
        }
        //开始进行状态转移
        for (int i=1;i<n;i++){
            for (int j=1;j<n;j++){
                dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                max = Math.max(dp[i][j],max);
            }
        }
        return max;
    }



    public static void main(String[] args){
        int i = fun_8(new int[][]{
                {0, 1, 1},
                {1, 1, 1},
                {0, 1, 1}

        });
        System.out.println(i);
    }
}
