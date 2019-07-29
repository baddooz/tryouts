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
      // converting to charArray gives better performance
      char[] inputCharArray = inputStr.toCharArray();
      char[] patternCharArray = wildCardPatternStr.toCharArray();
      int patternIndex = 0;
      int inputStrIndex = 0;
      boolean hasAsteriskEncountered = false;
      char patternElement;
      char inputStrElement;
      while (inputStrIndex < len && patternIndex < patternLen) {
        patternElement = patternCharArray[patternIndex];
        inputStrElement = inputCharArray[inputStrIndex];
        if (patternElement == '?') {
          // advance pattern string element position
          hasAsteriskEncountered = false;
          patternIndex = incrementToLimit(patternIndex, patternLen);
        } else if (patternElement == '*') {
          //remember that asterisk was encountered so that inputstr position can be advanced until
          hasAsteriskEncountered = true;
          // advance pattern string element position
          patternIndex = incrementToLimit(patternIndex, patternLen);
          // No * is encovuntered but pattern char && inputstr chars do not match
        } else if (!hasAsteriskEncountered && patternElement != inputStrElement) {
          hasMatched = false;
          break;
          //if characters match
        } else if (patternElement == inputStrElement) {
          patternIndex = incrementToLimit(patternIndex, patternLen);
          hasMatched = hasMatched && true;
          //reset asterisk flag as characters matched
          hasAsteriskEncountered = false;
          //if inputstr exhausted and no match found
        } else if ((inputStrIndex + 1 >= len)) {
          hasMatched = false;
          break;
        }
        inputStrIndex = incrementToLimit(inputStrIndex, len);
      }
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
    return ((limit - counter) > 0 ? counter + 1 : counter);
  }

  public static void main(String[] atgs) {
    //String str = "abcdefg";
    String str = "ab";

    String[] pattern = {"a?c*M", "*bc*g", str, "?bcdefg", "ab*", "M*?", "ab", "*M", "a?c?*g",
        "a?c?*M"};
    String[] pattern1 = {"ab"};
    for (int i = 0; i < pattern.length; i++) {
      boolean patternMatch = WildcardMatcher.matches(str, pattern[i]);
      System.out.println("pattern = " + pattern[i] + " result = " + patternMatch);

    }
  }
}
