class MilitaerResept extends HvitResept {

  public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);  //Kaller på resepters konstruktør
  }

  public double prisAaBetale(){  //Metode som returnerer prisen på militærresepten.
    return 0;
  }
  public String hentType(){
      return "militaer";
  }
}
