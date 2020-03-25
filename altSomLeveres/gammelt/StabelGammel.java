class Stabel<T> extends Lenkeliste<T>{

  public void leggPaa(T x){  //Metode for å legge til element på slutten av listen.
    super.leggTil(x);  //Bruker metoden fra lenkeliste som legger elementet bakerst
  }

  public T taAv(){  //Metode for å fjerne element på slutten av listen.
    T fjernet = super.fjern(super.stoerrelse()-1);
    return fjernet;
  }
}
