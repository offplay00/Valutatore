public class Es1_10 {
  public static boolean scan(String s) {
  int state = 0;
  int i = 0;
  while (state >= 0 && i < s.length()) {
    final char ch = s.charAt(i++);
    switch (state) {
      case 0:
        if(ch == '/')
          state = 1;
        else if(ch =='a' || ch == '*')
          state = 0;
        else
          state = -1;
        break;

        case 1:
          if(ch == '*')
            state = 2;
          else if(ch == 'a')
            state = 0;
          else
            state = -1;
          break;

        case 2:
          if(ch == 'a' || ch == '/')
            state = 2;
          else if(ch == '*')
            state = 3;
          else
            state = -1;
          break;

        case 3:
          if(ch == 'a')
            state = 2;
          else if(ch == '*')
            state = 3;
          else if(ch == '/')
            state = 0;
          else
            state = -1;
    }
  }
  return state == 0 || state == 1 ;
 }

  public static void main(String[] args) {
      String a = "aaa/****/aa"; //OK
      String b = "aa/*a*a*/"; //OK
      String c = "aaaa"; //OK
      String d = "/****/"; //OK
      String e = "/*aa*/"; //OK
      String f = "*/a"; //OK
      String g = "a/**/***a"; //OK
      String h = "a/**/***/a"; //OK
      String j = "a/**/aa/***/a"; //OK
      String k = "aaa/*/aa"; //NOPE
      String l = "a/**//***a"; //NOPE
      String m = "aa/*aa"; //NOPE
      System.out.println(scan(a) ? "OK" : "NOPE");
      System.out.println(scan(b) ? "OK" : "NOPE");
      System.out.println(scan(b) ? "OK" : "NOPE");
      System.out.println(scan(c) ? "OK" : "NOPE");
      System.out.println(scan(d) ? "OK" : "NOPE");
      System.out.println(scan(e) ? "OK" : "NOPE");
      System.out.println(scan(f) ? "OK" : "NOPE");
      System.out.println(scan(g) ? "OK" : "NOPE");
      System.out.println(scan(h) ? "OK" : "NOPE");
      System.out.println(scan(j) ? "OK" : "NOPE");
      System.out.println(scan(k) ? "OK" : "NOPE");
      System.out.println(scan(l) ? "OK" : "NOPE");
      System.out.println(scan(m) ? "OK" : "NOPE");
  }
}
