public class ProfRep {

    // --------------------------------------
    //               Attribut
    //---------------------------------------
    private Professional[] profs;
    private Request request;
    private static Professional notProfessional;

    // --------------------------------------
    //             Constructeur
    //---------------------------------------

    /**
     * Constructeur de ProfRep
     *
     * @param request Un objet Request
     */
    public ProfRep(Request request) {
        this.request = request;
        profs = new Professional[0];
        notProfessional = new Professional();
    }

    // --------------------------------------
    //             Getter/Setter
    //---------------------------------------

    /**
     * Getter de profs
     *
     * @return L'attribut profs
     */
    public Professional[] getProfs() {
        return profs;
    }

    // --------------------------------------
    //               Méthode
    //---------------------------------------

    /**
     * Permet d'accéder à un professionnel en particulier
     *
     * @param nb Le numéro d'un professionnel
     * @return Un professionnel ayant comme numéro celui passé en argument
     */
    public Professional getProf(String nb){
        for(int i = 0; i < profs.length; i++) {
            if(profs[i].getProfNb().equals(nb)) {
                return profs[i];
            }
        }

        return notProfessional;
    }

    /**
     * Permet d'ajouter un professionnel à profs
     *
     * @param prof Un professionnel
     */
    public void addProf(Professional prof){
        int size = profs.length;
        Professional[] newProfs = new Professional[size+1];

        for(int i = 0; i < size; i++) {
            newProfs[i] = profs[i];
        }
        newProfs[size] = prof;
        profs = newProfs;
    };

    /**
     * Permet de supprimer un professionnel et ses services associées
     *
     * @param nb Un numéro de professionnel
     */
    public void deleteProf(String nb){
        int size = profs.length;
        Professional[] newProfs = new Professional[size-1];
        for(int i = 0; i < size; i++) {
            if(profs[i].getProfNb().equals(nb)) {
                //Suppression des services
                String services = profs[i].getServices();
                if(!services.equals("")) {
                    String[] oldServices = services.split(",");
                    for (int j = 0; j < oldServices.length; j++) {
                        request.deleteService(new String[]{oldServices[j], nb}, false);
                    }
                }

                //Suppression du professionnel
                for(int j = 0; j < i; j++) {
                    newProfs[j] = profs[j];
                }
                for(int j = i+1; j < size; j++) {
                    newProfs[j-1] = profs[j];
                }
            }
        }
        profs = newProfs;
    };

}
