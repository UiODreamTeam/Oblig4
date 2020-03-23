class HvitResept extends Resept {

  public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);  //Kaller konstruktøren til Resepter
  }
  public double prisAaBetale(){  //Metode som returnerer prisen på alminnelig hvit resept.
    return legemiddel.hentPris();
  }
  public String farge(){  //Metode som returnerer reseptens farge
    return "hvit";
  }
}
