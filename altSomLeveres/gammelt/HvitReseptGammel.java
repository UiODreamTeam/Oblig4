class HvitResept extends Resept {

  public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);  //Kaller konstruktøren til Resepter
  }
  public double prisAaBetale(){  //Metode som returnerer prisen på alminnelig hvit resept.
    return legemiddel.hentPris();
  }
  public String farge(){  //Metode som returnerer reseptens farge
    return "hvit";
  }
}
