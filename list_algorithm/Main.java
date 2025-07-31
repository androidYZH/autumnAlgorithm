package list_algorithm;

import java.util.ArrayList;
import java.util.List;

//链表算法题总结
public class Main {
    //链表的倒数第k个节点
    //1 2
    public static ListNode kthToLast(ListNode head, int k) {
        ListNode p = head;
        while (head!=null&&k-->0){
            head=head.next;
        }
        if (head==null&&k>0){
            return null;
        }
        while (head!=null){
            head=head.next;
            p=p.next;
        }
        return p;
    }
    //从尾到头打印链表-非递归
    public static ListNode fun_2(ListNode head){
        ListNode p = head;
        ListNode pre = null;
        while (p!=null){
            ListNode pp = p.next;
            p.next=pre;
            pre = p;
            p = pp;
        }
        List<Integer> list = printListNode(pre);
        //开始打印
        System.out.println(list);
        return null;
    }
    public static List<Integer> printListNode(ListNode head){
        List<Integer> res = new ArrayList<>();
        while (head!=null){
            res.add(head.val);
            head = head.next;
        }
        return res;
    }
    //反转链表
    public static ListNode fun_3(ListNode head){
        ListNode pre = null;
        ListNode p = head;
        while (p!=null){
            ListNode pp = p.next;
            p.next = pre;
            pre = p;
            p = pp;
        }
        return pre;
    }
    //反转链表II
    public static ListNode fun_4(ListNode head){
        if (head.next==null){
            return head;
        }
        ListNode newHead = fun_4(head.next);
        head.next.next=head;
        head.next=null;
        return newHead;
    }
    //反向打印链表
    public static void fun_5(ListNode head){
        ListNode listNode = fun_4(head);
        printListNode(listNode);
    }
    //判断一个链表是否有环
    public static boolean fun_6(ListNode head){
        ListNode slow = head;
        ListNode quick = head;
        while (quick!=null&&quick.next!=null){
            slow = slow.next;
            quick = slow.next.next;
            if (slow==quick){
                return true;
            }
        }
        return false;
    }
    //链表中环的入口
    public static ListNode fun_7(ListNode head){
        ListNode slow = head;
        ListNode quick = head;
        boolean flag = false;
        while (quick!=null&&quick.next!=null){
            slow=slow.next;
            quick=quick.next.next;
            if (slow==quick){
                quick=head;
                flag=true;
                break;
            }
        }
        if (!flag){
            return null;
        }
        while (slow!=quick){
            slow=slow.next;
            quick=quick.next;
        }
        return slow;
    }

    //合并两个排序的链表
    public static ListNode mergeTwoList(ListNode list1, ListNode list2){
        ListNode dump = new ListNode(-1);
        ListNode p = dump;
        while (list1!=null&&list2!=null){
            if (list1.val<list2.val){
                p.next = list1;
                list1 = list1.next;
            }else{
                p.next = list2;
                list2 = list2.next;
            }
            p=p.next;
        }
        if (list1!=null){
            p.next = list1;
        }
        if (list2!=null){
            p.next = list2;
        }
        return dump.next;
    }

    //找出两个链表的公共节点
    public ListNode findCommonListNode(ListNode head1, ListNode head2){
        ListNode p1 = head1;
        ListNode p2 = head2;
        while (p1!=null&&p2!=null){
            p1=p1.next;
            p2=p2.next;
        }
        ListNode newP;
        //此时p2要长
        if (p1!=null){
            newP = head1;
            while (p1!=null){
                p1=p1.next;
                newP=newP.next;
            }
            p2=head2;
            while (newP!=p2){
                p2=p2.next;
                newP=newP.next;
            }
        } else if (p2!=null){
            newP = head2;
            while (p2!=null){
                p2=p2.next;
                newP=newP.next;
            }
            p1 = head1;
            while (newP!=p1){
                p1=p1.next;
                newP=newP.next;
            }
        }else{
            newP=head1;
            p2=head2;
            while (newP!=p2){
                newP=newP.next;
                p2=p2.next;
            }
        }
        return newP;
    }

    public ListNode fun_8(ListNode head){
        ListNode slow = head;
        ListNode quick = head;
        while (quick!=null&&quick.next!=null){
            slow=slow.next;
            quick=quick.next;
            if (slow==quick){
                return slow;
            }
        }
        return null;
    }
    //计算环的长度
    public int fun_9(ListNode head){
        ListNode listNode = fun_8(head);
        if (listNode==null){
            return 0;
        }
        int len = 1;
        ListNode p = listNode.next;
        while (p!=listNode){
            len++;
            p=p.next;
        }
        return len;
    }
    //1 2 3
    //以时间复杂度O(1)删除链表节点
    public static ListNode fun_10(ListNode head, ListNode node){
        //最后一个节点
        if (node.next==null){
            ListNode p = head;
            while (p.next!=node){
                p=p.next;
            }
            //开始进行删除
            p.next=null;
        }else{
            //不是最后一个节点-类似于删除下一个节点
            node.val=node.next.val;
            node.next=node.next.next;
        }
        return head;
    }
    //链表排序-每次找出最小的节点 类似于选择排序
    //链表进行排序-对链表进行排序7 4 8 9
    public static ListNode listSort(ListNode head){
        ListNode dump = new ListNode(-1);
        ListNode newP = dump;
        //开始外面的循环-判断是不是所有的节点都被移除完
        while (head!=null){
            ListNode pre = null;
            ListNode p=head;
            //找出最小的节点
            ListNode minNodePre = null;
            ListNode minNode = head;
            int min = Integer.MAX_VALUE;
            while (p!=null){
                if (p.val< min){
                    minNodePre=pre;
                    minNode=p;
                    min = p.val;
                }
                pre = p;
                p=p.next;
            }
            //不是头节点
            if (minNodePre!=null){
                minNodePre.next=minNode.next;
            }else{
                //是头节点
                head=head.next;
            }
            newP.next=minNode;
            newP=newP.next;
        }
        return dump.next;
    }

    //7 4 8 9
    //2 1 3
    //
    //链表排序-快速排序
    public static ListNode partition(ListNode head, ListNode end){
        int val = head.val;
        //slow表示指向小于部分的最后一个节点
        ListNode slow = head;
        ListNode quick = head.next;
        while (quick!=end){
            if (quick.val<val){
                slow = slow.next;
                //进行交换节点
                int temp = slow.val;
                slow.val = quick.val;
                quick.val = temp;
            }
            quick=quick.next;
        }
        //将val进行交换过来-将基准值放到中间
        int temp = slow.val;
        slow.val = head.val;
        head.val = temp;
        //返回这个节点
        return slow;
    }

    public static void listQuickSort(ListNode head, ListNode end){
        //end为哨兵 初始为null 没有节点或者节点为一个时终止
        if (head==end || head.next==end){
            return;
        }
        //找到最后一个节点
        ListNode p = partition(head, end);
        listQuickSort(head,p);
        listQuickSort(p.next,end);
    }

    //链表归并排序
    //1 2
    public static ListNode mergeSort(ListNode head){
        if (head==null || head.next==null){
            return head;
        }
        ListNode slowPre = null;
        ListNode slow = head;
        ListNode quick = head;
        //找到中间的节点
        while (quick!=null&&quick.next!=null){
            slowPre=slow;
            slow=slow.next;
            quick=quick.next.next;
        }
        if (slowPre==null){
            return head;
        }
        //将这个节点设置为null
        slowPre.next=null;
        //两个节点
        ListNode leftList = mergeSort(head);
        ListNode rightList = mergeSort(slow);
        return merge(leftList,rightList);
    }
    //开始合并两个链表
    private static ListNode merge(ListNode leftList, ListNode rightList) {
        ListNode dump = new ListNode(-1);
        ListNode p = dump;
        while (leftList!=null&&rightList!=null){
            if (leftList.val<rightList.val){
                p.next=leftList;
                leftList=leftList.next;
            }else{
                p.next=rightList;
                rightList=rightList.next;
            }
            p=p.next;
        }
        if (leftList!=null){
            p.next=leftList;
        }
        if (rightList!=null){
            p.next=rightList;
        }
        return dump.next;
    }

    // 1 2 3
    public static void main(String[] args) {
        ListNode head = new ListNode(7);
        ListNode headNext=new ListNode(4);
        ListNode headNextNext = new ListNode(8);
        head.next=headNext;
        headNext.next=headNextNext;
        listQuickSort(head,null);
        List<Integer> list = printListNode(head);
        System.out.println(list);
    }
}
