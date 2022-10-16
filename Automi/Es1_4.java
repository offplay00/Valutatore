public class Es1_4 {

  public static boolean scan(String s) {
    int state = 0;
    int i = 0;
    boolean odd = false,nmn = false;
    int a = 0;

    while (state >= 0 && i < s.length()) {
    final char ch = s.charAt(i++);

    if(Character.isDigit(ch)) {
      if((int)ch%2==0)
        odd = true;
    }

    switch (state) {
    case 0:
      if (Character.isDigit(ch) && odd == true)
        state = 1;
      else if(Character.isDigit(ch) && odd == false)
        state = 2;
      else
        state = -1
      break;

    case 1:
      if (Character.isDigit(ch) && odd == true && nmn == false)
        state = 1;
      else if(Character.isDigit(ch) && odd == false && nmn == false)
        state = 2;
      else if (Character.isLetter(ch)) {
        if(ch >= 65 && ch <= 75)
          state = 3;
      }else if(ch == ' ') {
        state = 1;
        nmn = true;
      }
      else
        state = -1;
      break;

      case 2:
        if (Character.isDigit(ch) && odd == true && nmn == false)
          state = 1;
        else if(Character.isDigit(ch) && odd == false && nmn == false)
          state = 2;
        else if (Character.isLetter(ch)) {
          if(ch >= 76 && ch <= 90)
            state = 3;
        } else if(ch == ' ') {
          state = 2;
          nmn = true;
        }
        else
          state = -1;
        break;

    case 3:
      if (ch >= 97 && ch <= 122)
        state = 3;
      else if(ch == ' ')
        state = 4;
      else
        state = -1;
      break;

    case 4:
      if(ch == ' ')
        state = 4;
      else if(ch >= 65 && ch <= 90)
        state 3;
      else
        state = -1;
      break;
    }
  }
  return state == 3;
  }
}

public static void main(String[] args) {
  String a = "1a";
  System.out.println(scan(a) ? "OK" : "NOPE");
}
