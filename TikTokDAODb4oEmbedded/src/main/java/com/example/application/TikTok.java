package com.example.application;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import com.example.model.entities.Video;

public class TikTok{
    public static ArrayList<Video> CargarVideos(String rutaCSV){
        String line = "";
        String delimiter = ","; 
        ArrayList<Video> arrVideos=new ArrayList<Video>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String primeraLinea = br.readLine();
            int cont=0;
            while ((line = br.readLine()) != null) {
                // Split each line by the comma
                System.out.println(cont);
                String[] values = line.split(delimiter);
                int fecha=Integer.parseInt((values[4].split("-")[0]+values[4].split("-")[1]+values[4].split("-")[2]).replaceAll("\"",""));
                Video Video=new Video(values[0],Float.parseFloat(values[1]),values[2],Integer.parseInt(values[3]),fecha,values[5]+values[6]+values[7]);
                arrVideos.add(Video);
                System.out.println(arrVideos.get(cont));
                cont++;
                
            }
            }catch (IOException e) {
                e.printStackTrace();
        }
        return arrVideos;
    }
        
  
    public static ArrayList<Video> BuscarVideosAutor(String Nombre, ArrayList<Video> arrVideos){
        ArrayList<Video> arrVideos2= new ArrayList<Video>();
        for(int i=0;i<arrVideos.size();i++){
            //System.out.println(arrVideos.get(i).getAutor());
            if(arrVideos.get(i).getAutor().equals(Nombre)){
                System.out.println(arrVideos.get(i));
                arrVideos2.add(arrVideos.get(i));
            }
        }
        return arrVideos2;
    }
    
    public static void CrearBinarioObjetos(ArrayList<Video> arrVideos, String rutaBinario){
        //declara el fichero
        File fichero = new File("FichTikTok.dat");
        FileOutputStream fileout;
        try {
            fileout = new FileOutputStream(fichero);
            ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
            for (int i=0;i<arrVideos.size(); i++){ //recorro los arrays
                dataOS.writeObject(arrVideos.get(i));
            }
            dataOS.close(); //cerrar stream de salid
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } //crea el flujo de salida //conecta el flujo de bytes al flujo de datos
        

    }
    public static ArrayList<Video> EliminarVideoFicBin(String rutaBinario, String usuario){
        Video Video; //defino la variable persona
        File fichero = new File ("FichTikTok.dat");
        ArrayList<Video> arrVideos=new ArrayList<Video>();
        ObjectInputStream dataIS=null;
        try {
            FileInputStream filein = new FileInputStream (fichero); //crea el flujo de entrada
            //conecta el flujo de bytes al flujo de datos
            dataIS = new ObjectInputStream(filein);
            while (true) { //lectura del fichero
            Video = (Video) dataIS.readObject(); //leer una Persona System.out.println("Nombre: " + persona.getNombre() + ", edad: " + persona.getEdad());
            if(Video.getAutor()==usuario){


            }else{
                arrVideos.add(Video);
            }
            
            }        
        }catch (Exception eo) { }
            //cerrar stream de entrada
        finally {
                // Asegúrate de que el flujo se cierre correctamente
                try {
                    if (dataIS != null) {
                        dataIS.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return arrVideos;
        
    }
    
/**public static void main(String[]args){
    Scanner scanner = new Scanner(System.in);
    String rutaCSV="C:\\Users\\maider\\Desktop\\Ciudad_jardin\\ADAT\\Examen2024\\Examen2024\\TikTok2.csv";
    int num=Integer.parseInt(args[0]);
    ArrayList<Video> arrVideos=CargarVideos(rutaCSV);
    System.out.println("Size"+arrVideos.size());
    if(num!=0){
        System.out.println("La aplicación tendrá un menú con varias opciones:");
        System.out.println("1.Buscar Videos por región.");
        System.out.println("2.Crear XML Videos.");
        System.out.println("3.Crear fichero binario objetos.");
        System.out.println("4.Eliminar Video fic. Binario objetos.");
        System.out.println("0.Salir.");
        
        if (num==1){
            
            System.out.println("Introduzca el nombre de la Video");
            String nombre = scanner.nextLine();
            System.out.println(nombre);
            ArrayList<Video> arrVideosConNombre=BuscarVideosAutor(nombre, arrVideos);
            for (int i=0;i<arrVideosConNombre.size();i++){
                System.out.println(arrVideosConNombre.get(i));
            }
        }if (num==2){
            CrearXMLVideos(arrVideos, "Videos.XML");
        }if (num==3){
            CrearBinarioObjetos(arrVideos, "TikTokBin.dat");
        }if (num==4){
            System.out.println("Introduzca un número de Video");
            String autor = scanner.next();
            ArrayList<Video> arrbVideos= EliminarVideoFicBin("TikTokBin.dat",autor);
            CrearBinarioObjetos(arrVideos,"Videos_sin_identificador.dat");
        }if (num>4 || num<0){
            System.out.println("Debe introducir un número entre el 0 y el 4");
        }
        
    }

}**/
}  
