public class Es1_9 {
  public static boolean scan(String s) {
  int state = 0;
  int i = 0;
  while (state >= 0 && i < s.length()) {
    final char ch = s.charAt(i++);
    switch (state) {
      case 0:
        if(ch == '/')
          state = 1;
        else
          state = -1;
        break;

      case 1:
        if(ch == '*')
          state = 2;
        else
          state = -1;
        break;

      case 2:
        if(ch == 'a')
          state = 2;
        else if(ch == '*')
          state = 3;
        else if(ch == '/')
          state = 2;
        else
          state = -1;
        break;

      case 3:
        if(ch == 'a')
          state = 2;
        else if(ch == '*')
          state = 3;
        else if(ch == '/')
          state = 4;
        else
          state = -1;
        break;

      case 4:
        state = -1;
        break;

    }
  }
  return state == 4;
 }

  public static void main(String[] args) {
      String a = "/****/"; //OK
      String b = "/*a*a*/"; //OK
      String c = "/*a/**/"; //OK
      String d = "/**a///a/a**/"; //OK
      String e = "/**/"; //OK
      String f = "/*/*/"; //OK
      String g = "/*/"; //NOPE
      String h = "/**/***/"; //NOPE
      System.out.println(scan(a) ? "OK" : "NOPE");
      System.out.println(scan(b) ? "OK" : "NOPE");
      System.out.println(scan(b) ? "OK" : "NOPE");
      System.out.println(scan(c) ? "OK" : "NOPE");
      System.out.println(scan(d) ? "OK" : "NOPE");
      System.out.println(scan(e) ? "OK" : "NOPE");
      System.out.println(scan(f) ? "OK" : "NOPE");
      System.out.println(scan(g) ? "OK" : "NOPE");
      System.out.println(scan(h) ? "OK" : "NOPE");
  }
}
