import java.io.File;

public class SessionRep {

    // --------------------------------------
    //               Attribut
    //---------------------------------------
    //Code service, date, ins, conf, available
    private Session[] sessions;
    private Session notSession;

    // --------------------------------------
    //             Constructeur
    //---------------------------------------

    /**
     * Constructeur de SessionRep
     *
     */
    public SessionRep() {
        sessions = new Session[0];
        notSession = new Session();
    }

    // --------------------------------------
    //             Getter/Setter
    //---------------------------------------

    /**
     * Getter de sessions
     *
     * @return L'attribut sessions
     */
    public Session[] getSessions() {
        return sessions;
    }

    // --------------------------------------
    //               Méthode
    //---------------------------------------

    /**
     * Permet d'accéder à une séance précise
     *
     * @param date Une date
     * @param nb Une numéro de séance
     * @return Une session ayant un numéro et une date comme indiqué en argument
     */
    public Session getSession(String date, String nb){
        for(int i = 0; i < sessions.length; i++) {
            if(sessions[i].getService().getServiceNb().equals(nb) && sessions[i].getDate().equals(date)) {
                return sessions[i];
            }
        }
        return notSession;
    }

    /**
     * Permet d'ajouter une séance à l'attribut sessions
     *
     * @param session Une séance
     */
    public void addSession(Session session){
        int size = sessions.length;
        Session[] newSession = new Session[size+1];
        newSession[0] = session;
        for(int i = 0; i < size; i++) {
            newSession[i+1] = sessions[i];
        }
        sessions = newSession;
    };

}
