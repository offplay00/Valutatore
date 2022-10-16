import java.io.*;
import java.util.*;

public class Lexer {

    public static int line = 1;
    private char peek = ' ';
    public boolean start = true;
    public boolean cmt = false;

    private void readch(BufferedReader br) {
        try {
            peek = (char) br.read();
        } catch (IOException exc) {
            peek = (char) -1; // ERROR
        }
    }

    public Token lexical_scan(BufferedReader br) {
        while (peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r') {
            if (peek == '\n')
                line++;
            readch(br);
            start = true;
        }

        switch (peek) {
            case '!':
                peek = ' ';
                return Token.not;

            case '(':
                peek = ' ';
                return Token.lpt;

            case ')':
                peek = ' ';
                return Token.rpt;

            case '{':
                peek = ' ';
                return Token.lpg;

            case '}':
                peek = ' ';
                return Token.rpg;

            case '+':
                peek = ' ';
                return Token.plus;

            case '-':
                peek = ' ';
                return Token.minus;

            case '*':
                peek = ' ';
                return Token.mult;

            case '/':
                readch(br);
                if(peek == '/') {
                    while (peek != '\n') {
                        readch(br);
                        if(peek == (char) -1)
                            return null;
                    }
                    return lexical_scan(br);
                } else if(peek == '*') {
                    readch(br);
                    while (peek != '*') {
                        readch(br);
                        if(peek == (char) -1)
                            break;
                    }
                    readch(br);
                    if(peek == '/'){
                        peek = ' ';
                        return lexical_scan(br);
                    } else {
                        System.err.println("Erroneous character"
                                + " after * : " + peek);
                        return null;
                    }
                } else {
                    peek = ' ';
                    return Token.div;
                }
            case ';':
                peek = ' ';
                return Token.semicolon;

            case ',':
                peek = ' ';
                return Token.comma;

            case '&':
                readch(br);
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } else {
                    System.err.println("Erroneous character"
                            + " after & : " + peek);
                    return null;
                }

            case '|':
                readch(br);
                if (peek == '|') {
                    peek = ' ';
                    return Word.or;
                } else {
                    System.err.println("Erroneous character"
                            + " after | : " + peek);
                    return null;
                }

            case '<':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.le;
                } else if (peek == '>') {
                    peek = ' ';
                    return Word.ne;
                } else {
                    peek = ' ';
                    return Word.lt;
                }

            case '>':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.ge;
                } else {
                    peek = ' ';
                    return Word.gt;
                }

            case '=':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.eq;
                } else {
                    System.err.println("Erroneous character"
                            + " after = : " + peek);
                    return null;
                }

            case (char) -1:
                return new Token(Tag.EOF);

            default:
                String a = "";
                if (Character.isLetter(peek) || peek == '_') {
                    while (Character.isLetter(peek) || Character.isDigit(peek) || peek == '_') {
                        if (start == true && peek == '_') {
                            while (peek == '_') {
                                a += peek;
                                readch(br);
                            }
                            if (Character.isLetter(peek) || Character.isDigit(peek)) {
                                start = false;
                            } else {
                                System.out.println("Error in line " + line);
                                return null;
                            }
                        }
                        a += peek;
                        readch(br);
                    }
                    peek = ' ';
                    if (a.equals("assign")) { return Word.assign;}
                    if (a.equals("to")) {return Word.to;}
                    if (a.equals("if")) {return Word.iftok;}
                    if (a.equals("else")) {return Word.elsetok;}
                    if (a.equals("while")) {return Word.whiletok;}
                    if (a.equals("begin")) {return Word.begin;}
                    if (a.equals("end")) {return Word.end;}
                    if (a.equals("print")) {return Word.print;}
                    if (a.equals("read")) {return Word.read;}
                    else
                        return new LetterTok(257, a);
                } else if (Character.isDigit(peek)) {
                    if (peek == '0') {
                        readch(br);
                        if (Character.isDigit(peek)) {
                            System.out.println("Error in line " + line);
                            throw new IllegalArgumentException("Error! Wrong digit");
                        } else {
                            a = "0";
                            peek = ' ';
                            return new NumberTok(256, a);
                        }
                    } else {
                        while (Character.isDigit(peek)) {
                            a += peek;
                            readch(br);
                        }
                        peek = ' ';
                        return new NumberTok(256, a);
                    }
                } else {
                    System.err.println("Erroneous character: " + peek);
                    return null;
                }
        }
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path;
        path = "C:/Users/simon/Desktop/info/LFT-Lab/Esercizi/Analisi Lessicale/bo.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Token tok;
            do {
                tok = lex.lexical_scan(br);
                System.out.println(tok);
            } while (tok.tag != Tag.EOF);
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
