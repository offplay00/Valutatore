import java.io.*;
public class Parser2 extends Lexer {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Parser2(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }

    void move() {
        look = lex.lexical_scan(pbr);
        System.out.println("token = " + look);
    }

    void error(String s) {
	throw new Error("near line " + lex.line + ": " + s);
    }

    void match(int t) {
	     if (look.tag == t) {
	        if (look.tag != Tag.EOF) move();
	     } else error("syntax error");
     }

    public void start() {
      prog();
	    match(Tag.EOF);
}


    private void prog() {
      statlist();
      match(Tag.EOF);
    }

    private void statlist() {
      stat();
      statlistp();
    }

    private void statlistp() {
      switch(look.tag) {
        case ';':
          match(';');
          stat();
          statlistp();
          break;
        case ' ':
          match(' ');
          break;

        default:
          break;
      }
	  }

    private void stat() {
        switch(look.tag) {
          case Tag.ASSIGN:
            match(Tag.ASSIGN);
            expr();
            match(Tag.TO);
            idlist();
            break;

          case Tag.PRINT:
            match(Tag.PRINT);
            match('(');
            expr();
            match(')');
            break;

          case Tag.READ:
            match(Tag.READ);
            match('(');
            expr();
            match(')');
            break;

          case Tag.WHILE:
            match(Tag.WHILE);
            match('(');
            bexpr();
            match(')');
            stat();
            break;

          case Tag.IF:
            match(Tag.IF);
            match('(');
            bexpr();
            match(')');
            stat();
            stat2();
            break;

          case '{':
            match('{');
            statlist();
            match('}');
            break;

          default:
            break;
        }

    }

    private void stat2() {
      switch(look.tag) {
        case Tag.END:
          match(Tag.END);
          break;

        case Tag.ELSE:
          stat();
          match(Tag.END);
          break;

        default:
          break;
      }
    }

    private void idlist() {
      match(Tag.ID);
      idlistp();
    }

    private void idlistp() {
      switch(look.tag) {
        case ',':
          match(',');
          match(Tag.ID);
          idlistp();
          break;

        case ' ':
          match(' ');
          break;

        default:
          break;
      }
    }

      private void bexpr() {
        match(Tag.RELOP);
        expr();
        expr();
      }

      private void expr() {
        switch(look.tag) {
          case '+':
            match('+');
            match('(');
            exprlist();
            match(')');
            break;

          case '-':
            match('-');
            expr();
            expr();
            break;

          case '*':
            match('*');
            match('(');
            exprlist();
            match(')');
            break;

          case '/':
            match('/');
            expr();
            expr();
            break;

          case(Tag.NUM):
            match(Tag.NUM);
            break;

          case(Tag.ID):
            match(Tag.ID);
            break;

          default:
            break;
        }
      }

      private void exprlist() {
        expr();
        exprlistp();
      }

      private void exprlistp() {
        switch(look.tag) {
          case ',':
            match(',');
            expr();
            exprlistp();
            break;

          case ' ':
            match(' ');
            break;

          default:
            break;
      }
    }

    private void fact() { //----> F
          switch(look.tag){

            case '(':
            match('(');
            expr();
            break;

            case Tag.NUM:
            match(Tag.NUM);
            break;
          }

    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "C:/Users/simon/Desktop/info/LFT-Lab/Esercizi/Analisi Sintattica/bo.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.start();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
