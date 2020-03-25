class MilitaerResept extends HvitResept {

  public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);  //Kaller på resepters konstruktør
  }

  public double prisAaBetale(){  //Metode som returnerer prisen på militærresepten.
    return 0;
  }
}
