import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Request {

    // --------------------------------------
    //               Attribut
    //---------------------------------------
    private static int clientCounter;
    private static int profCounter;
    private ClientRep clientRep;
    private ProfRep profRep;
    private ServiceRep serviceRep;
    private SessionRep sessionRep;
    private FormatCompt formatCompt;

    // --------------------------------------
    //             Constructeur
    //---------------------------------------

    /**
     * Constructeur de Request
     *
     */
    public Request(){
        serviceRep = new ServiceRep();
        profRep = new ProfRep(this);
        clientRep = new ClientRep();
        sessionRep = new SessionRep();
        formatCompt = new FormatCompt();
        clientCounter = 1;
        profCounter = 1;
    };

    // --------------------------------------
    //             Getter/Setter
    //---------------------------------------

    /**
     * Getter de clientRep
     *
     * @return L'attribut clientRep
     */
    public ClientRep getClientRep() {
        return clientRep;
    }

    /**
     * Getter de profRep
     *
     * @return L'attribut profRep
     */
    public ProfRep getProfRep() {
        return profRep;
    }

    /**
     * Getter de sessionRep
     *
     * @return L'attribut sessionRep
     */
    public SessionRep getSessionRep() {
        return sessionRep;
    }

    /**
     * Getter de serviceRep
     *
     * @return L'attribut serviceRep
     */
    public ServiceRep getServiceRep() {
        return serviceRep;
    }

    // --------------------------------------
    //               Méthode
    //---------------------------------------
    /**
     * Permet d'ajouter un client au répertoire de client
     *
     * @param info Les informations concernant le client
     */
    public void addClient(String[] info){
        String nb = "" + clientCounter;
        int size = nb.length();
        while(size < 9) {
            nb = "0" + nb;
            size++;
        }
        Client client = new Client(nb, info);
        clientRep.addClient(client);
        clientCounter++;
        System.out.println("Le numéro du client est " + nb);
    };

    /**
     * Permet de mettre à jour les informations d'un client
     *
     * @param info Les nouvelles informations du client
     * @param nb Le numéro du client
     */
    public void updateClient(String[] info, String nb){
        Client client = clientRep.getClient(nb);
        if(info[0] != null) {
            client.setName(info[0]);
        }
        if(info[1] != null) {
            client.setEmail(info[1]);
        }
        if(info[2] != null) {
            client.setAddress(info[2]);
        }
        if(info[3] != null) {
            client.setCity(info[3]);
        }
        if(info[4] != null) {
            client.setProvince(info[4]);
        }
        if(info[5] != null) {
            client.setPostalCode(info[5]);
        }
    };

    /**
     * Permet de supprimer un client du répertoire de client
     *
     * @param nb Le numéro d'un client
     */
    public void deleteClient(String nb){
        if(checkClientNb(nb)) {
            clientRep.deleteClient(nb);
            System.out.println("Compte supprimé");
        } else {
            System.out.println("Numéro invalide");
        }
    };

    /**
     * Permet d'ajouter un professionnel au répertoire de professionnel
     *
     * @param info Toutes les informations concernant le professionnel
     */
    public void addProf(String[] info){
        String nb = "" + profCounter;
        int size = nb.length();
        while(size < 9) {
            nb = "0" + nb;
            size++;
        }
        Professional prof = new Professional(nb, info);
        profRep.addProf(prof);
        profCounter++;
        System.out.println("Le numéro du professionnel est " + nb);
    };

    /**
     * Permet de mettre à jour un client
     *
     * @param info Les nouvelle informations du professionnel
     * @param nb Le numéro du professionnel
     */
    public void updateProf(String[] info, String nb){
        Professional prof = profRep.getProf(nb);
        if(info[0] != null) {
            prof.setName(info[0]);
        }
        if(info[1] != null) {
            prof.setEmail(info[1]);
        }
        if(info[2] != null) {
            prof.setAddress(info[2]);
        }
        if(info[3] != null) {
            prof.setCity(info[3]);
        }
        if(info[4] != null) {
            prof.setProvince(info[4]);
        }
        if(info[5] != null) {
            prof.setPostalCode(info[5]);
        }
    };

    /**
     * Permet de supprimer un professionnel du répertoire de professionnel
     *
     * @param nb Le numéro d'un professionnel
     */
    public void deleteProf(String nb){
        if(checkProfNb(nb)) {
            profRep.deleteProf(nb);
            System.out.println("Compte supprimé");
        } else {
            System.out.println("Numéro invalide");
        }
    };

    /**
     * Permet d'ajouter un service au répertoire de service
     *
     * @param info Toutes les informations concernant le service
     */
    public void addService(String[] info){
        Professional professional = profRep.getProf(info[7]);

        String[] newInfo = new String[10];
        newInfo[2] = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        newInfo[0] = info[0];
        newInfo[1] = info[1];
        for(int i = 3; i < 10; i++) {
            newInfo[i] = info[i];
        }

        // Code de la séance
        String code = info[2];
        String[] services = professional.getServices().split(",");
        if(!services[0].equals("")) {
            int size = services.length;
            int max = 1;
            int session;
            for (int i = 0; i < size; i++) {
                if (services[i].substring(0, 3).equals(code)) {
                    session = Integer.parseInt(services[i].substring(3, 5));
                    if (max == session)
                        max = session + 1;
                }
            }
            String sessionNb = "" + max;
            if (sessionNb.length() < 2)
                sessionNb = "0" + sessionNb;

            code += sessionNb;
            code += info[7].substring(7, 9);
        } else {
            String sessionNb = "01";
            code += sessionNb;
            code += info[7].substring(7, 9);
        }

        Service service = new Service(code, newInfo);
        serviceRep.addService(service);
        professional.addService(code);
        System.out.println("Le code du service est " + code);
    };

    /**
     * Permet de mettre à jour un service
     *
     * @param info Les nouvelles informations du service
     * @param nb Le numéro de service
     */
    public void updateService(String[] info,String nb){
        Service service = serviceRep.getService(nb);
        if(info[0] != null) {
            service.setHour(info[0]);
        }
        if(info[1] != null) {
            service.setDay(info[1]);
        }
        if(info[2] != null) {
            service.setCapacity(info[2]);
        }
        if(info[3] != null) {
            service.setCost(info[3]);
        }
        if(info[4] != null) {
            service.setComment(info[4]);
        }
    };

    /**
     * Permet de supprimer un service du répertoire de serivce
     *
     * @param nb Un numéro de professionel et un numéro de séance
     * @param prof Vrai si l'on pas en train de supprimer un professionel, faux sinon
     */
    public void deleteService(String[] nb, Boolean prof){
        Professional professional = profRep.getProf(nb[1]);
        serviceRep.deleteService(nb[0]);
        if(prof)
            professional.deleteService(nb[0]);
        System.out.println("Service supprimé");
    };

    /**
     * Vérifie la validité d'un client et affiche l'état de son compte
     *
     * @param nb Le numéro d'un client
     * @return Vrai si le numéro est valide, faux sinon
     */
    public Boolean clientAcces(String nb) {
        Client client = clientRep.getClient(nb);

        if(client.getClientNb().equals("0")) {
            System.out.println("Numéro inexistant");
            return false;
        } else if(client.getValidity()) {
            System.out.println("Numéro valide");
            return true;
        } else {
            System.out.println("Numéro suspendu");
            return false;
        }
    }

    /**
     * Vérifie si un numéro de client existe dans le répertoire
     *
     * @param nb Un numéro de client
     * @return Vrai si le numéro existe dans le répertoire, faux sinon
     */
    public Boolean checkClientNb(String nb){
        Client client = clientRep.getClient(nb);

        if(client.getClientNb().equals("0"))
            return false;

        return true;
    };

    /**
     * Vérifie si un numéro de professionel existe dans le répertoire
     *
     * @param nb Un numéro de professionel
     * @return Vrai si le numéro existe dans le répertoire, faux sinon
     */
    public Boolean checkProfNb(String nb){
        Professional prof = profRep.getProf(nb);

        if(prof.getProfNb().equals("0"))
            return false;

        return true;
    };

    /**
     * Vérifie si un numéro de service existe dans le répertoire
     *
     * @param nb Un numéro de service
     * @return Vrai si le numéro existe dans le répertoire, faux sinon
     */
    public Boolean checkServiceNb(String nb) {
        Service services = serviceRep.getService(nb);

        if(services.getServiceNb().equals("0"))
            return false;

        return true;
    }

    /**
     * Vérifie si un professionel est associé à un service
     *
     * @param nb Un numéro de professionnel et un numéro de service
     * @return Vrai si le professionel donne le service, faux sinon
     */
    public Boolean checkService(String[] nb){
        Service service = serviceRep.getService(nb[0]);
        if(service.getServiceNb().equals("0"))
            return false;

        if(service.getProfNb().equals(nb[1]))
            return true;

        return false;
    };

    /**
     * Permet d'inscrire un client à une séance
     *
     * @param info Toutes les informations concernant l'inscription
     */
    public void insSession(String[] info){
        Service service = serviceRep.getService(info[0]);
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Session session = sessionRep.getSession(date,info[0]);
        if(session.getService().getServiceNb().equals("0")) {
            session = new Session(service);
            session.addClient(info[1],info[2]);
            sessionRep.addSession(session);
        } else {
            session.addClient(info[1],info[2]);
        }
    };

    /**
     * Permet de confirmer la présence d'un client à une séance
     *
     * @param info Toutes les informatiosn concernant la confirmation
     */
    public void confSession(String[] info){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Session session = sessionRep.getSession(date,info[0]);

        if(session.getService().getServiceNb().equals("0")){
            System.out.println("Client non inscit.");
            return;
        }

        if(session.checkClient(info[1], info[2]))
            System.out.println("Inscrit");
        else
            System.out.println("Non inscrit");
    };

    /**
     * Permet d'afficher le répertoire de séance offerte dans la journée
     *
     */
    public void checkRepertoire(){
        Service[] services = serviceRep.getServices();
        for(int i = 0; i < services.length; i++) {
            if(checkSessionDay(services[i].getServiceNb())) {
                System.out.println("------------------------------------------");
                System.out.println("Nom du service : " + services[i].getName());
                System.out.println("Journée : " + services[i].getDay());
                System.out.println("Heure : " + services[i].getHour());
                System.out.println("Frais : " + services[i].getCost() + "$");
                System.out.println("Commentaire : " + services[i].getComment());
                System.out.println("Numéro de professionnel : " + services[i].getProfNb());
                System.out.println("Code de service : " + services[i].getServiceNb());
                System.out.println("------------------------------------------");
            }
        }
    };

    /**
     * Permet d'afficher une séance
     *
     * @param nb Le numéro de la séance
     */
    public void showSession(String nb) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Session session = sessionRep.getSession(date, nb);

        if(session.getService().getServiceNb().equals("0"))
            System.out.println("Séance inexistante");
        else {
            Service service = serviceRep.getService(nb);
            System.out.println("------------------------------------------");
            System.out.println("Heure : " + service.getHour());
            System.out.println("Frais : " + service.getCost());
            System.out.println("Commentaire : " + service.getComment());
            System.out.println("Numéro de professionnel : " + service.getProfNb());
            System.out.println("------------------------------------------");
        }

    }

    /**
     * Permet d'afficher un service
     *
     * @param nb Un numéro de service
     */
    public void showService(String nb) {
        Service service = serviceRep.getService(nb);
        System.out.println("------------------------------------------");
        System.out.println("Heure : " + service.getHour());
        System.out.println("Frais : " + service.getCost());
        System.out.println("Commentaire : " + service.getComment());
        System.out.println("Numéro de professionnel : " + service.getProfNb());
        System.out.println("------------------------------------------");

    }

    /**
     * Permet de montrer les inscriptions à une séance
     *
     * @param info La date et le numéro de la séance
     */
    public void showIns(String[] info) {
        Client client;
        Session session = sessionRep.getSession(info[1],info[0]);

        if(session.getService().getServiceNb().equals("0")) {
            System.out.println("Session inexistante");
            return;
        }

        String[] inscriptions = session.getInscriptions().split(",");
        String[] confirmations = session.getConfirmations().split(",");

        System.out.println("------------------------------------------");
        System.out.println("Membres inscrits :");
        for(int i = 0; i < inscriptions.length; i++) {
            client = clientRep.getClient(inscriptions[i]);
            System.out.println("\t - " + client.getName());
        }
        if(session.getConfirmations().equals("")) {
            System.out.println("Aucun membre confirmé");
        } else {
            System.out.println("Membre ayant confirmé la présence :");
            for(int i = 0; i < confirmations.length; i++) {
                client = clientRep.getClient(confirmations[i]);
                System.out.println("\t - " + client.getName());
            }
        }
        System.out.println("------------------------------------------");
    }

    /**
     * Permet de vérifier qu'une séance se déroule aujourd'hui
     *
     * @param nb Le numéro d'une séance
     * @return Vrai si la séance se déroule aujourdh'hui
     */
    public Boolean checkSessionDay(String nb) {
        String[] days = new String[] {"lundi","mardi","mercredi","jeudi",
                "vendredi","samedi","dimanche"};
        Service service = serviceRep.getService(nb);
        LocalDate localDate = LocalDate.now();
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
        int dayNb = dayOfWeek.getValue();
        String day = days[dayNb - 1];
        return service.getDay().equals(day);
    }

    /**
     * Permet de générer un rapport de synthèse
     *
     */
    public void makeReport(){
        formatCompt.formatReport(sessionRep, profRep);
    };

    /**
     * Permet d'effectuer la procédure comptable
     *
     */
    public void makeCompt() {
        makeReport();
        makeTEF();
        makeProfReport();
        makeClientReport();
    }

    /**
     * Permet de générer les fichier TEF
     *
     */
    private void makeTEF(){
        formatCompt.formatTEF(sessionRep, profRep);
    };

    /**
     * Permet de générer les fichiers de professionnel
     *
     */
    private void makeProfReport() {
        formatCompt.formatProf(sessionRep,profRep,clientRep);
    }

    /**
     * Permet de générer les fichiers de clients
     *
     */
    private void makeClientReport() {
        formatCompt.formatClient(sessionRep,profRep,clientRep);
    }

}
