package probs.solutions;

public class WildcardMatcher {

  public static boolean matches(String inputStr, String wildCardPatternStr) {
    boolean hasMatched = true;
    if (isStringNull(inputStr) && isStringNull(wildCardPatternStr)) {
      hasMatched = true;
    } else if (isStringNull(wildCardPatternStr)) {
      hasMatched = true;
    } else {
      int len = inputStr.length();
      int patternLen = wildCardPatternStr.length();
      char[] inputCharArray = inputStr.toCharArray();
      char[] patternCharArray = wildCardPatternStr.toCharArray();
      int patternIndex = 0;
      int inputStrIndex = 0;
      boolean hasAstreikEncountered = false;
      char patternElement;
      char inputStrElement;
      while (inputStrIndex < len && patternIndex < patternLen) {
        patternElement = patternCharArray[patternIndex];
        inputStrElement = inputCharArray[inputStrIndex];
        if (patternElement == '?') {
          hasAstreikEncountered = false;
          patternIndex = incrementToLimit(patternIndex,patternLen);
        } else if (patternElement == '*') {
          hasAstreikEncountered = true;
          patternIndex = incrementToLimit(patternIndex,patternLen);
        } else if (!hasAstreikEncountered && patternElement != inputStrElement ){
          hasMatched = false;
          break;
        }else if (patternElement == inputStrElement) {
          patternIndex = incrementToLimit(patternIndex,patternLen);
          hasMatched = hasMatched && true;
          hasAstreikEncountered = false;
        } else if((inputStrIndex+1 >= len)) {
          hasMatched = false;
          break;
        }
        inputStrIndex = incrementToLimit(inputStrIndex,len);
      }
      System.out.println("inputStrIndex = " + inputStrIndex + "   patternIndex = " +patternIndex);

    }
    return hasMatched;
  }

  private static boolean isStringNull(String inputStr) {
    boolean isNull = true;
    if (inputStr != null && !inputStr.isEmpty()) {
      isNull = false;
    }
    return isNull;
  }

  private static int incrementToLimit(int counter, int limit) {
    if ((limit - counter) > 0) {
      return counter+1;
    }
    return counter;
  }

  public static void main(String[] atgs) {
    //String str = "abcdefg";
    String str = "ab";

    String[] pattern = {"a?c*M","*bc*g",str,"?bcdefg","ab*","M*?","ab", "*M","a?c?*g","a?c?*M"};
    String[] pattern1= {"ab"};
    for(int i=0;i<pattern.length;i++) {
      boolean patternMatch = WildcardMatcher.matches(str, pattern[i]);
      System.out.println("pattern = " + pattern[i] +" result = " + patternMatch);

    }
  }
}
