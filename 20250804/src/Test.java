import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test {
    //跳跃游戏
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        return dfs_canJump(0,nums);
    }

    //[2,3,1,1,4]
    private static boolean dfs_canJump(int pos, int[] nums) {
        //此时已超过
        if (pos>=nums.length-1){
            return true;
        }

        //必须往前面跳
        for (int i=1;i<=nums[pos];i++){
            //往前面跳
            boolean res = dfs_canJump(pos + i, nums);
            //如果找到了
            if (res){
                return true;
            }
        }
        return false;
    }

    //最短无序连续子数组-找出第一个乱序的
    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int resI = 0;
        while (resI<n-1&&nums[resI]<nums[resI+1]){
            resI++;
        }
        //此时是顺序的，直接返回0
        if (resI==n-1){
            return 0;
        }
        int resJ = n-1;
        while (resJ>0&&nums[resJ]<nums[resJ-1]){
            resJ--;
        }
        //表示是倒叙的
        if (resJ == n-1){
            return n;
        }
        //3 2 4
        //此时resI表示有序的最后一个 resJ表示有序的最后一个
        //找出无序数组中的最大值和最小值
        int maxV = nums[resI];
        int minV = nums[resI];
        for (int k=resI;k<=resJ;k++){
            if (nums[k]>maxV){
                maxV=nums[k];
            }
            if (nums[k]<nums[k]){
                minV=nums[k];
            }
        }
        //开始进行移动
        while (resI>0&&nums[resI-1]>minV){
            resI--;
        }
        while (resJ<n-1&&nums[resJ+1]<maxV){
            resJ++;
        }
        //返回
        return resJ-resI+1;
    }



    //跳跃游戏
    //dp[i]
    public static boolean canJump_fun(int[] nums) {
        int n = nums.length;
        //初始全为false
        boolean[] canJump = new boolean[n];
        if (nums.length==1){
            return true;
        }
        //初始化
        if (nums[0]>=1){
            canJump[0]=true;
        }
        for (int i=0;i<n;i++){
            //不能跳的地方直接false
            if (!canJump[i]){
                continue;
            }
            //记录能够跳的地方
            for (int j=0;j<=nums[i];j++){
                //要在范围内
                if (i+j<n){
                    canJump[i+j]=true;
                }
            }
        }

        return canJump[n - 1];
    }
    //
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode quick = head;
        while (quick!=null&&quick.next!=null){
            slow=slow.next;
            quick=quick.next.next;
            //相交
            if (slow==quick){
                ListNode newQuick = head;
                while (newQuick!=slow){
                    slow = slow.next;
                    newQuick=newQuick.next;
                }
                return slow;
            }
        }
        //没有环
        return null;
    }

    //只出现一次的数字
    public static int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i=1;i<nums.length;i++){
            res = res ^ nums[i];
        }
        return res;
    }
    //零钱兑换 2 5 8
    public static int coinChange(int[] coins, int amount) {
        //传入参数dp[i]表示amount为i的最少硬币数量
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0]=0;
        for (int i=1;i<=amount;i++){
            for (int j:coins){
                //注意边界条件
                if (i>=j){
                    //找出一个最小的
                    dp[i]=Math.min(dp[i-j]+1,dp[i]);
                }
            }
        }
        return dp[amount]>amount?-1:dp[amount];
    }

    //目标和
    public static int findTargetSumWays(int[] nums, int target) {
        int[] count = new int[1];
        dfs_find(nums, target, 0, count);
        return count[0];
    }

    private static void dfs_find(int[] nums, int target, int i, int[] count) {
        // 到结束的条件：处理完所有元素且目标和为0
        if (i == nums.length && target == 0) {
            count[0]++;
            return;
        }
        // 如果已处理完所有元素但目标和不为0，直接返回
        if (i == nums.length) {
            return;
        }
        // 两种情况：当前元素加正号或负号
        for (int k = 0; k < 2; k++) {
            dfs_find(nums, target + (k % 2 == 0 ? 1 : -1) * nums[i], i + 1, count);
        }
    }

    //汉明距离
    public static int hammingDistance(int x, int y) {
        int res = x^y;
        int count =0;
        while (res!=0){
            if ((res&1)!=0) {
                count++;
            }
            res = res >> 1;
        }
        return count;
    }

    //找出数组中消失的数字
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        HashMap<Integer,Boolean> hashMap = new HashMap<>();
        for (int i:nums){
            hashMap.put(i,true);
        }
        List<Integer> res = new ArrayList<>();
        for (int i=1;i<=n;i++){
            if (!hashMap.containsKey(i)){
                res.add(i);
            }
        }
        return res;
    }
    //4, 3, 2, 7, 8, 2, 3, 1
    //-4-3 -2 -7    -2 -3
    public static List<Integer> findDisappearedNumbers_fun(int[] nums) {
        for (int i=0;i<nums.length;i++){
            int temp = Math.abs(nums[i]);
            //还未出现
            if (nums[temp-1]>0){
                nums[temp-1]=nums[temp-1]*-1;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            if (nums[i]>0){
                res.add(i+1);
            }
        }
        return res;
    }

    //两数相加
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dump = new ListNode(-1);
        ListNode p = dump;
        ListNode p1 = l1;
        ListNode p2 = l2;
        //
        int k=0;
        while (p1!=null&&p2!=null){
            int res = p1.val+p2.val+k;
            k = res / 10;
            p.next = new ListNode(res % 10);
            p=p.next;
            p1=p1.next;
            p2=p2.next;
        }
        while (p1!=null){
            int res = p1.val+k;
            k = res / 10;
            p.next = new ListNode(res % 10);
            p=p.next;
            p1=p1.next;
        }
        while (p2!=null){
            int res = p2.val+k;
            k = res / 10;
            p.next = new ListNode(res % 10);
            p=p.next;
            p2=p2.next;
        }
        return dump.next;
    }
    public static void main(String[] args) {

    }
}
