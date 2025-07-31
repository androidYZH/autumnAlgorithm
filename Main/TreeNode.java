package Main;

public class TreeNode<T> {
    TreeNode left;
    TreeNode right;
    T data;
    public TreeNode(){}
    public TreeNode(T data){
        this.data = data;
    }
    public TreeNode(T data, TreeNode left, TreeNode right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
