/**
 * Decription:递归，将三个字符颠倒顺序后输出
 * Author:cjx
 * Date: 2017/6/15 9:17
 * param:  * @param null
 */
public class RecursionDemo {
    public static void main(String[] args)
    {
        //使用递归完成全排列
        char[] source=new char[]{'A','B','C'};
        char[] result=new char[source.length];
        allPermutation(0,source,result);

    }
    /**
     *
     * @param index当前考虑的数的下标(从0开始)
     * @param source
     * @param result
     */
    public static void allPermutation(int index,char[] source,char[] result){
        //当源数据中只有一个字符时，将该字符加入结果数组，并输出
        if(source.length==1){
            result[index]=source[0];
            show(result);
            return ;
        }

        for(int i=0;i<result.length-index;i++){
            result[index]=source[i];
            char[] newSource=getNewSource(source,source[i]);
            allPermutation(index+1, newSource,result);
        }
    }
    public static void show(char[] result){
        System.out.println(result);
    }
    /**
     * 生成去掉指定字符的新源数据数组
     * @param source 原来的源数据数组
     * @param c 指定去掉的字符
     */
    public static char[] getNewSource(char[] source,char c){
        char[] newSource=new char[source.length-1];
        for(int i=0,j=0;i<source.length;i++){
            if(source[i]!=c){
                newSource[j]=source[i];
                j++;
            }
        }
        return newSource;
    }
}
