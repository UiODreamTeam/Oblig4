import java.io.FileNotFoundException;

class LegesystemTest{
    public static void main(String[] args) throws FileNotFoundException, UlovligUtskrift{
        Legesystem les = new Legesystem();
        les.lesFil("myeInndata.txt");
        //Test Pasientseksjon
        for(Pasient p : les.pasientListe){
            System.out.println(p.fodselsnummer);
        }
        //Test Legemiddelseksjon
        for(Legemiddel l : les.legemiddelListe){
            System.out.println(l.navn);
        }
        //Test Legeseksjonen
        for(Lege leg : les.legeListe){
            System.out.println(leg);
        }
        //Test Reseptseksjon
        for(Lege leg : les.legeListe){
            for(Resept r : leg.hentReseptListe()){
                System.out.println(r);
            }
        }
    }
}
