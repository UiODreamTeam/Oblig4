class Lege {
  protected String navn;

  public Lege(String navn){
    this.navn = navn;
  }
  public String hentNavn(){  //Metode for å hente navn
    return navn;
  }

  public String toString(){  //Metode for enklere utskrift av objektet
    return "Navn: " + navn;
  }
}
