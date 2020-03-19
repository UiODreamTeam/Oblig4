class BlaaResept extends Resept {
  public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);  //Kaller på resepters konstruktør
  }

  public double prisAaBetale(){  //Metode som returnerer prisen på BlaaResept.
    double pris = legemiddel.hentPris()*0.25; //Gir 75% rabatt så pasienten betaler 25%.
    return pris;
  }
  public String farge(){  //Metode som returnerer reseptens farge
    return "blaa";
  }
}
