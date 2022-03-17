import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatSesData {

    // --------------------------------------
    //               Attribut
    //---------------------------------------
    private SaveSesRep saveSesRep;

    // --------------------------------------
    //             Constructeur
    //---------------------------------------

    /**
     * Constructeur de FormatSesData
     *
     */
    public FormatSesData() {
        saveSesRep = new SaveSesRep();
    };

    // --------------------------------------
    //             Getter/Setter
    //---------------------------------------


    // --------------------------------------
    //               Méthode
    //---------------------------------------

    /**
     * Met dans le bon format l'enregistrement d'une inscription d'un client à une séance
     *
     * @param session Une séance
     * @param client Un client
     * @param comment Un commentaire
     */
    public void saveIns(Session session, String client, String comment){
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
        String[] data = new String[8];
        data[0] = "-------------------------------------------------------";
        data[1] = "Date et heure actuelle : " + date;
        data[2] = "Date à laquelle le service sera fourni : " + session.getDate();
        data[3] = "Numéro du professionnel : " + session.getService().getProfNb();
        data[4] = "Numéro du membre : " + client;
        data[5] = "Code du service : " + session.getService().getServiceNb();
        data[6] = "Commentaire : " + comment;
        data[7] = "-------------------------------------------------------";
        saveSesRep.saveInc(data);
    };

    /**
     * Met dans le bon format l'enregistrement d'une confirmation d'un client à une séance
     *
     * @param session Une séance
     * @param client Un client
     * @param comment Un commentaire
     */
    public void saveConf(Session session, String client, String comment){
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
        String[] data = new String[7];
        data[0] = "-------------------------------------------------------";
        data[1] = "Date et heure actuelle : " + date;
        data[2] = "Numéro du professionnel : " + session.getService().getProfNb();
        data[3] = "Numéro du membre : " + client;
        data[4] = "Code du service : " + session.getService().getServiceNb();
        data[5] = "Commentaire : " + comment;
        data[6] = "-------------------------------------------------------";
        saveSesRep.saveConf(data);
    };

}
