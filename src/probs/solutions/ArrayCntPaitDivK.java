package probs.solutions;

public class ArrayCntPaitDivK {


  public static void main(String[] args) {
    int arr1[] = {2, 2, 1, 7, 5, 3};
    int k = 4, count = 0;
    for (int i = 0; i < arr1.length; i++) {
      for (int j = i + 1; j < arr1.length; j++) {
        if ((arr1[i] + arr1[j]) % k == 0) {
          count++;
        }
      }
    }
    System.out.println("count:" + count);
  }


}
