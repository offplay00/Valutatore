/*la nostra grammatica LL(1) è formata da
E --> (,NUM     case 0
E'--> +,-            1
E'--> EOF,+,-,),     2
T--> (,NUM           3
T'--> *,/            4
T'--> EOF,),+,-,*,/  5
F--> (,NUM           6
*/

import java.io.*;


public class Parser extends Lexer {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;



    public Parser(Lexer l, BufferedReader br) {
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
      expr();
	    match(Tag.EOF);
}


    private void expr(){ // ---> E
      switch(look.tag){

      case '(':
        match('(');
        term();
        exprp();
        break;

      case Tag.NUM:
        match(Tag.NUM);
        term();
        exprp();
        break;

      default:
      term();
      exprp();
      break;
    }

}

    private void exprp() { // ---> E'
	switch (look.tag) {

	case '+':
	match('+');
  term();
  exprp();
  break;

  case '-':
  match('-');
  term();
  exprp();
  break;

  case ')':
  match(')');
  break;

	}
    }

    private void term() { // ----> T
        switch(look.tag){
        case '(':
          match('(');
          fact();
          termp();
          break;

        case Tag.NUM:
          match(Tag.NUM);
          fact();
          termp();
          break;

        default:
        fact();
        termp();
        break;
        }

    }

    private void termp() { //---> T'
    switch (look.tag) {

    case '*':
    match('*');
    fact();
    termp();
    break;

    case '/':
    match('/');
    fact();
    termp();
    break;

    case '+':
    match('+');
    break;

    case '-':
    match('-');
    break;

    case ')':
    match(')');
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
        String path = "C:/Users/simon/Desktop/info/LFT-Lab/Esercizi/Analisi Sintattica/bo.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.start();
            System.out.print("Input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
