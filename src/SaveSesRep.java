import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveSesRep {

    public void saveInc(String[] ins){
        try {
            FileWriter fw = new FileWriter("Inscription.txt",true);
            BufferedWriter writer = new BufferedWriter(fw);
            for(int i = 0; i < ins.length; i++) {
                writer.write(ins[i]);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    };

    public void saveConf(String[] conf){
        try {
            FileWriter fw = new FileWriter("Confirmation.txt",true);
            BufferedWriter writer = new BufferedWriter(fw);
            for(int i = 0; i < conf.length; i++) {
                writer.write(conf[i]);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    };

}