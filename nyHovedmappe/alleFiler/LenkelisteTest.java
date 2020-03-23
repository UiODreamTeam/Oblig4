class LenkelisteTest {
  public static void main(String[] args) {
    Liste<String> liste = new Lenkeliste<String>();
    liste.leggTil("hei");
    liste.leggTil("paa");
    liste.leggTil("deg");
    liste.leggTil("!");
    for(String s : liste){
      System.out.println(s);
    }
  }
}
