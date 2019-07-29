package probs.solutions;

public class LookNSaySequence {

  public static void genSeqNthRow(String sequence, int row) {

    String setSequence = sequence;
    while (row > 0) {
      System.out.println("sequence =  " + setSequence);
      setSequence = genNextSequence(setSequence);
      row--;
    }


  }

  public static String genNextSequence(String sequence) {

    StringBuilder nextSeq = new StringBuilder();

    char[] seqCharArray = sequence.toCharArray();
    int seqCharArrayLen = seqCharArray.length;
    char preecedingChar = sequence.charAt(0);
    int prevRowSeqCount = 0;
    int index = 0;

    do {
      if (preecedingChar == seqCharArray[index]) {
        prevRowSeqCount++;
      } else {
        nextSeq.append(prevRowSeqCount).append(preecedingChar);
        preecedingChar = seqCharArray[index];
        prevRowSeqCount = 1;
      }
      index++;
    } while (index < seqCharArrayLen);

    nextSeq.append(prevRowSeqCount).append(preecedingChar);
    return nextSeq.toString();
  }


  public static void main(String[] args) {

    LookNSaySequence.genSeqNthRow("1", 7);


  }

}
