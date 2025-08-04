package algorithm_20250731;


public class Solution {
    //相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pa = headA;
        ListNode pb = headB;
        //将链表进行往后移动
        while (pa!=null&&pb!=null){
            pa=pa.next;
            pb=pb.next;
        }
        ListNode newHeadA=headA;
        ListNode newHeadB=headB;
        //此时headA长
        if (pa!=null){
            while (pa!=null){
                pa=pa.next;
                newHeadA=newHeadA.next;
            }
        }else if (pb!=null){
            //此时pb长
            while (pb!=null){
                newHeadB=newHeadB.next;
                pb=pb.next;
            }
        }
        //此时newHeadA和newHeadB至末尾一样长
        while (newHeadA!=null&&newHeadB!=null&&newHeadA!=newHeadB){
            newHeadA=newHeadA.next;
            newHeadB=newHeadB.next;
        }
        //只要返回一个节点就可以
        return newHeadA;
    }

    //跳跃游戏-可以进行尝试跳跃
    public static boolean canJump(int[] nums) {
        int pos = 0;
        //数组，当前所在位置
        return dfs_canJump(nums,pos);
    }

    private static boolean dfs_canJump(int[] nums, int pos) {
        if (pos==nums.length-1){
            return true;
        }
        //大于直接
        if (pos>nums.length-1){
            return false;
        }
        //可以跳1-nums[pos]步
        for (int i=1;i<=nums[pos];i++){
            //开始进行跳跃
            dfs_canJump(nums, pos + i);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("hello world");
    }
}
