public class Es1_8 {

  public static boolean scan(String s) {
    int state = 0;
    int i = 0;
    boolean couple = false;

    while(state >= 0 && i < s.length()) {
      final char ch = s.charAt(i++);
      switch (state) {

        case 0:
          if(ch == '+' || ch == '-')
            state = 1;
          else if(ch == ',' || ch == '.')
            state = 2;
          else if(Character.isDigit(ch))
            state = 3;
          else
            state = -1;
          break;

        case 1:
          if(Character.isDigit(ch))
            state = 4;
          else if(ch == '.' || ch == ',')
            state = 2;
          else
            state = -1;
          break;

        case 2:
          if(Character.isDigit(ch))
            state = 4;
          else
            state = -1;
          break;

        case 3:
          if(Character.isDigit(ch))
            state = 4;
          else if(ch == ',' || ch =='.')
            state = 2;
          else if(ch == 'e')
            state = 5;
          else
            state = -1;
          break;

        case 4:
          if(Character.isDigit(ch))
            state = 4;
          else if(ch == 'e')
            state = 5;
          else if(ch == ',' || ch == '.')
            state = 2;
          else
            state = -1;
          break;

          case 5:
            if(ch == '+' || ch == '-')
              state = 6;
            else if(ch == ',' || ch == '.')
              state = 7;
            else if(Character.isDigit(ch))
              state = 8;
            else
              state = -1;
            break;

          case 6:
            if(Character.isDigit(ch))
              state = 9;
            else if(ch == ',' || ch == '.')
              state = 7;
            else
              state = -1;
            break;

          case 7:
            if(Character.isDigit(ch))
              state = 9;
            else
              state = -1;
            break;

          case 8:
            if(Character.isDigit(ch))
              state = 9;
            else if(ch == ',' || ch =='.')
              state = 6;
            else
             state = -1;
            break;

          case 9:
            if(Character.isDigit(ch))
              state = 9;
            else if(ch == ',' || ch == '.')
              state = 7;
            else
              state = -1;
      }
    }
    return state == 3 || state == 4 || state == 8 || state == 9;
  }

  public static void main(String[] args) {
    String a = ".";
    String b = "e3";
    String c = "123.";
    String d = "+e6";
    String e = "1.2.3";
    String f = "4e5e6";
    String g = "++3";
    String h = "-.7e2";
    String i = "1e2.3";

    System.out.println(scan(a) ? "OK" : "NOPE");
    System.out.println(scan(b) ? "OK" : "NOPE");
    System.out.println(scan(c) ? "OK" : "NOPE");
    System.out.println(scan(d) ? "OK" : "NOPE");
    System.out.println(scan(e) ? "OK" : "NOPE");
    System.out.println(scan(f) ? "OK" : "NOPE");
    System.out.println(scan(g) ? "OK" : "NOPE");
    System.out.println(scan(h) ? "OK" : "NOPE");
    System.out.println(scan(i) ? "OK" : "NOPE");
  }
}
