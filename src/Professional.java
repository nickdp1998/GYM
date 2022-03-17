public class Professional extends Member {

    // --------------------------------------
    //               Attribut
    //---------------------------------------
    private String profNb;
    private String services;

    // --------------------------------------
    //             Constructeur
    //---------------------------------------

    /**
     * Constructeur général de Professionnal
     *
     * @param nb Un numéro de professionnel
     * @param info Toutes les informations importantes concernant le professionnel
     */
    public Professional(String nb, String[] info) {
        profNb = nb;

        services = "";
        super.setName(info[0]);
        super.setEmail(info[1]);
        super.setAddress(info[2]);
        super.setCity(info[3]);
        super.setProvince(info[4]);
        super.setPostalCode(info[5]);

    }

    /**
     * Constructeur d'un professionnel ayant comme numéro de professionnel "0", permet d'identifier
     * les cas d'une recherche infructueuse de professionnel
     *
     */
    public Professional() {
        profNb = "0";
    }

    // --------------------------------------
    //             Getter/Setter
    //---------------------------------------

    /**
     * Getter de profNb
     *
     * @return L'attribut profNb
     */
    public String getProfNb(){
        return profNb;
    };

    /**
     * Getter de services
     *
     * @return L'attribut services
     */
    public String getServices(){
        return services;
    };

    // --------------------------------------
    //               Méthode
    //---------------------------------------

    /**
     * Permet de mettre à jour les données d'un professionnel
     *
     * @param info Les nouvelles informations
     */
    public void update(String[] info){
        super.setName(info[0]);
        super.setEmail(info[1]);
        super.setAddress(info[3]);
        super.setCity(info[4]);
        super.setProvince(info[5]);
        super.setPostalCode(info[6]);
    };

    /**
     * Permet d'ajouter un service à l'attribut services
     *
     * @param nb Le numéro d'un service
     */
    public void addService(String nb){
        if(services.equals("")) {
            services = nb;
        } else {
            services += "," + nb;
        }
    };

    /**
     * Permet de supprimer un service de l'attribut services
     *
     * @param nb Le numéro d'un service
     */
    public void deleteService(String nb){
        String[] arrayServ = services.split(",");
        int size = arrayServ.length;
        services = "";
        for(int i = 0; i < size; i++) {
            if(!arrayServ[i].equals(nb)) {
                if(services.equals("")) {
                    services = arrayServ[i];
                } else {
                    services += "," + arrayServ[i];
                }
            }
        }
    };

}
