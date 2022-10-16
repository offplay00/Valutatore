public class LetterTok extends Token {
  public String lexeme = "";
  public LetterTok(int tag, String s) { super(tag);  lexeme =s; }
  public String toString() {return "<" + tag + "," + lexeme + ">";}
  public static final LetterTok
  a = new LetterTok(Tag.ID, "a"),b = new LetterTok(Tag.ID, "b"),
  e = new LetterTok(Tag.ID, "e"),i = new LetterTok(Tag.ID, "i"),
  p = new LetterTok(Tag.ID, "p"),r = new LetterTok(Tag.ID, "r"),
  t = new LetterTok(Tag.ID, "t"),w = new LetterTok(Tag.ID, "w");
}
