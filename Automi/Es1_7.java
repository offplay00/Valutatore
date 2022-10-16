public class Es1_7 {

  public static boolean scan(String s) {

    int state = 0;
    int i = 0;
    boolean couple = false;
    String L_1 = "Ss";
    String L_2 = "Ii";
    String L_3 = "Mm";
    String L_4 = "Oo";
    String L_5 = "Nn";
    String L_6 = "Ee";

    while(state >= 0 && i < s.length()) {
      final char ch = s.charAt(i++);
      switch (state) {
        case 0 :
          if(Character.isLetter(ch)) {
            if(L_1.indexOf(ch) >=  0) {
              state = 1;
            } else if(L_1.indexOf(ch) < 0 && couple == false) {
              state  = 1;
              couple = true;
            } else
                state = -1;
              break;
          }
          case 1:
          if(Character.isLetter(ch)) {
            if(L_2.indexOf(ch) >= 0) {
              state = 2;
            } else if(L_2.indexOf(ch) < 0 && couple == false) {
              state  = 2;
              couple = true;
            } else
                state = -1;
              break;
          }
          case 2:
          if(Character.isLetter(ch)) {
            if(L_3.indexOf(ch) >= 0) {
              state = 3;
            } else if(L_3.indexOf(ch) < 0 && couple == false) {
              state  = 3;
              couple = true;
            } else
                state = -1;
              break;
          }
          case 3:
          if(Character.isLetter(ch)) {
            if(L_4.indexOf(ch) >= 0) {
              state = 4;
            } else if(L_4.indexOf(ch) < 0 && couple == false) {
              state  = 4;
              couple = true;
            } else
                state = -1;
              break;
          }
          case 4:
          if(Character.isLetter(ch)) {
            if(L_5.indexOf(ch) >= 0) {
              state = 5;
            } else if(L_5.indexOf(ch) < 0 && couple == false) {
              state  = 5;
              couple = true;
            } else
                state = -1;
              break;
          }
          case 5:
          if(Character.isLetter(ch)) {
            if(L_6.indexOf(ch) >= 0) {
              state = 5;
            } else if(L_6.indexOf(ch) < 0 && couple == false) {
              state  = 5;
              couple = true;
            } else
                state = -1;
              break;
          }
      }
    }
    return state == 5;
  }

  public static void main(String[] args) {
    String a = "Simope"; //OK
    String b = "Eva";    //NOPE
    String c = "ISmone"; //NOPE
    String d = "";       //NOPE

    System.out.println(scan(a) ? "OK" : "NOPE");
    System.out.println(scan(b) ? "OK" : "NOPE");
    System.out.println(scan(c) ? "OK" : "NOPE");
    System.out.println(scan(d) ? "OK" : "NOPE");
  }

}
