import java.io.*;
import java.util.*;
import java.lang.Integer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;





public class maxsubarray{
  public static class answer{
    public int low;
    public int high;
    public int max_sum;

    public answer(int low, int high, int max_sum){
      this.low = low;
      this.high = high;
      this.max_sum = max_sum;
    }

  }

  public static answer bruteforce(int[] arr){
    int low = -1;
    int high = -1;
    int max_sum = Integer.MIN_VALUE;
    for (int i=0; i < arr.length ; i++){
      int curr_sum = 0;
      for (int j = i; j < arr.length; j++){
        curr_sum += arr[j];
        if (curr_sum > max_sum) {
          max_sum = curr_sum;
          low = i;
          high = j;
        }
      }
    }
    answer a = new answer(low, high, max_sum);
    return a;
  }

  public static answer crossingsub(int[] arr, int low, int mid, int high){
    int max_left = 0;
    int max_right = 0;
    int left_sum = Integer.MIN_VALUE;
    int sum = 0;
    for (int i = mid; i >= low; i--) {
      sum = sum + arr[i];
      if (sum > left_sum)
        left_sum = sum;
        max_left = i;
        }
    sum = 0;
    int right_sum = Integer.MIN_VALUE;
    for (int j = mid + 1; j <= high; j++){
      sum = sum + arr[j];
      if (sum > right_sum)
        right_sum = sum;
        max_right = j;
       }
    int total = left_sum + right_sum;
    answer a = new answer(max_left, max_right, total);
    return a;
  }

  public static answer fmaxsubarray(int[] arr, int low, int high){
    if (high == low){
      answer a = new answer(low, high, arr[low]);
      return a;
    }
    else{
      int mid = (low + high)/2;
      answer left = fmaxsubarray(arr, low, mid);
      answer right = fmaxsubarray(arr, mid+1, high);
      answer cross = crossingsub(arr, low, mid, high);
      if (left.max_sum >= right.max_sum && left.max_sum >= cross.max_sum){
        answer a = new answer(left.low, left.high, left.max_sum);
        return a;
      }
      else if (right.max_sum >= left.max_sum && right.max_sum >= cross.max_sum){
        answer a = new answer(right.low, right.high, right.max_sum);
        return a;
      }
      else{
        answer a = new answer(cross.low, cross.high, cross.max_sum);
        return a;
      }
      }
    }
    // public static int getRandomNumberInRange(int min, int max) {
    //
    // 		if (min >= max) {
    // 			throw new IllegalArgumentException("max must be greater than min");
    // 		}
    //
    // 		Random r = new Random();
    // 		return r.nextInt((max - min) + 1) + min;
    // 	}



  public static void main(String[] args) {
    int[] a = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
    List alist = new ArrayList();

    int idx = 0;
    for (int i = 5; i < 251; i+=5 ) {
      int[] x = new int[i];
      for (int j = 0; j < i; j++){
        int y = ThreadLocalRandom.current().nextInt(min, max + 1);
        x[j] = y;
      }
      alist.add(x);


    }









    long startTime = System.nanoTime();
    answer ans = bruteforce(a);
    long endTime = System.nanoTime();
    long timeElapsed = endTime - startTime;

    long startTime1 = System.nanoTime();
    answer ans1 = fmaxsubarray(a, 0, a.length-1);
    long endTime1 = System.nanoTime();
    long timeElapsed1 = endTime1 - startTime1;




    System.out.println(ans.max_sum);
    System.out.println(ans1.max_sum);
    System.out.println(timeElapsed);
    System.out.println(timeElapsed1);



  }





}
