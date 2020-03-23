class Pasient {
    static int idTeller;
    String navn, fodselsnummer;
    int id;
    Stabel<Resept> reseptStabel = new Stabel<Resept>();

    public Pasient(String navn, String fodselsnummer){
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        id = idTeller;
        idTeller++;
    }

    public void leggTilResept(Resept nyResept){  //Metodefor å legge til resept
        reseptStabel.leggPaa(nyResept);
    }

    public Stabel<Resept> hentAlleResept(){  //Metode for å hente alle resepter.
        return reseptStabel;
    }

    public int hentId(){  //Metode for å hente id.
        return id;
    }

    public String hentNavn(){  //Metode for å hente navnet på pasienten.
        return navn;
    }

    public String hentFnr(){  //Metode for å hente fødselsnummer.
        return fodselsnummer;
    }
    public String toString(){  //Metode for penere utskrift
      String pasient = String.format("%-50s" + "%-20s", "Navn: "+navn, "Fodselsnummer:   " + fodselsnummer);
        return pasient;
    }
}
