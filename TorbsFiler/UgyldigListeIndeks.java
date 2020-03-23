class UgyldigListeIndeks extends RuntimeException {  //Gitte exception
  UgyldigListeIndeks(int indeks) {
    super("Ugyldig indeks:" + indeks);
  }
}
