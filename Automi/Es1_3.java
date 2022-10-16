public class Es1_3 {

  public static void main(String[] args) {
    String a = "1a";
    System.out.println(scan(a) ? "OK" : "NOPE");
  }

  public static boolean scan(String s)
  {
    int state = 0;
    int i = 0;
    boolean odd = false;
    int a = 0;

    while (state >= 0 && i < s.length()) {
    final char ch = s.charAt(i++);

    if(Character.isDigit(ch)) {
      a = ch;
      if(a%2==0)
        odd = true;
    }

    switch (state) {
    case 0:
      if (Character.isDigit(ch))
        state = 1;
      else
        state = -1;
        break;

    case 1:
      if (Character.isDigit(ch))
        state = 1;
      else if (Character.isLetter(ch) && odd == true) {
          // a = ch.toLowerCase();
        if(lowerCase(ch) >= 97 && lowerCase(ch) <= 107) {
          state = 2;
          }
          else
            state = -1;
        }
      else if (Character.isLetter(ch) && odd == false) {
        // a = ch.toLowerCase();
        if(lowerCase(ch) >= 108 && lowerCase(ch) <= 122) {
          state = 2;
        }
      }
      else
        state = -1;
      break;

    case 2:
      if (Character.isLetter(ch))
        state = 2;
      else
        state = -1;
      break;
    }
  }
  return state == 2;
  }



  public static int lowerCase(char a) {
    int c = a;
    if(c >= 65 || c <= 90) {
      c = c + 32;
    }
    return c;
  }
}
