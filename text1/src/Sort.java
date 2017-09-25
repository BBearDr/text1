import java.util.Arrays;
import java.util.List;

/**
 * <p>Title:Sort</p>
 * <p>Description:八种排序总结
 * <p>Company:</p>
 * author:chenjx
 * date:2017年3月7日
 */
public class Sort {
	public static void main(String[] args) {
		int[] a = {11,3,10,5,7,2,4,9,6};
/*		Sort s = new Sort();
		s.QuickSort(a,0,a.length-1);*/
        ShellSort shellSort = new ShellSort();
        shellSort.ShellInsertSort(a);
	}
	private void exchange(int[] A,int a,int b){

		int temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}
	/**
	 * 冒泡排序，每一次冒出来的都是最大的值，即最大值最先被判断出来
	 * param list
	 */
	private void InsertSort(int[] list){
		for(int i=0 ;i<list.length-1;i++){
			for(int j=0;j<list.length-1-i;j++){
				if(list[j]>list[j+1]){ //后一个数字小于前一个数字
					exchange(list,j,j+1);
				}
			}
			System.out.println(Arrays.toString(list));
		}
	}

	/**
	 * 鸡尾酒排序(冒泡排序的升级版)，从前往后找出最大值，从后往前找出最小值
	 */
	void InsertSort2(int[] list){
		int left = 0;
		int right = list.length - 1;
		while(left < right){
			for(int i=left ; i<right ; i++){
				if(list[i] >list[i+1]){
					exchange(list,i,i+1);
				}
			}
			right--;
			for(int j=right; j>left;j--){
				if(list[j]<list[j-1]){
					exchange(list,j-1,j);
				}
			}
			left++;
			System.out.println(Arrays.toString(list));
		}
	}
	/**
	 * 选择排序，在未选出的序列中选出最小的值排在已排序列的末尾
	 * param list
	 */
	void selectSort(int[] list){
		int min;
		for(int i=0;i<list.length-1;i++){
			min = i;
			for(int j=i+1;j<list.length;j++){
				if(list[j]<list[min]){
					min = j;
				}
			}
			if(min!=i){
				exchange(list,min,i);
			}
			System.out.println(Arrays.toString(list));
		}
	}

	/**
	 * 插入排序1--普通(直接)插入，扑克插牌，
	 * 将要插入的牌与已排序的牌进行对比，符合插入位置上的牌依次向后挪，是稳定的
     * 时间复杂度：O（n^2）
	 */
	void InsertSort3(int[] list){
		for(int i=1;i<list.length;i++){
			int a = list[i];//要比对的数字
			int j = i-1;
			while(j>=0 && a<list[j] ){
				list[j+1] = list[j];
				j--;
			}
			list[j+1] =  a;
			System.out.println(Arrays.toString(list));
		}
	}
	/**
	 * Decription:Sort 快速排序：挖坑排序+分治法，依次对左右两边的数字进行排序
	 * 以一个数为基准，从有向左找出大于基准的值，从左向右找出小于基准的值，当左右两边的值相等时，将基准值赋予此下标的值
	 * 而后递归，基准左边的，基准右边的再依次进行上述排序
	 * Author:cjx
	 * Date: 2017/5/9 10:06
	 * param:  * @param null
	 */
	private void QuickSort(int[] list,int l, int r){
		if (l < r) {
			int i = l, j = r, x = list[l];
			while (i < j) {
				while (i < j && list[j] >= x) // 从右向左找第一个小于x的数
					j--;
				if (i < j)
					list[i++] = list[j];

				while (i < j && list[i] < x) // 从左向右找第一个大于等于x的数
					i++;
				if (i < j)
					list[j--] = list[i];
			}
			list[i] = x;
			QuickSort(list, l, i - 1); // 递归调用
			QuickSort(list, i + 1, r);
		}
		System.out.println(Arrays.toString(list));
	}
}
/**
 * 插入排序2--希尔排序
 * 将一数组等分成多份,然后将每一份的头尾进行比较交换,循环进行,下一次进行时,
 * 将原来的等分量进行缩减,直至只剩下长度为1时,结束
 */
class ShellSort{
    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
     void ShellInsertSort(int[] arr) {
         for (int gap = arr.length / 2; gap > 0; gap /= 2) {
             for (int i = gap; i < arr.length; i++) {
                 int j = i;
                 while (j - gap >= 0 && arr[j] < arr[j - gap]) {
                        swap(arr,j,j-gap);
                        j-=gap;
                 }
                 System.out.println(Arrays.toString(arr));
             }
         }
    }
}
