package probs.solutions;

public class FiniteAutomata {


    public static void main(String[] args) {
        String text = "abfabababA";
        int len = text.length();
        int[] sample = new int[256];

        for (int i=0; i< len;i++) {
            char x = text.charAt(i);
            //System.out.println("x = " + x + "  " + " index = "+ (x-'A') + " Sample =  " + sample[x-'A']);
            sample[x-'A'] = sample[x-'A']+1;
        }

        for(int j=0; j<sample.length;j++) {
            if (sample[j] > 0) {
                System.out.println((char)(j+'A'));
            }
        }
    }
}
