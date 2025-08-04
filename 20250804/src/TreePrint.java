import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//训练一下前序，中序，后序遍历
public class TreePrint {
    //前序遍历
    //    1
    // 2     3
    //4  5
    //前序遍历
    public static void print_1(TreeNode head){
        //走到最左边
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = head;
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()||p!=null){
            //走向最左边
            if (p!=null){
                //将数据加入进去
                res.add(p.data);
                //将p加入进去
                stack.push(p);
                p=p.left;
            }else{
                TreeNode node = stack.pop();
                p=node.right;
            }
        }
        System.out.println(res);
    }
    //中序遍历
    public static void print_2(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        List<Integer> res = new ArrayList<>();
        while (p!=null||!stack.isEmpty()){
            if (p!=null){
                stack.push(p);
                p=p.left;
            }else{
                //此时p==null
                TreeNode node = stack.pop();
                res.add(node.data);
                p=node.right;
            }
        }
        System.out.println(res);
    }

    //后序遍历
    public static void print_3(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        TreeNode cur = null;
        List<Integer> res = new ArrayList<>();
        while (p!=null||!stack.isEmpty()){
            if (p!=null){
                stack.push(p);
                p=p.left;
            }else{
                //试探上面的节点
                TreeNode peek = stack.peek();
                //不为null并且没有拜访过
                if (peek.right!=null&&cur!=peek.right){
                    p=peek.right;
                }else{
                    TreeNode node = stack.pop();
                    res.add(node.data);
                    cur=node;
                }
            }
        }
        System.out.println(res);
    }

    //层次遍历
    public static void print_4(TreeNode root){
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        TreeNode p = root;
        linkedList.offer(root);
        List<Integer> res = new ArrayList<>();
        while (!linkedList.isEmpty()){
            TreeNode poll = linkedList.poll();
            res.add(poll.data);
            if (poll.left!=null){
                linkedList.offer(poll.left);
            }
            if (poll.right!=null){
                linkedList.offer(poll.right);
            }
        }
        System.out.println(res);
    }
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left=new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        print_4(head);
    }

}
