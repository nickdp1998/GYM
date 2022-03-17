import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FormatCompt {

    // --------------------------------------
    //               Attribut
    //---------------------------------------
    private SaveReport saveReport;

    // --------------------------------------
    //             Constructeur
    //---------------------------------------

    /**
     * Constructeur de FormatCompt
     *
     */
    public FormatCompt() {
        saveReport = new SaveReport();
    }

    // --------------------------------------
    //             Getter/Setter
    //---------------------------------------


    // --------------------------------------
    //               Méthode
    //---------------------------------------

    /**
     * Met dans le bon format le rapport de synthèse
     *
     * @param sessionRep Le répertoire de séance
     * @param profRep Le répertoire de professionnel
     */
    public void formatReport(SessionRep sessionRep, ProfRep profRep){
        Professional[] profs = profRep.getProfs();
        Session[] sessions = sessionRep.getSessions();
        int pSize = profs.length;
        int sSize = sessions.length;
        int pCost;
        int pNbService;
        int totalCost = 0;
        String cost;
        int costSize;

        String[] data = new String[pSize*6 + 5];
        for(int i = 0; i < pSize; i++) {
            data[i*6] = "------------------------------------";
            data[i*6+1] = "Nom : " + profs[i].getName();
            data[i*6+2] = "Numéro : " + profs[i].getProfNb();

            pCost = 0;
            pNbService = 0;
            for(int j = 0; j < sSize; j++) {
                if(checkNb(sessions[j],profs[i]) && checkDate(sessions[j])) {
                    pNbService++;
                    pCost += calculateCost(sessions[j]);
                }
            }

            if(pCost == 0) {
                cost = "0";
            } else {
                cost = "" + pCost;
                costSize = cost.length();
                cost = cost.substring(0, costSize - 2) + "," + cost.substring(costSize - 2, costSize);
            }

            data[i*6+3] = "Frais pour la semaine : " + cost + "$";
            data[i*6+4] = "Nombre de service : " + pNbService;
            data[i*6+5] = "------------------------------------";
            totalCost += pCost;
        }

        if(totalCost == 0) {
            cost = "0";
        } else {
            cost = "" + totalCost;
            costSize = cost.length();
            cost = cost.substring(0, costSize - 2) + "," + cost.substring(costSize - 2, costSize);
        }
        data[pSize*6] = "------------------------------------";
        data[pSize*6+1] = "Nombre total de professionnel : " + pSize;
        data[pSize*6+2] = "Nombre total de service : " + sSize;
        data[pSize*6+3] = "Cout total : " + cost + "$";
        data[pSize*6+4] = "------------------------------------";
        saveReport.save(data);
    };

    /**
     * Met dans le bon format les données pour les fichiers TEF
     *
     * @param sessionRep Le répertoire de séance
     * @param profRep Le répertoire de professionnel
     */
    public void formatTEF(SessionRep sessionRep, ProfRep profRep){
        Professional[] profs = profRep.getProfs();
        Session[] sessions = sessionRep.getSessions();
        int pSize = profs.length;
        int sSize = sessions.length;
        int pCost;
        String cost;
        int costSize;
        String info;

        for(int i = 0; i < pSize; i++) {
            pCost = 0;
            info = "";
            for(int j = 0; j < sSize; j++) {
                if(checkNb(sessions[j],profs[i]) && checkDate(sessions[j])) {
                    pCost += calculateCost(sessions[j]);
                }
            }

            if(pCost == 0) {
                cost = "0";
            } else {
                cost = "" + pCost;
                costSize = cost.length();
                cost = cost.substring(0, costSize - 2) + "," + cost.substring(costSize - 2, costSize);
            }

            info += profs[i].getName() + ";" + profs[i].getProfNb() + ";" + cost;
            saveReport.saveTEF(profs[i].getProfNb(), info);
        }
    };

    /**
     * Met dans le bon format le fichier d'un professionel pour la procédure comptable
     *
     * @param sessionRep Le répertoire de séance
     * @param profRep Le répertoire de professionnel
     * @param clientRep Le répertoire de client
     */
    public void formatProf(SessionRep sessionRep, ProfRep profRep, ClientRep clientRep) {
        Professional[] profs = profRep.getProfs();
        Session[] sessions = sessionRep.getSessions();
        int pSize = profs.length;
        int sSize = sessions.length;
        ArrayList<String> info;

        for(int i = 0; i < pSize; i++) {
            Professional prof = profs[i];
            info = new ArrayList<>();

            info.add("Nom : " + prof.getName());
            info.add("Numéro : " + prof.getProfNb());
            info.add("Adresse : " + prof.getAddress());
            info.add("Ville : " + prof.getCity());
            info.add("Province : " + prof.getProvince());
            info.add("Code postal : " + prof.getPostalCode());
            info.add("Séances : ");

            for(int j = 0; j < sSize; j++) {
                if(checkNb(sessions[j],profs[i]) && checkDate(sessions[j])) {
                    Session session = sessions[j];
                    info.add("\tDate de la séance : " + session.getDate());
                    info.add("\tDate de création : " + session.getService().getDate());

                    String[] clients = session.getConfirmations().split(",");
                    if(!clients.equals("")) {
                        info.add("\tClients :");
                        for(int k = 0; k < clients.length; k++) {
                            Client client = clientRep.getClient(clients[k]);
                            info.add("\t\t" + client.getName() + ", " + client.getClientNb());
                        }
                    }

                    info.add("\tNuméro de séance : " + session.getService().getServiceNb());
                    info.add("\tCout : " + session.getService().getCost() + "$ par client");
                }
            }

            saveReport.saveMember(prof.getName(), info.toArray(new String[0]));
        }
    }

    /**
     * Met dans le bon format le fichier d'un client pour la procédure comptable
     *
     * @param sessionRep Le répertoire de séance
     * @param profRep Le répertoire de professionnel
     * @param clientRep Le répertoire de client
     */
    public void formatClient(SessionRep sessionRep, ProfRep profRep, ClientRep clientRep) {
        Client[] clients = clientRep.getClients();
        Session[] sessions = sessionRep.getSessions();
        int pSize = clients.length;
        int sSize = sessions.length;
        ArrayList<String> info;

        for(int i = 0; i < pSize; i++) {
            Client client = clients[i];
            info = new ArrayList<>();

            info.add("Nom : " + client.getName());
            info.add("Numéro : " + client.getClientNb());
            info.add("Adresse : " + client.getAddress());
            info.add("Ville : " + client.getCity());
            info.add("Province : " + client.getProvince());
            info.add("Code postal : " + client.getPostalCode());
            info.add("Séances : ");

            for(int j = 0; j < sSize; j++) {
                if(checkSub(sessions[j],client) && checkDate(sessions[j])) {
                    Session session = sessions[j];
                    info.add("\tDate de la séance : " + session.getDate());

                    Professional prof = profRep.getProf(session.getService().getProfNb());
                    info.add("\tNom du professionnel : " + prof.getName());


                    info.add("\tNom du service : " + session.getService().getName());
                }
            }

            saveReport.saveMember(client.getName(), info.toArray(new String[0]));
        }
    };

    /**
     * Vérifie qu'un professionnel donne une séance
     *
     * @param session Une séance
     * @param prof Un professionnel
     * @return Vrai si le professionnel donne la séance, faux sinon
     */
    private Boolean checkNb(Session session, Professional prof) {
        Service service = session.getService();

        return service.getProfNb().equals(prof.getProfNb());
    }

    /**
     * Vérifie que la date d'une séance est dans la semaine dernière
     *
     * @param session Une séance
     * @return Vrai si la séance s'est déroulé dans la semaine dernière, faux sinon
     */
    private Boolean checkDate(Session session) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(6,-7);
        Date weekAgo = calendar.getTime();
        try {
            Date sessionDate = new SimpleDateFormat("dd-MM-yyyy").parse(session.getDate());
            return (sessionDate.after(weekAgo) || sessionDate.equals(weekAgo));
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Calcul le coût total d'une session (nom de participant X frais)
     *
     * @param session Une séance
     * @return Les frais totaux d'une séance
     */
    private int calculateCost(Session session) {
        int cost;
        String confirmations = session.getConfirmations();

        if(confirmations.equals(""))
            return 0;

        int nbConfirmations = confirmations.split(",").length;

        String sCost = session.getService().getCost();
        int size = sCost.length();

        cost = Integer.parseInt(sCost.substring(0,size-3))*100 +
                Integer.parseInt(sCost.substring(size-2,size));

        return cost * nbConfirmations;
    }

    /**
     * Vérifie qu'un client à participé à une séance
     *
     * @param session Une séance
     * @param client Un client
     * @return Vrai si le client à participé à la séance, faux sinon
     */
    public Boolean checkSub(Session session, Client client) {
        if(session.getConfirmations().equals("")) {
            return false;
        }

        String[] conf = session.getConfirmations().split(",");

        for(int i = 0; i < conf.length; i++) {
            if(conf[i].equals(client.getClientNb()))
                return true;
        }

        return false;
    }
}
