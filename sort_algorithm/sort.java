package sort_algorithm;

import java.util.Arrays;

public class sort {
    //进行数据交换
    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    //打印数组
    public static void printArray(int[] nums){
        System.out.println(Arrays.toString(nums));
    }
    //排序算法
    //冒泡排序-传入一个待排序数组
    //7 4 8 9
    public static void fun_1(int[] nums){
        int n = nums.length;
        for (int i=0;i<n-1;i++){
            for (int j=0;j<n-i-1;j++){
                //进行数据交换
                if (nums[j]>nums[j+1]){
                    swap(nums, j, j+1);
                }
            }
        }
    }
    //选择排序，每次选择最小的放入最前面
    public static void fun_2(int[] nums){
        int n = nums.length;
        for (int i=0;i<n;i++){
            //找出下标最小的
            int minIndex = i;
            for (int j=i;j<n;j++){
                if (nums[j]<nums[i]){
                    minIndex=j;
                }
            }
            //最小值有变动就进行交换
            if (minIndex!=i){
                swap(nums,minIndex,i);
            }
        }
    }
    //7 4
    //直接插入排序 初始第一个元素是有序的
    public static void fun_3(int[] nums){
        int n = nums.length;
        for (int i=1;i<n;i++){
            int value = nums[i];
            //从后往前进行遍历
            //记住这个j要放出来 j=i-1
            int j=i-1;
            for (;j>=0;j--){
                //将元素往后移动
                if (value<nums[j]){
                    nums[j+1]=nums[j];
                }else{
                    break;
                }
            }
            //将元素存入进来
            nums[j+1]=value;
        }
    }

    //希尔排序-将元素按照间隔进行排序
    public static void fun_4(int[] nums){
        int n = nums.length;
        //按照步长进行切割
        for (int step = n/2;step>0;step=step/2){
            //再进行希尔排序
            for (int i=step;i<n;i++){
                int value = nums[i];
                //开始进行步长的插入
                int j=i-step;
                for (;j>=0;j-=step){
                    if (value<nums[j]){
                        //将数据往后进行移动
                        nums[j+step]=nums[j];
                    }else{
                        //找到要存入的位置就进行退出
                        break;
                    }
                }
                //将最后的数据存入
                nums[j+step]=value;
            }
        }
    }

    //快速排序-进行快速排序
    public static void fun_5(int[] nums, int left, int right){
        //递归的出口
        if (left>=right){
            return;
        }
        //找出中间点
        int partition = getPartition(nums, left, right);
        fun_5(nums,left,partition-1);
        fun_5(nums,partition+1,right);
    }

    //7 4 8 9
    //4 7 8 9
    private static int getPartition(int[] nums, int left, int right) {
        int i=left;
        int j=right;
        int val = nums[left];
        while (i<=j){
            if (nums[i]<=val){
                i++;
            }else if (nums[j]>=val){
                j--;
            }else{
                //进行交换
                swap(nums,i,j);
                i++;
                j--;
            }
        }
        //将数据放入
        nums[left]=nums[j];
        nums[j]=val;
        return j;
    }

    //归并排序
    public static int[] mergeSort(int[] nums, int left, int right){
        int mid = (left+right)/2;
        mergeSort(nums,left,mid);
        mergeSort(nums, mid+1,right);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,4,8,9,3,2,4,4,2};
        fun_5(nums,0,nums.length-1);
        printArray(nums);
    }
}
