class Testing {
  public static void main(String[] args) throws UlovligUtskrift{
    Narkotisk extasy = new Narkotisk("Extasy", 49.9, 0.01, 10);
    Vanedannende valium = new Vanedannende("Valium", 249.9, 48.5, 8);
    Vanlig paracet = new Vanlig("Paracet", 69.90, 100);
    Lege lege = new Lege("Ole");
    Lege lege2 = new Lege("Per");
    Pasient pasient = new Pasient("Cold", "12345212232");
    HvitResept hvit = new HvitResept(valium, lege, pasient, 2);
    BlaaResept blaa = new BlaaResept(paracet, lege, pasient, 2);
    PResept P = new PResept(valium, lege, pasient);
    MilitaerResept militaer = new MilitaerResept(paracet, lege, pasient, 4);
    Spesialist spesialisten = new Spesialist("Dole", 3);

    System.out.println("Testing av metoder! Forventet er true på alt som ikke er reseptutskrifter, der skrives det ut i tekst.");
    System.out.println("\nTester hentNavn()");
    System.out.println("Ole" == lege.hentNavn());
    System.out.println("\nTester compareTo()");
    System.out.println(lege.compareTo(lege2) == -1);
    System.out.println("\nTester utskrift av de ulike reseptene:\nBlaa;");
    System.out.println(spesialisten.skrivBlaaResept(extasy, pasient, 5));
    System.out.println("\nMilitaer;");
    System.out.println(spesialisten.skrivMilitaerResept(valium, pasient, 3));
    System.out.println("\nP;");
    System.out.println(spesialisten.skrivPResept(paracet, pasient));
    System.out.println("\nHvit;");
    System.out.println(spesialisten.skrivHvitResept(paracet, pasient, 2));
    System.out.println("\nTester henting av lista med utskrevende resepter:");
    for(Resept r : spesialisten.hentReseptListe()){
        System.out.println(r);
    }
    System.out.println("\nTester om lege kan skrive tut narkotisk:");
    try{
      lege.skrivHvitResept(extasy, pasient, 2);
  } catch(Exception e){
    System.out.println("true");
    }
    System.out.println("\nTester om spesialist kan skrive narkotisk:");
    try{
      spesialisten.skrivMilitaerResept(extasy, pasient, 3);
      System.out.println("true");
    } catch(Exception e){
      System.out.println("false");
    }
  }
}
