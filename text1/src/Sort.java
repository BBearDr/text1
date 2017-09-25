import java.util.Arrays;
import java.util.List;

/**
 * <p>Title:Sort</p>
 * <p>Description:���������ܽ�
 * <p>Company:</p>
 * author:chenjx
 * date:2017��3��7��
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
	 * ð������ÿһ��ð�����Ķ�������ֵ�������ֵ���ȱ��жϳ���
	 * param list
	 */
	private void InsertSort(int[] list){
		for(int i=0 ;i<list.length-1;i++){
			for(int j=0;j<list.length-1-i;j++){
				if(list[j]>list[j+1]){ //��һ������С��ǰһ������
					exchange(list,j,j+1);
				}
			}
			System.out.println(Arrays.toString(list));
		}
	}

	/**
	 * ��β������(ð�������������)����ǰ�����ҳ����ֵ���Ӻ���ǰ�ҳ���Сֵ
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
	 * ѡ��������δѡ����������ѡ����С��ֵ�����������е�ĩβ
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
	 * ��������1--��ͨ(ֱ��)���룬�˿˲��ƣ�
	 * ��Ҫ�����������������ƽ��жԱȣ����ϲ���λ���ϵ����������Ų�����ȶ���
     * ʱ�临�Ӷȣ�O��n^2��
	 */
	void InsertSort3(int[] list){
		for(int i=1;i<list.length;i++){
			int a = list[i];//Ҫ�ȶԵ�����
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
	 * Decription:Sort ���������ڿ�����+���η������ζ��������ߵ����ֽ�������
	 * ��һ����Ϊ��׼�����������ҳ����ڻ�׼��ֵ�����������ҳ�С�ڻ�׼��ֵ�����������ߵ�ֵ���ʱ������׼ֵ������±��ֵ
	 * ����ݹ飬��׼��ߵģ���׼�ұߵ������ν�����������
	 * Author:cjx
	 * Date: 2017/5/9 10:06
	 * param:  * @param null
	 */
	private void QuickSort(int[] list,int l, int r){
		if (l < r) {
			int i = l, j = r, x = list[l];
			while (i < j) {
				while (i < j && list[j] >= x) // ���������ҵ�һ��С��x����
					j--;
				if (i < j)
					list[i++] = list[j];

				while (i < j && list[i] < x) // ���������ҵ�һ�����ڵ���x����
					i++;
				if (i < j)
					list[j--] = list[i];
			}
			list[i] = x;
			QuickSort(list, l, i - 1); // �ݹ����
			QuickSort(list, i + 1, r);
		}
		System.out.println(Arrays.toString(list));
	}
}
/**
 * ��������2--ϣ������
 * ��һ����ȷֳɶ��,Ȼ��ÿһ�ݵ�ͷβ���бȽϽ���,ѭ������,��һ�ν���ʱ,
 * ��ԭ���ĵȷ�����������,ֱ��ֻʣ�³���Ϊ1ʱ,����
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
