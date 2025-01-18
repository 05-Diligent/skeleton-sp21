package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }
    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
       AList<Integer> Ns = new AList<>();
       AList<Double> times = new AList<>();
       AList<Integer> opCount = new AList<>();
       for(int N = 1000; N <= 128000; N *= 2) {
           Ns.addLast(N);
           opCount.addLast(N);
           Stopwatch sw = new Stopwatch();//开始记录时间
           //实现过程，创建需要的时间
           AList<Integer> testList = new AList<>();
           for(int i = 0; i < N; i++) {
               testList.addLast(i);
           }
           //记录结束时间与开始时间之间的时间
           double timeInSeconds = sw.elapsedTime();
           //把这个时间记录在time数组中
           times.addLast(timeInSeconds);
       }
       printTimingTable(Ns,times,opCount);
    }

}
