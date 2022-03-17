import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveReport {

    // --------------------------------------
    //               Attribut
    //---------------------------------------


    // --------------------------------------
    //             Constructeur
    //---------------------------------------


    // --------------------------------------
    //             Getter/Setter
    //---------------------------------------


    // --------------------------------------
    //               Méthode
    //---------------------------------------

    /**
     * Permet d'enregistrer un raport de synthèse dans un fichier
     *
     * @param report L'ensemble des informations à écrire dans le fichier
     */
    public void save(String[] report){
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
        try {
            FileWriter fw = new FileWriter("Rapport_" + date + ".txt");
            BufferedWriter writer = new BufferedWriter(fw);
            for(int i = 0; i < report.length; i++) {
                writer.write(report[i]);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Permet d'enregistrer un fichier TEF
     *
     * @param name Le nom à donner au fichier
     * @param info Les informations à écrire dans le fichier
     */
    public void saveTEF(String name, String info) {
        try {
            FileWriter fw = new FileWriter(name + ".TEF");
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(info);
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Permet de créer le fichier d'un client ou d'un professionnel pour la procédure comptable
     *
     * @param name Le nom du membre
     * @param info Les informations à écrire dans le fichier
     */
    public void saveMember(String name, String[] info) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
        try {
            FileWriter fw = new FileWriter(name + "_" + date + ".txt");
            BufferedWriter writer = new BufferedWriter(fw);
            for(int i = 0; i < info.length; i++) {
                writer.write(info[i]);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
