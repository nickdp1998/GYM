import java.io.File;

public class ClientRep {

    // --------------------------------------
    //               Attribut
    //---------------------------------------
    private Client[] clients;
    private Client notClient;

    // --------------------------------------
    //             Constructeur
    //---------------------------------------

    /**
     * Constucteur de ClientRep
     *
     */
    public ClientRep() {
        clients = new Client[0];
        notClient = new Client();
    }

    // --------------------------------------
    //             Getter/Setter
    //---------------------------------------

    /**
     * Getter de clients
     *
     * @return L'attribut clients
     */
    public Client[] getClients() {
        return clients;
    }

    // --------------------------------------
    //               Méthode
    //---------------------------------------

    /**
     * Permet d'accéder à un client en particulier
     *
     * @param nb Le numéro d'un client
     * @return Un client ayant comme numéro celui passé en argument
     */
    public Client getClient(String nb){
        for(int i = 0; i < clients.length; i++) {
            if(clients[i].getClientNb().equals(nb)) {
                return clients[i];
            }
        }

        return notClient;
    };

    /**
     * Permet d'ajouter un client à l'attribut clients
     *
     * @param client Un client
     */
    public void addClient(Client client){
        int size = clients.length;
        Client[] newClients = new Client[size+1];

        for(int i = 0; i < size; i++) {
            newClients[i] = clients[i];
        }
        newClients[size] = client;
        clients = newClients;
    };

    /**
     * Permet de supprimer un client
     *
     * @param nb Le numéro d'un client
     */
    public void deleteClient(String nb){
        int size = clients.length;
        Client[] newClients = new Client[size-1];
        for(int i = 0; i < size; i++) {
            if(clients[i].getClientNb().equals(nb)) {
                for(int j = 0; j < i; j++) {
                    newClients[j] = clients[j];
                }
                for(int j = i+1; j < size; j++) {
                    newClients[j-1] = clients[j];
                }
            }
        }
        clients = newClients;
    };

}
