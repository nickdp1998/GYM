public class Service {

    // --------------------------------------
    //               Attribut
    //---------------------------------------
    private String name;
    private String date;
    private String beginning;
    private String end;
    private String hour;
    private String day;
    private String capacity;
    private String profNb;
    private String serviceNb;
    private String cost;
    private String comment;

    // --------------------------------------
    //             Constructeur
    //---------------------------------------

    /**
     * Constructeur général de service
     *
     * @param nb Le numéro du service
     * @param info Toutes les infos pertinentes du service
     */
    public Service(String nb, String[] info) {
        serviceNb = nb;
        name = info[0];
        date = info[2];
        beginning = info[1];
        end = info[3];
        hour = info[4];
        day = info[5];
        capacity = info[6];
        profNb = info[7];
        cost = info[8];
        comment = info[9];
    }

    /**
     * Constructeur d'un service ayant comme numéro de service "0", permet d'identifier
     * les cas d'une recherche infructueuse d'un service
     *
     */
    public Service() {
        serviceNb = "0";
    }

    // --------------------------------------
    //             Getter/Setter
    //---------------------------------------

    /**
     * Getter de name
     *
     * @return L'attribut name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter de date
     *
     * @return L'attribut date
     */
    public String getDate(){
        return date;
    };

    /**
     * Getter de beginning
     *
     * @return L'attribut beginning
     */
    public String getBeginning(){
        return beginning;
    };

    /**
     * Getter de end
     *
     * @return L'attribut end
     */
    public String getEnd(){
        return end;
    };

    /**
     * Getter de hour
     *
     * @return L'attribut hour
     */
    public String getHour(){
        return hour;
    };

    /**
     * Setter de hour
     *
     * @param hour Une nouvelle heure
     */
    public void setHour(String hour){
        this.hour = hour;
    };

    /**
     * Getter de day
     *
     * @return L'attribut day
     */
    public String getDay(){
        return day;
    };

    /**
     * Setter de day
     *
     * @param day Une nouvelle journée
     */
    public void setDay(String day){
        this.day = day;
    };

    /**
     * Getter de capacity
     *
     * @return L'attribut capacity
     */
    public String getCapacity(){
        return capacity;
    };

    /**
     * Setter de capacity
     *
     * @param capacity Une nouvelle capacité
     */
    public void setCapacity(String capacity){
        this.capacity = capacity;
    };

    /**
     * Getter de profNb
     *
     * @return L'attribut profNb
     */
    public String getProfNb(){
        return profNb;
    };

    /**
     * Getter de serviceNb
     *
     * @return L'attribut serviceNb
     */
    public String getServiceNb(){
        return serviceNb;
    };

    /**
     * Getter de cost
     *
     * @return L'attribut cost
     */
    public String getCost(){
        return cost;
    };

    /**
     * Setter de cost
     *
     * @param cost Un nouveau frais
     */
    public void setCost(String cost){
        this.cost = cost;
    };

    /**
     * Getter de comment
     *
     * @return L'attribut comment
     */
    public String getComment(){
        return comment;
    };

    /**
     * Setter de comment
     *
     * @param comment Un nouveau comment
     */
    public void setComment(String comment){
        this.comment = comment;
    }

    // --------------------------------------
    //               Méthode
    //---------------------------------------


}
