class PResept extends HvitResept {

  public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
    super(legemiddel, utskrivendeLege, pasient, 3);  //Kaller på resepters konstruktør
  }

  public double prisAaBetale(){  //Metode som returnerer prisen på en P-resept
    double pris = legemiddel.hentPris();
    if ((pris - 108)>0){
      pris = pris -108;
    }else{
      pris = 0;
    }
    return pris;
  }
}
