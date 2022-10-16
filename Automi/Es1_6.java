public class Es1_6 {

  public static boolean scan(String s) {

    int state = 0;
    int i = 0;
    boolean last = false;
    boolean before = false;
    String O = "ABCDEFGHIJK";
    String E = "LMNOPQRSTUVWXYZ";

    while (state >= 0 && i < s.length()) {
      final char ch = s.charAt(i++);
      switch (state) {

        case 0:
          if (Character.isDigit(ch)) {
            if (odd(ch) == true) {
              state = 1;
              before = true;
            } else if(odd(ch) == false) {
              state = 2;
              before = false;
            }
          } else
            state = -1;
          break;

        case 1:
          if (Character.isDigit(ch)) {
            if (odd(ch) == true) {
              state = 3;
              last = true;
            } else if (odd(ch) == false) {
              state = 4;
              last = false;
            }
          } else
            state = -1;
          break;

        case 2:
          if (Character.isDigit(ch)) {
            if (odd(ch) == true) {
              state = 3;
              last = true;
            } else if (odd(ch) == false) {
              state = 4;
              last = false;
            }
          }

        case 3:  //ultima cifra pari
          if(Character.isDigit(ch)) {
            if (odd(ch) == true) {
              state = 3;
              before = last;
              last = true;
            } else if (odd(ch) == false) {
              state = 4;
              before = last;
              last = false;
            }
          } else if(Character.isLetter(ch)) {
              if(O.indexOf(ch) >= 0) {
                if(before == true) {
                  state = 5; //T2
                } else
                    state = 6; //T1
              }
            } else
                state = -1;
          break;

        case 4: //ultima cifra dispari
          if(Character.isDigit(ch)) {
            if (odd(ch)) {
              state = 3;
              before = last;
              last = true;
            } else if (odd(ch) == false) {
              state = 4;
              before = last;
              last = false;
            }
          } else if(Character.isLetter(ch)) {
            if(E.indexOf(ch) >= 0) {
              if(before == true) {
                state = 7; //T4
              } else
                state = 8; //T3
            }
          } else
            state = -1;
          break;

        case 5:
          if(Character.isLetter(ch))
            state = 5;
          else
            state = -1;
          break;

        case 6:
          if(Character.isLetter(ch))
            state = 6;
          else
            state = -1;
          break;

        case 7:
          if(Character.isLetter(ch))
            state = 7;
          else
            state = -1;
          break;

        case 8:
          if(Character.isLetter(ch))
            state = 8;
          else
            state = -1;
          break;
      }
    }
    if(state == 5)
      System.out.print("T2-");
    if(state == 6)
      System.out.print("T1-");
    if(state == 7)
      System.out.print("T4-");
    if(state == 8)
      System.out.print("T3-");

    return state == 5 || state == 6 || state == 7 || state == 8;
  }

  public static void main(String[] args) {
    String a = "3133Pessio";  //T3
    String b = "3134Apessio"; //T1
    String c = "3125Ppessio"; //T4
    String d = "3144Apessio"; //T2

    System.out.println((scan(a) ? "OK" : "NOPE" ));
    System.out.println((scan(b) ? "OK" : "NOPE" ));
    System.out.println((scan(c) ? "OK" : "NOPE" ));
    System.out.println((scan(d) ? "OK" : "NOPE" ));
  }

  public static boolean odd(char c) {
      if (c % 2 == 0)
        return true;
      else
        return false;
  }
}
