import java.util.Iterator;  //Importerer iteratoren som skal med.

class Lenkeliste<T> implements Liste<T> {
  protected int stoerrelse;
  protected Node start, slutt;  //Lager tre variabler for å holde orden på klassen

  protected class Node{
      T innhold;
      Node neste, forrige;  //Velger å ha pekere både forover og bakover

      public Node(T innhold){
        this.innhold = innhold;
      }
  }

  private class LenkelisteIterator implements Iterator<T>{  //Implementerer iterator for enkel utskrift
    Node gjeldende;

    public LenkelisteIterator(){
      gjeldende = start;
    }
    public boolean hasNext(){   //Overskriver hasNext-metoden for å fungere med klassen
      return gjeldende != slutt.forrige;
    }
    public T next(){    //Overskriver next-metoden for å fungere med klassen
      gjeldende = gjeldende.neste;
      return gjeldende.innhold;
    }
  }

  public Iterator<T> iterator(){  //Metode som hører til iterator for enkel utskrift
    return new LenkelisteIterator();
  }

  public Lenkeliste(){  //Konstruktør til lenkelista.
    stoerrelse = 0;  //Størrelsen vi starter med er 0
    start = new Node(null);
    slutt = new Node(null);  //Har en start- og sluttnode for å markere hvor
                             //lenkelista starter og slutter.
    start.neste = slutt;
    slutt.forrige = start;  //Passer på at pekerene stemmer fra start.
  }

  public void leggTil(T x){  //Metode for å legge til på slutten av lenkelista
    Node ny = new Node(x);
    ny.forrige = slutt.forrige;  //Den nyes forrige-peker skal peke på den noden
                                //som tidligere var sist, altså slutts forrige-peker
    ny.forrige.neste = ny;  //Den forrige bakerste er nå foran ny, og peker derfor på ny.
    ny.neste = slutt;  //Den nye noden er nå bakerst og peker mot sluttnoden.
    slutt.forrige = ny;  //Sluttnoden peker nå på den nye, sist i lenkelisten.

    stoerrelse ++;   //Størrelsen økes med 1.
  }

  public T fjern(){    //Metode for å fjerne siste element i lenkelista.
    if(stoerrelse == 0){  //Sjekker at det er noe å fjerne.
      throw new UgyldigListeIndeks(-1);
    }

    Node fjernet = start.neste;  //Fjerner den første noden, noden etter start.
    fjernet.neste.forrige = start;
    start.neste = fjernet.neste;    //Endrer pekerene så alt stemmer, og ingen
                                    //peker mot elementet som fjernes.
    stoerrelse--;            //Minker størrelsen med 1.
    return fjernet.innhold;  //Returnerer innholdet til noden som ble fjernet,
                             //som er typen vi er interessert i.
  }

  public T fjern(int pos){  //Metode for å fjerne på en gitt posisjon i lenkelista.
    if(stoerrelse == 0){  //Sjekker først om det fins noe å fjerne.
      throw new UgyldigListeIndeks(-1);
    }
    if(pos < 0 || pos >= stoerrelse){  //Passer på at pos fins som posisjon.
      throw new UgyldigListeIndeks(pos);
    }
    int i = 0;
    Node itererer = start.neste;  //Lager en node som hjelp for å jobbe oss gjennom
                                  //lenkelista, starter på noden etter start.
    while(i != pos){
      itererer = itererer.neste;  //Går videre til neste node.
      i++;
    }
    T fjernet = itererer.innhold;  //Innholdet til noden, skal returneres
    itererer.neste.forrige = itererer.forrige;
    itererer.forrige.neste = itererer.neste;   //Endrer pekerene så alt stemmer

    stoerrelse --;      //Minker størrelsen
    return fjernet;
  }

  public void sett(int pos, T x){  //Metode for å sette inn element på gitt plass
    if(stoerrelse == 0){  //Sjekker først at det faktisk fins noe liste.
      throw new UgyldigListeIndeks(-1);
    }
    if(pos < 0 || pos >= stoerrelse){   //Sjekker at vi er innenfor listas indekser.
      throw new UgyldigListeIndeks(pos);
    }

    Node ny = new Node(x);   //Oppretter den nye noden.
    Node bytte = start.neste;   //Finner noden vi skal erstatte.
    int i = 0;
    while(i != pos){
      bytte = bytte.neste;    //Finner noden på gitte plass.
      i++;
    }
    ny.neste = bytte.neste;
    ny.forrige = bytte.forrige;
    ny.neste.forrige = ny;
    ny.forrige.neste = ny;    //Endrer pekerene så elementet som var ikke fins mer
                              //og det nye peker på det den skal.
  }

  public void leggTil(int pos, T x){   //Metode for å legge til på angitt plass
    if (pos < 0 || pos > stoerrelse){  //Sjekker om pos fins som posisjon.
      throw new UgyldigListeIndeks(pos);
    }

    Node ny = new Node(x);   //Oppretter noden som skal legges til.
    Node foran = start;      //Oppretter node til hjelp for å finne angitte plass
    for(int i=0; i<pos; i++){
      foran = foran.neste;
    }
    ny.neste = foran.neste;
    ny.neste.forrige = ny;
    ny.forrige = foran;
    foran.neste = ny;    //Endrer pekerene så elementet som befant seg på plassen,
                         //og det nye elementet peker på rett ting.

    stoerrelse++;        //Øker størrelsen
  }

  public int stoerrelse(){    //Metode for å hente størrelsen
    return stoerrelse;
  }

  public T hent(int pos){   //Metode for å hente elementet på gitte posisjon
    if (stoerrelse == 0){   //Sjekker at det fins noe å hente.
      throw new UgyldigListeIndeks(-1);
    }
    if(pos < 0 || pos >= stoerrelse){  //Sjekker at pos fins som posisjon
      throw new UgyldigListeIndeks(pos);
    }
    int i = 0;
    Node itererer = start.neste;  //Node for å gå gjennom lista
    while(i != pos){
      itererer = itererer.neste;
      i++;
    }
    T interessant = itererer.innhold;  //Tar ut innholdet av noden vi var interessert i
    return interessant;
  }
}

class TestLista {
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
