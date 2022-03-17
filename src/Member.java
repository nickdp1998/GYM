public abstract class Member {

    // --------------------------------------
    //               Attribut
    //---------------------------------------
    private String name;
    private String email;
    private String address;
    private String city;
    private String province;
    private String postalCode;

    // --------------------------------------
    //             Constructeur
    //---------------------------------------


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
     * Setter de name
     *
     * @param name Un nouveau nom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter de email
     *
     * @return L'attribut email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter de email
     *
     * @param email Un nouveau email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter de address
     *
     * @return L'attribut address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter de address
     *
     * @param address Une nouvelle addresse
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter de city
     *
     * @return L'attribut city
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter de city
     *
     * @param city Une nouvelle ville
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter de province
     *
     * @return L'attribut province
     */
    public String getProvince() {
        return province;
    }

    /**
     * Setter de province
     *
     * @param province Une nouvelle province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Getter de postalCode
     *
     * @return L'attribut postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter de postalCode
     *
     * @param postalCode Un nouveau code postal
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    // --------------------------------------
    //               Méthode
    //---------------------------------------

    /**
     * Permet de mettre à jour le information d'un membre
     *
     * @param info Les nouvelles informations d'un membre
     */
    public abstract void update(String[] info);

}
