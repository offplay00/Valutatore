public class Es1_5 {

  public static boolean scan(String s) {
    int state = 0;
    int i = 0;
    boolean odd=true;
    boolean fin=true;
    int a = 0;

    while (state >= 0 && i < s.length()) {
    final char ch = s.charAt(i++);
    switch(state) {
      case 0:
        if(ch >= 65 && ch <= 75) {
          odd = true;
          state = 1;
        }
        else if(ch >= 76 && ch <= 90) {
          odd = false;
          state = 1;
        }
        else
          state = -1;
        break;

      case 1:
        if(ch >= 97 && ch <= 122)
          state = 2;
        else
          state = -1;
        break;

      case 2:
      if(ch >= 97 && ch <= 122)
        state = 2;
      else if(Character.isDigit(ch))
        state = 3;
      else
        state = -1;
      break;

      case 3:
        if(Character.isDigit(ch)) {
          if(ch%2 == 0 )
            fin = true;
          else
            fin = false;
          state = 3;
        }
        else
          state = -1;
        break;
    }
  }
  return state == 3 && fin == odd;
  }

  public static void main(String[] args) {
    String a = "Bianchi123456"; //OK
    String b = "Rossi654321"; //OK
    String c = "123456Bianchi"; //NOPE
    String d = "654321Rossi"; //NOPE
    System.out.println(scan(a) ? "OK" : "NOPE");
    System.out.println(scan(b) ? "OK" : "NOPE");
    System.out.println(scan(c) ? "OK" : "NOPE");
    System.out.println(scan(d) ? "OK" : "NOPE");
  }
}
