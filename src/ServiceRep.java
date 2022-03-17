import java.io.File;
import java.lang.reflect.Array;
import java.security.Provider;
import java.util.Arrays;

public class ServiceRep {

    // --------------------------------------
    //               Attribut
    //---------------------------------------
    private Service[] services;
    private Service notService;

    // --------------------------------------
    //             Constructeur
    //---------------------------------------

    /**
     * Constructeur de ServiceRep
     *
     */
    public ServiceRep(){
        services = new Service[0];
        notService = new Service();
    }

    // --------------------------------------
    //             Getter/Setter
    //---------------------------------------

    /**
     * Getter de services
     *
     * @return L'attribut services
     */
    public Service[] getServices() {
        return services;
    }

    // --------------------------------------
    //               Méthode
    //---------------------------------------

    /**
     * Permet d'accéder à un service en particulier
     *
     * @param nb Le numéro d'un service
     * @return Un service ayant comme numéro celui passé en argument
     */
    public Service getService(String nb){
        for(int i = 0; i < services.length; i++) {
            if(services[i].getServiceNb().equals(nb)) {
                return services[i];
            }
        }

        return notService;
    };

    /**
     * Permet d'ajouter un service à l'attribut services
     *
     * @param service Un service
     */
    public void addService(Service service){
        int size = services.length;
        Service[] newServices = new Service[size+1];

        for(int i = 0; i < size; i++) {
            newServices[i] = services[i];
        }
        newServices[size] = service;
        services = newServices;
    };

    /**
     * Permet de supprimer un service de l'attribut services
     *
     * @param nb Un numéro de service
     */
    public void deleteService(String nb){
        int size = services.length;
        Service[] newServices = new Service[size-1];
        for(int i = 0; i < size; i++) {
            if(services[i].getServiceNb().equals(nb)) {
                for(int j = 0; j < i; j++) {
                    newServices[j] = services[j];
                }
                for(int j = i+1; j < size; j++) {
                    newServices[j-1] = services[j];
                }
            }
        }
        services = newServices;
    };

}
