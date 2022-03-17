public class Client extends Member {

    // --------------------------------------
    //               Attribut
    //---------------------------------------
    private String clientNb;
    private Boolean validity;

    // --------------------------------------
    //             Constructeur
    //---------------------------------------

    /**
     * Constructeur général d'un client
     *
     * @param nb Le numéro du client
     * @param info Toutes les informations pertinentes concernant le client
     */
    public Client(String nb, String[] info) {
        clientNb = nb;

        super.setName(info[0]);
        super.setEmail(info[1]);
        super.setAddress(info[2]);
        super.setCity(info[3]);
        super.setProvince(info[4]);
        super.setPostalCode(info[5]);
        validity = true;
    }

    /**
     * Constructeur d'un client ayant comme numéro de client "0", permet d'identifier
     * les cas d'une recherche infructueuse de client
     *
     */
    public Client() {
        clientNb = "0";
    }

    // --------------------------------------
    //             Getter/Setter
    //---------------------------------------

    /**
     * Getter de clientNb
     *
     * @return L'attribut client
     */
    public String getClientNb(){
        return clientNb;
    };

    /**
     * Getter de validity
     *
     * @return L'attribut validity
     */
    public Boolean getValidity() {
        return validity;
    }

    // --------------------------------------
    //               Méthode
    //---------------------------------------

    /**
     * Permet de mettre à jour les informations d'un client
     *
     * @param info Les nouvelles informations d'un client
     */
    public void update(String[] info){

        super.setName(info[0]);
        super.setEmail(info[1]);
        super.setAddress(info[3]);
        super.setCity(info[4]);
        super.setProvince(info[5]);
        super.setPostalCode(info[6]);

    };

}
