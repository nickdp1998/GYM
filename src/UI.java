import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UI {

    // --------------------------------------
    //              Attribut
    //---------------------------------------
    private Request request;

    // --------------------------------------
    //            Constructeur
    //---------------------------------------

    /**
     * Constructeur de UI
     *
     * @param request Un objet request
     */
    public UI (Request request) {
        this.request = request;
    }


    // --------------------------------------
    //               Méthode
    //---------------------------------------

    /**
     * Permet d'obtenir la réponse à une question passé en entrée.
     *
     * @param message Une question à afficher
     * @return L'entrée à une question
     */
    private String getInfo(String message){
        Scanner sc= new Scanner(System.in);
        String s = "";
        Boolean good;

        while(true) {
            System.out.println(message);
            s = sc.nextLine() + "0"; //pour vérifier le dernier symbole
            good = checkFormat(s);
            if(good)
                break;
            else
                System.out.println("Format invalide");
        }

        return s.substring(0, s.length()-1);
    }

    /**
     * Vérifie le format d'une réponse d'un utilisateur (pas de ";", moins de 100 char en général)
     *
     * @param ans Réponse à une question
     * @return Vrai si le format est bon, faux sinon
     */
    private Boolean checkFormat(String ans) {
        String[] test = ans.split(";");

        if (test.length != 1) {
            return false;
        }

        return true;
    }

    /**
     * Vérifie le format d'un code postal
     *
     * @param postalCode Code postal
     * @return Vrai si le format du code postal est bon, faux sinon
     */
    private Boolean checkPostalCode(String postalCode) {
        if(postalCode.length() != 6)
            return false;

        if(!Pattern.matches("[a-zA-Z]", "" + postalCode.charAt(0)))
            return false;

        if(!Pattern.matches("[0-9]", "" + postalCode.charAt(1)))
            return false;

        if(!Pattern.matches("[a-zA-Z]", "" + postalCode.charAt(2)))
            return false;

        if(!Pattern.matches("[0-9]", "" + postalCode.charAt(3)))
            return false;

        if(!Pattern.matches("[a-zA-Z]", "" + postalCode.charAt(4)))
            return false;

        if(!Pattern.matches("[0-9]", "" + postalCode.charAt(5)))
            return false;

        return true;
    }

    /**
     * Vérifie le format d'une date
     *
     * @param date Une date sous format dd-MM-yyyy
     * @return Vrai si le format de la date est bon, faux sinon
     */
    private Boolean checkDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            dateTimeFormatter.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Vérifie le format d'une heure
     *
     * @param hour Une heure sous format HH:mm
     * @return Vrai si le format de l'heure est bon, faux sinon
     */
    private Boolean checkHour(String hour) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            dateTimeFormatter.parse(hour);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Vérifie le format d'une journée de la semaine
     *
     * @param day Une journée en français
     * @return Vrai si le format de la journée est bon, faux sinon
     */
    private Boolean checkDay(String day) {

        String[] days = new String[] {"lundi","mardi","mercredi","jeudi",
                            "vendredi","samedi","dimanche"};
        for(int i = 0 ; i < 7; i++) {
            if(day.equals(days[i])){
                return true;
            }
        }

        return false;
    }

    /**
     * Vérifie le format d'un frais
     *
     * @param cost Un frais sous format dd,cc
     * @return Vrai si le format du frais est bon, faux sinon
     */
    private Boolean checkCost(String cost) {
        String[] separate = cost.split(",");
        if(separate.length != 2) {
            return false;
        }
        if(separate[1].length() != 2) {
            return false;
        }
        try{
            int dollar = Integer.parseInt(separate[0]);
            int cent = Integer.parseInt(separate[1]);
            if(100 < dollar) {
                return false;
            }
            if(dollar == 100 && cent != 0) {
                return false;
            }
            if(dollar < 0 || cent < 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Vérifie le format d'une capacité pour les salles
     *
     * @param capacity Une capacité
     * @return Vrai si le format de la capacité est bon, faux sinon
     */
    private Boolean checkCapacity(String capacity) {
        try{
            int cap = Integer.parseInt(capacity);
            if(30 < cap || cap < 1)
                return false;

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Vérifie le format d'un code de service (3 chiffres)
     *
     * @param nb Un code de service
     * @return Vrai si le format du code est bon, faux sinon
     */
    private Boolean checkCode(String nb) {
        if(Pattern.matches("[0-9]{3}", nb))
            return true;

        return false;
    }

    /**
     * Permet d'ajouter un service en posant les questions appropriés à l'utilisateur
     *
     */
    public void addService(){
        Boolean good;
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        String s7 = "";
        String s8 = "";
        String s9 = "";
        String s10 = "";
        Scanner sc = new Scanner(System.in);
        s7 = getInfo("Veuillez entrer le numéro du professionnel");
        if(!request.checkProfNb(s7)) {
            System.out.println("Numéro invalide");
            return;
        }
        s1 = getInfo("Veuillez entrer le nom du service");
        while(true) {
            s10 = getInfo("Veuillez entrer le code à 3 chiffre du service");
            good = checkCode(s10);
            if(good) {
                break;
            } else {
                System.out.println("Format invalide");
            }
        }
        while(true) {
            s2 = getInfo("Veuillez entrer la date de début du service JJ-MM-AAAA");
            good = checkDate(s2);
            if(good) {
                break;
            } else {
                System.out.println("Format invalide");
            }
        }
        while(true) {
            s3 = getInfo("Veuillez entrer la date de fin du service sous format JJ-MM-AAAA");
            good = checkDate(s3);
            if(good) {
                break;
            } else {
                System.out.println("Format invalide");
            }
        }
        while(true) {
            s4 = getInfo("Veuillez entrer l'heure du service sous format HH:MM");
            good = checkHour(s4);
            if(good) {
                break;
            } else {
                System.out.println("Format invalide");
            }
        }
        while(true) {
            s5 = getInfo("Veuillez entrer la journée à laquelle les séances se dérouleront");
            s5 = s5.toLowerCase();
            good = checkDay(s5);
            if(good) {
                break;
            } else {
                System.out.println("Format invalide");
            }
        }
        while(true) {
            s6 = getInfo("Veuillez entrer la capacité maximale");
            good = checkCapacity(s6);
            if(good) {
                break;
            } else {
                System.out.println("Format invalide");
            }
        }
        while(true) {
            s8 = getInfo("Veuillez entrer les frais du service sous format dd,cc");
            good = checkCost(s8);
            if(good) {
                break;
            } else {
                System.out.println("Format invalide");
            }
        }
        while(true) {
            s9 = getInfo("Veuillez entrer un commentaire si désiré");
            good = s9.length() <= 100;
            if(good)
                break;
            else
                System.out.println("Maximum 100 caractère");
        }

        System.out.println("Entrez 'Confirmer' pour confirmer la création.");
        String s11 = sc.nextLine();
        if(s11.equals("Confirmer")) {
            String[] info = new String[]{s1, s2, s10, s3, s4, s5, s6, s7, s8, s9};
            request.addService(info);
        } else {
            System.out.println("Annulation");
        }
    };

    /**
     * Permet de mettre à jour un service en posant les questions appropriés à l'utilisateur
     *
     */
    public void updateService(){
        Boolean confirm = false;
        Boolean cancel = false;
        Boolean good;
        Scanner sc= new Scanner(System.in);
        System.out.println("Veuillez entrer le numéro du service");
        String s1 = sc.nextLine();
        System.out.println("Veuillez entrer le numéro du professionnel");
        String s2 = sc.nextLine();
        String[] nb = new String[] {s1,s2};
        String[] info = new String[5];
        String tempo;
        if(request.checkService(nb)) {
            while(!confirm) {
                Boolean notNumber = true;
                int choice = -1;
                while(notNumber) {
                    notNumber = false;
                    System.out.println("Choisir parmi les choix suivants :");
                    System.out.println("1. Modifier l'heure");
                    System.out.println("2. Modifier la journée");
                    System.out.println("3. Modifier la capacité");
                    System.out.println("4. Modifier les frais");
                    System.out.println("5. Modifier le commentaire");
                    System.out.println("6. Confirmer les modification");
                    System.out.println("0. Annuler les modification");
                    String s = sc.nextLine();
                    try {
                        choice = Integer.parseInt(s);
                    } catch (Exception e) {
                        System.out.println("Entrez un chiffre");
                        notNumber = true;
                    }
                }

                switch (choice) {
                    case 1:
                        while(true) {
                            tempo = getInfo("Entrez la nouvelle heure");
                            good = checkHour(tempo);
                            if(good) {
                                info[0] = tempo;
                                break;
                            } else {
                                System.out.println("Format invalide");
                            }
                        }
                        break;
                    case 2:
                        while(true) {
                            tempo = getInfo("Entrez la nouvelle journée");
                            tempo = tempo.toLowerCase();
                            good = checkDay(tempo);
                            if(good) {
                                info[1] = tempo;
                                break;
                            } else {
                                System.out.println("Format invalide");
                            }
                        }
                        break;
                    case 3:
                        while(true) {
                            tempo = getInfo("Entrez la nouvelle capacité");
                            good = checkCapacity(tempo);
                            if(good) {
                                info[2] = tempo;
                                break;
                            } else {
                                System.out.println("Format invalide");
                            }
                        }
                        break;
                    case 4:
                        while(true) {
                            tempo = getInfo("Entrez les nouveaux frais sous le format dd,cc");
                            good = checkCost(tempo);
                            if(good) {
                                info[3] = tempo;
                                break;
                            } else {
                                System.out.println("Format invalide");
                            }
                        }
                        break;
                    case 5:
                        while(true) {
                            tempo =  getInfo("Veuillez entrer un commentaire si désiré");
                            good = tempo.length() <= 100;
                            if(good) {
                                info[4] = tempo;
                                break;
                            } else {
                                System.out.println("Maximum 100 caractère");
                            }
                        }
                        break;
                    case 6:
                        confirm = true;
                        break;
                    case 7:
                        confirm = true;
                        cancel = true;
                        break;
                    default:
                        break;
                }
            }

            if(!cancel) {
                request.updateService(info, nb[0]);
                System.out.println("Informations mises à jours.");
            } else {
                System.out.println("Annulation");
            }

        } else {
            System.out.println("Numéro invalide");
        }
    }

    /**
     * Permet de supprimer un service en posant les quesiton appropriés à l'utlisateur
     *
     */
    public void deleteService(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Veuillez entrer le numéro du service");
        String s1 = sc.nextLine();
        System.out.println("Veuillez entrer le numéro du professionnel");
        String s2 = sc.nextLine();
        String[] nb = new String[] {s1,s2};
        if(request.checkService(nb)) {
            System.out.println("Veuillez entrer 'Confirmer' pour valider la suppression du service " +
                    "ayant comme numéro " + s1);
            String confirm = sc.nextLine();
            if (confirm.equals("Confirmer")) {
                request.deleteService(nb, true);
            } else {
                System.out.println("Suppression annulée.");
            }
        } else {
            System.out.println("Numéro invalide");
        }
    }

    /**
     * Permet d'ajouter un professionnel en posant les quesiton appropriés à l'utlisateur
     *
     */
    public void addProf() {
        Boolean good;
        Scanner sc= new Scanner(System.in);
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        while(true) {
            s1 = getInfo("Veuillez entrer le nom et prénom");
            good = s1.length() <= 25 && 1 <= s1.length();
            if(good)
                break;
            else
                System.out.println("Format erroné");
        }
        while(true) {
            s2 = getInfo("Veuillez entrer l'email");
            good = 1 <= s2.length();
            if(good)
                break;
            else
                System.out.println("Format erroné");
        }
        while(true) {
            s3 = getInfo("Veuillez entrer la rue et l'adresse");
            good = s3.length() <= 25 && 1 <= s3.length();
            if(good)
                break;
            else
                System.out.println("Format erroné");
        }
        while(true) {
            s4 = getInfo("Veuillez entrer la ville");
            good = s4.length() <= 14 && 1 <= s4.length();
            if(good)
                break;
            else
                System.out.println("Format erroné");
        }
        while(true) {
            s5 = getInfo("Veuillez entrer la province");
            good = s5.length() <= 2 && 1 <= s5.length();
            if(good)
                break;
            else
                System.out.println("Format erroné");
        }
        while(true) {
            s6 = getInfo("Veuillez entrer le code postal");
            good = checkPostalCode(s6);
            if(good)
                break;
            else
                System.out.println("Format invalide");
        }
        System.out.println("Entrez 'Confirmer' pour confirmer l'inscription.");
        String s7 = sc.nextLine();
        if(s7.equals("Confirmer")) {
            String[] info = new String[]{s1, s2, s3, s4, s5, s6};
            request.addProf(info);
        } else {
            System.out.println("Annulation");
        }
    }

    /**
     * Permet de mettre à jour un professionnel en posant les quesiton appropriés à l'utlisateur
     *
     */
    public void updateProf() {
        Boolean confirm = false;
        Boolean cancel = false;
        Boolean good;
        Scanner sc= new Scanner(System.in);
        System.out.println("Veuillez entrer le numéro du professionnel");
        String nb = sc.nextLine();
        String[] info = new String[6];
        String tempo;
        if(request.checkProfNb(nb)) {
            while(!confirm) {
                Boolean notNumber = true;
                int choice = -1;
                while(notNumber) {
                    notNumber = false;
                    System.out.println("Choisir parmi les choix suivants :");
                    System.out.println("1. Modifier le nom");
                    System.out.println("2. Modifier l'email");
                    System.out.println("3. Modifier l'adresse");
                    System.out.println("4. Modifier la ville");
                    System.out.println("5. Modifier la province");
                    System.out.println("6. Modifier le code postal");
                    System.out.println("7. Confirmer les modification");
                    System.out.println("0. Annuler les modification");
                    String s = sc.nextLine();
                    try {
                        choice = Integer.parseInt(s);
                    } catch (Exception e) {
                        System.out.println("Entrez un chiffre");
                        notNumber = true;
                    }
                }

                switch (choice) {
                    case 1:
                        while(true) {
                            tempo = getInfo("Entrez le nom");
                            good = tempo.length() <= 25 && 1 <= tempo.length();
                            if(good) {
                                info[0] = tempo;
                                break;
                            } else {
                                System.out.println("Format erroné");
                            }
                        }
                        break;
                    case 2:
                        while(true) {
                            tempo = getInfo("Entrez l'email");
                            good = 1 <= tempo.length();
                            if(good) {
                                info[1] = tempo;
                                break;
                            } else {
                                System.out.println("Format erroné");
                            }
                        }

                        break;
                    case 3:
                        while(true) {
                            tempo = getInfo("Entrez l'adresse");
                            good = tempo.length() <= 25 && 1 <= tempo.length();
                            if(good) {
                                info[2] = tempo;
                                break;
                            } else {
                                System.out.println("Format erroné");
                            }
                        }
                        break;
                    case 4:
                        while(true) {
                            tempo = getInfo("Entrez la ville");
                            good = tempo.length() <= 14 && 1 <= tempo.length();
                            if(good) {
                                info[3] = tempo;
                                break;
                            } else {
                                System.out.println("Format erroné");
                            }
                        }
                        break;
                    case 5:
                        while(true) {
                            tempo = getInfo("Entrez la province");
                            good = tempo.length() <= 2 && 1 <= tempo.length();
                            if(good) {
                                info[4] = tempo;
                                break;
                            } else {
                                System.out.println("Format erroné");
                            }
                        }
                        break;
                    case 6:
                        while(true) {
                            tempo = getInfo("Entrez le code postal");
                            good = checkPostalCode(tempo);
                            if(good) {
                                info[5] = tempo;
                                break;
                            } else {
                                System.out.println("Format erroné");
                            }
                        }
                        break;
                    case 7:
                        confirm = true;
                        break;
                    case 0:
                        confirm = true;
                        cancel = true;
                        break;
                    default:
                        break;
                }
            }

            if(!cancel) {
                request.updateProf(info, nb);
                System.out.println("Informations mises à jours.");
            } else {
                System.out.println("Annulation");
            }

        } else {
            System.out.println("Numéro invalide");
        }
    }

    /**
     * Permet de supprimer un professionnel en posant les quesiton appropriés à l'utlisateur
     *
     */
    public void deleteProf() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Veuillez entrer le numéro du professionnel");
        String nb = sc.nextLine();
        System.out.println("Veuillez entrer 'Confirmer' pour valider la suppression du compte " +
                "professionnel ayant comme numéro " + nb);
        String confirm = sc.nextLine();
        if(confirm.equals("Confirmer")) {
            request.deleteProf(nb);
        } else {
            System.out.println("Suppression annulée.");
        }
    }

    /**
     * Permet d'ajouter un client en posant les quesiton appropriés à l'utlisateur
     *
     */
    public void addClient() {
        Boolean good;
        Scanner sc= new Scanner(System.in);
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        String s5 = "";
        String s6 = "";
        while(true) {
            s1 = getInfo("Veuillez entrer le nom et prénom");
            good = s1.length() <= 25 && 1 <= s1.length();
            if(good)
                break;
            else
                System.out.println("Format erroné");
        }
        while(true) {
            s2 = getInfo("Veuillez entrer l'email");
            good = 1 <= s2.length();
            if(good)
                break;
            else
                System.out.println("Format erroné");
        }
        while(true) {
            s3 = getInfo("Veuillez entrer la rue et l'adresse");
            good = s3.length() <= 25 && 1 <= s3.length();
            if(good)
                break;
            else
                System.out.println("Format erroné");
        }
        while(true) {
            s4 = getInfo("Veuillez entrer la ville");
            good = s4.length() <= 14 && 1 <= s4.length();
            if(good)
                break;
            else
                System.out.println("Format erroné");
        }
        while(true) {
            s5 = getInfo("Veuillez entrer la province");
            good = s5.length() <= 2 && 1 <= s5.length();
            if(good)
                break;
            else
                System.out.println("Format erroné");
        }
        while(true) {
            s6 = getInfo("Veuillez entrer le code postal");
            good = checkPostalCode(s6);
            if(good)
                break;
            else
                System.out.println("Format invalide");
        }
        System.out.println("Entrez 'Confirmer' pour confirmer l'inscription.");
        String s7 = sc.nextLine();
        if(s7.equals("Confirmer")) {
            String[] info = new String[]{s1, s2, s3, s4, s5, s6};
            request.addClient(info);
        } else {
            System.out.println("Annulation");
        }
    }

    /**
     * Permet de mettre à jour un client en posant les quesiton appropriés à l'utlisateur
     *
     */
    public void updateClient() {
        Boolean confirm = false;
        Boolean cancel = false;
        Boolean good;
        Scanner sc= new Scanner(System.in);
        System.out.println("Veuillez entrer le numéro du client");
        String nb = sc.nextLine();
        String[] info = new String[6];
        String tempo;
        if(request.checkClientNb(nb)) {
            while(!confirm) {
                Boolean notNumber = true;
                int choice = -1;
                while(notNumber) {
                    notNumber = false;
                    System.out.println("Choisir parmi les choix suivants :");
                    System.out.println("1. Modifier le nom");
                    System.out.println("2. Modifier l'email");
                    System.out.println("3. Modifier l'adresse");
                    System.out.println("4. Modifier la ville");
                    System.out.println("5. Modifier la province");
                    System.out.println("6. Modifier le code postal");
                    System.out.println("7. Confirmer les modification");
                    System.out.println("0. Annuler les modification");
                    String s = sc.nextLine();
                    try {
                        choice = Integer.parseInt(s);
                    } catch(Exception e) {
                        System.out.println("Entrez un chiffre");
                        notNumber = true;
                    }
                }

                switch (choice) {
                    case 1:
                        while(true) {
                            tempo = getInfo("Entrez le nom");
                            good = tempo.length() <= 25 && 1 <= tempo.length();
                            if(good) {
                                info[0] = tempo;
                                break;
                            } else {
                                System.out.println("Format erroné");
                            }
                        }
                        break;
                    case 2:
                        while(true) {
                            tempo = getInfo("Entrez l'email");
                            good = 1 <= tempo.length();
                            if(good) {
                                info[1] = tempo;
                                break;
                            } else {
                                System.out.println("Format erroné");
                            }
                        }

                        break;
                    case 3:
                        while(true) {
                            tempo = getInfo("Entrez l'adresse");
                            good = tempo.length() <= 25 && 1 <= tempo.length();
                            if(good) {
                                info[2] = tempo;
                                break;
                            } else {
                                System.out.println("Format erroné");
                            }
                        }
                        break;
                    case 4:
                        while(true) {
                            tempo = getInfo("Entrez la ville");
                            good = tempo.length() <= 14 && 1 <= tempo.length();
                            if(good) {
                                info[3] = tempo;
                                break;
                            } else {
                                System.out.println("Format erroné");
                            }
                        }
                        break;
                    case 5:
                        while(true) {
                            tempo = getInfo("Entrez la province");
                            good = tempo.length() <= 2 && 1 <= tempo.length();
                            if(good) {
                                info[4] = tempo;
                                break;
                            } else {
                                System.out.println("Format erroné");
                            }
                        }
                        break;
                    case 6:
                        while(true) {
                            tempo = getInfo("Entrez le code postal");
                            good = checkPostalCode(tempo);
                            if(good) {
                                info[5] = tempo;
                                break;
                            } else {
                                System.out.println("Format erroné");
                            }
                        }
                        break;
                    case 7:
                        confirm = true;
                        break;
                    case 0:
                        confirm = true;
                        cancel = true;
                        break;
                    default:
                        break;
                }
            }
            if(!cancel) {
                request.updateClient(info, nb);
                System.out.println("Informations mises à jours.");
            } else {
                System.out.println("Annulation");
            }
            
        } else {
            System.out.println("Numéro invalide");
        }
    }

    /**
     * Permet de supprimer un client en posant les quesiton appropriés à l'utlisateur
     *
     */
    public void deleteClient() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Veuillez entrer le numéro du client");
        String nb = sc.nextLine();
        System.out.println("Veuillez entrer 'Confirmer' pour valider la suppression du compte " +
                "client ayant comme numéro " + nb);
        String confirm = sc.nextLine();
        if(confirm.equals("Confirmer")) {
            request.deleteClient(nb);
        } else {
            System.out.println("Suppression annulée.");
        }
    }

    /**
     * Vérifie la validité d'un client
     *
     * @param nb Un numéro de client
     * @return Vrai si le numéro est existant et valide
     */
    public Boolean clientAcces(String nb) {
        return request.clientAcces(nb);
    }

    /**
     * Permet de consulter le répertoire des séances de la journée présente
     *
     */
    public void checkRepertoire() {
        request.checkRepertoire();
    }

    /**
     * Permet d'inscrire un client à une séance
     *
     * @param nb Le numéro d'un client
     */
    public void insSession(String nb) {
        Scanner sc = new Scanner(System.in);
        String s1 = getInfo("Veuillez entrer le code du service.");
        if(!request.checkServiceNb(s1)) {
            System.out.println("Numéro invalide");
            return;
        }
        if(!request.checkSessionDay(s1)) {
            System.out.println("Journée invalide");
            return;
        }
        request.showService(s1);
        String s2 = getInfo("Veuillez entrer un commentaire (facultatif).");

        System.out.println("Veuillez entrer 'Confirmer' pour valider l'inscription");
        String confirm = sc.nextLine();
        if(confirm.equals("Confirmer")) {
            String[] info = new String[]{s1, nb, s2};
            request.insSession(info);
        } else {
            System.out.println("Inscription annulée.");
        }

    }

    /**
     * Permet de confirmer la présence d'un client à une séance
     *
     */
    public void confSession() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Veuillez entrer le code du service.");
        String s1 = sc.nextLine();
        if(!request.checkServiceNb(s1)) {
            System.out.println("Numéro invalide");
            return;
        }
        request.showSession(s1);
        System.out.println("Veuillez entrer le numéro du client pour confirmer l'inscription.");
        String s2 = sc.nextLine();
        System.out.println("Veuillez entrer un commentaire (facultatif).");
        String s3 = sc.nextLine();

        System.out.println("Veuillez entrer 'Confirmer' pour valider l'inscription");
        String confirm = sc.nextLine();
        if(confirm.equals("Confirmer")) {
            String[] info = new String[]{s1, s2, s3};
            request.confSession(info);
        } else {
            System.out.println("Confirmation annulée.");
        }
    }

    /**
     * Permet à un professionnel de consulter une séance à une date donnée
     *
     * @param nb Le numéro d'un professionnel
     */
    public void checkSession(String nb) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Veuillez entrer le numéro du service");
        String s1 = sc.nextLine();
        String[] nbs = new String[] {s1,nb};
        if(request.checkService(nbs)) {
            System.out.println("Veuillez entrer la date du service sous format jj-mm-aaaa");
            String s3 = sc.nextLine();
            String[] info = new String[] {s1,s3};
            request.showIns(info);
        } else {
            System.out.println("Numéro invalide");
        }
    }

    /**
     * Permet de créer un rapport de synthèse
     *
     */
    public void makeReport() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Veuillez entrer votre mot de passe (0 pour les tests)");
        String s1 = sc.nextLine();
        if(s1.equals("0")) {
            request.makeReport();
        } else {
            System.out.println("Mot de passe invalide");
        }
    }

    /**
     * Permet d'effectuer la procédure comptable
     *
     */
    public void makeCompt() {
        request.makeCompt();
    }

    // --------------------------------------
    //                Main
    //---------------------------------------
    public static void main(String arg[]){
        Request request = new Request();
        Scanner sc= new Scanner(System.in);
        UI ui = new UI(request);
        Boolean running = true;
        Boolean connected;
        Boolean good;
        Boolean notNumber;
        int choice1 = -1;
        int choice2 = -1;

        while(running) {
            connected = true;
            notNumber = true;
            while (notNumber) {
                System.out.println("Veuillez entrer un chiffre parmi les choix suivants :");
                System.out.println("1. Se connecter en tant qu'agent");
                System.out.println("2. Se connecter en tant que client");
                System.out.println("3. Se connecter en tant que professionnel");
                System.out.println("0. Quitter");
                try {
                    String s1 = sc.nextLine();
                    choice1 = Integer.parseInt(s1);
                    notNumber = false;
                } catch (Exception e) {
                    System.out.println("Format invalide");
                }

            }
            switch (choice1) {
                case 1:
                    while (connected) {
                        notNumber = true;
                        while (notNumber) {
                            System.out.println("Veuillez entrer un chiffre parmi les choix suivants :");
                            System.out.println("1. Ajouter un service");
                            System.out.println("2. Mettre à jour un service");
                            System.out.println("3. Supprimer un service");
                            System.out.println("4. Ajouter un professionnel");
                            System.out.println("5. Mettre à jour un professionnel");
                            System.out.println("6. Supprimer un professionnel");
                            System.out.println("7. Ajouter un client");
                            System.out.println("8. Mettre à jour un client");
                            System.out.println("9. Supprimer un client");
                            System.out.println("10. Générer un rapport de synthèse.");
                            System.out.println("11. Exécuter la procédure comptable.");
                            System.out.println("0. Quitter");
                            try {
                                String s2 = sc.nextLine();
                                choice2 = Integer.parseInt(s2);
                                notNumber = false;
                            } catch (Exception e) {
                                System.out.println("Format invalide");
                            }
                        }

                        switch (choice2) {
                            case 1:
                                ui.addService();
                                break;
                            case 2:
                                ui.updateService();
                                break;
                            case 3:
                                ui.deleteService();
                                break;
                            case 4:
                                ui.addProf();
                                break;
                            case 5:
                                ui.updateProf();
                                break;
                            case 6:
                                ui.deleteProf();
                                break;
                            case 7:
                                ui.addClient();
                                break;
                            case 8:
                                ui.updateClient();
                                break;
                            case 9:
                                ui.deleteClient();
                                break;
                            case 10:
                                ui.makeReport();
                                break;
                            case 11:
                                ui.makeCompt();
                                break;
                            case 0:
                                connected = false;
                                break;
                            default:
                                System.out.println("Chiffre invalide");
                        }
                        if(choice2 != 0) {
                            System.out.println("Appuez sur 'Entrée' pour revenir aux sélections");
                            sc.nextLine();
                        }
                    }
                    break;
                case 2:
                    System.out.println("Veuillez entrer le numéro du client");
                    String s2 = sc.nextLine();
                    good = ui.clientAcces(s2);
                    if(good) {
                        while (connected) {
                            notNumber = true;
                            while (notNumber) {
                                System.out.println("Veuillez entrer un chiffre parmi les choix suivants :");
                                System.out.println("1. S'inscrire à un séance");
                                System.out.println("2. Vérifier la validité (QR)");
                                System.out.println("3. Afficher le répertoire de séance");
                                System.out.println("0. Quitter");
                                try {
                                    String s3 = sc.nextLine();
                                    choice2 = Integer.parseInt(s3);
                                    notNumber = false;
                                } catch (Exception e) {
                                    System.out.println("Format invalide");
                                }
                            }

                            switch (choice2) {
                                case 1:
                                    ui.insSession(s2);
                                    break;
                                case 2:
                                    ui.clientAcces(s2);
                                    break;
                                case 3:
                                    ui.checkRepertoire();
                                    break;
                                case 0:
                                    connected = false;
                                    break;
                                default:
                                    System.out.println("Chiffre invalide");
                            }
                            if(choice2 != 0) {
                                System.out.println("Appuez sur 'Entrée' pour revenir aux sélections");
                                sc.nextLine();
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Veuillez entrer votre numéro de professionnel");
                    s2 = sc.nextLine();
                    if(request.checkProfNb(s2)) {
                        while (connected) {
                            notNumber = true;
                            while (notNumber) {
                                System.out.println("Veuillez entrer un chiffre parmi les choix suivants :");
                                System.out.println("1. Consulter une séance");
                                System.out.println("2. Confirmer une inscription");
                                System.out.println("3. Afficher le répertoire de séance");
                                System.out.println("0. Quitter");
                                try {
                                    String s3 = sc.nextLine();
                                    choice2 = Integer.parseInt(s3);
                                    notNumber = false;
                                } catch (Exception e) {
                                    System.out.println("Format invalide");
                                }
                            }

                            switch (choice2) {
                                case 1:
                                    ui.checkSession(s2);
                                    break;
                                case 2:
                                    ui.confSession();
                                    break;
                                case 3:
                                    ui.checkRepertoire();
                                    break;
                                case 0:
                                    connected = false;
                                    break;
                                default:
                                    System.out.println("Chiffre invalide");
                            }
                            if(choice2 != 0) {
                                System.out.println("Appuez sur 'Entrée' pour revenir aux sélections");
                                sc.nextLine();
                            }
                        }
                    } else {
                        System.out.println("Numéro invalide");
                    }
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Chiffre invalide");
            }


        }

    };


}
