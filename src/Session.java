import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Session {

    // --------------------------------------
    //               Attribut
    //---------------------------------------
    private String date;
    private String inscriptions;
    private String confirmations;
    private int available;
    private Service service;
    private FormatSesData formatSesData;

    // --------------------------------------
    //             Constructeur
    //---------------------------------------

    /**
     * Constructeur générale d'une session à l'aide de son service associé
     *
     * @param service Un service
     */
    public Session(Service service) {
        formatSesData = new FormatSesData();
        this.service = service;
        date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        inscriptions = "";
        confirmations = "";
        available = Integer.parseInt(service.getCapacity());
    }

    /**
     * Constructeur d'un session ayant comme numéro de séance "0", permet d'identifier
     * les cas d'une recherche infructueuse de séance
     *
     */
    public Session() {
        service = new Service();
    }

    // --------------------------------------
    //             Getter/Setter
    //---------------------------------------

    /**
     * Getter de date
     *
     * @return L'attribut date
     */
    public String getDate(){
        return date;
    };

    /**
     * Getter de available
     *
     * @return L'attribut available
     */
    public int getAvailable(){
        return available;
    };

    /**
     * Getter de service
     *
     * @return L'attribut service
     */
    public Service getService() {
        return service;
    }

    /**
     * Getter de inscriptions
     *
     * @return L'attribut inscriptions
     */
    public String getInscriptions() {
        return inscriptions;
    }

    /**
     * Getter de confirmations
     *
     * @return L'attribut confirmations
     */
    public String getConfirmations() {
        return confirmations;
    }

    // --------------------------------------
    //               Méthode
    //---------------------------------------

    /**
     * Permet d'ajouter une client à l'attribut inscriptions et de générer l'enregistrement
     *
     * @param nb Le numéro d'un client
     * @param comment Un commentaire pour l'enregistrement
     */
    public void addClient(String nb, String comment){
        if(available == 0) {
            System.out.println("Séance complète");
            return;
        }

        if(nb == null) {
            System.out.println("Numéro invalide");
            return;
        }

        String[] clients = inscriptions.split(",");
        for(int i = 0; i < clients.length; i++) {
            if(clients[i].equals(nb)) {
                System.out.println("Déjà inscrit");
                return;
            }
        }

        if(inscriptions.equals("")) {
            inscriptions = nb;
        } else {
            inscriptions += "," + nb;
        }
        available--;
        formatSesData.saveIns(this, nb, comment);

    };

    /**
     * Permet d'ajouter un client à l'attribut confirmations et de générer l'enregistrement
     *
     * @param nb Le numéro d'un client
     * @param comment Un commentaire pour l'enregistrement
     * @return Vrai si le client était dans les inscriptions, faux sinon
     */
    public Boolean checkClient(String nb, String comment){
        String[] arrayIns = inscriptions.split(",");
        String[] arrayConf = confirmations.split(",");

        if(nb == null) {
            return false;
        }

        for(int i = 0; i < arrayConf.length; i++) {
            if(arrayConf[i].equals(nb)) {
                return true;
            }
        }

        for(int i = 0; i < arrayIns.length; i++) {
            if(arrayIns[i].equals(nb)){
                if(confirmations.equals("")) {
                    confirmations = nb;
                } else {
                    confirmations += "," + nb;
                }
                formatSesData.saveConf(this, nb, comment);
                return true;
            }
        }
        return false;
    };

}
