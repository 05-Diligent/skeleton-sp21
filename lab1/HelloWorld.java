public class HelloWorld {
    public static int max(int[] m) {

        int max=m[0];
        for(int i=1;i<m.length;i++)
        {
            if(max<m[i])
            {
                max=m[i];
            }
        }


        return max;
    }
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        int num=max(numbers);
    }
}