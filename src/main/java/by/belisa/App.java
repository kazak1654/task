package by.belisa;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Ñlass reads the values from the two files, sorts and displays them in the result file.
 *
 * @author Sokol;
 * @version 1.1
 */
public class App
{
    /**
     * Îbject scanner for the first file.
     */
    private Scanner scanner1;
    /**
     *  Îbject scanner for the second file.
     */
    private Scanner scanner2;
    private final String RESULT_FILE_PATH = "src/resources/result.txt";
    private final String INPUT_FILE_FIRST = "input1.txt";
    private final String INPUT_FILE_SECOND = "input2.txt";
    private final String LINE_SEPARATOR = "\r\n";
    private final String ERROR_MESSAGE = "Check files, input type must to be int!";


    public static void main( String[] args ) {
        App app=new App();
        app.init();
        app.sort();
        }
    /**
     * Method of sorting data.
     */
    private void sort() {try {
        if(scanner2.hasNext()){
            sortRecursion(scanner1, scanner2, Integer.valueOf(scanner2.nextLine()));
        } else{
            while (scanner1.hasNext()){
                //System.out.println("slaveScanner.hasNext() false  returnInt="+Integer.valueOf(scanner1.nextLine()));
                writeToFile(Integer.valueOf(scanner1.nextLine()));
            }
        }
    }catch (NumberFormatException e){
        System.err.println(ERROR_MESSAGE);
    }

    }

    /**
     * Method reads input files and creates a result file.
     */
    private void  init(){
        scanner1 =new Scanner(getClass().getClassLoader().getResourceAsStream(INPUT_FILE_FIRST));
        scanner2 =new Scanner(getClass().getClassLoader().getResourceAsStream(INPUT_FILE_SECOND));

        File file = new File(RESULT_FILE_PATH);
        if (file.exists()){
            file.delete();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Recursive method of sorting data.
     *
     * @param masterScannerIn  object scanner for masterScanner,
     * @param slaveScannerIn   object scanner for slaveScanner,
     * @param slaveVal current slave value.
     */
    private  void sortRecursion (Scanner masterScannerIn,Scanner slaveScannerIn,int slaveVal) {
        int masterVal=0;
        if (masterScannerIn.hasNext()){
            masterVal =Integer.valueOf(masterScannerIn.nextLine());
            if(masterVal<=slaveVal){
                writeToFile(masterVal);
                sortRecursion(masterScannerIn, slaveScannerIn, slaveVal);
            }else {
                writeToFile(slaveVal);
                sortRecursion(slaveScannerIn, masterScannerIn, masterVal);
            }
        }else{
            writeToFile(slaveVal);
            while (slaveScannerIn.hasNext()){
                writeToFile(Integer.valueOf(slaveScannerIn.nextLine()));
            }
        }
    }

    /**
     * Method writes  the  sort result to a file.
     *
     * @param inInt sorted value
     */
    private   synchronized void writeToFile(int inInt)  {
        PrintWriter printWriter = null;
        File file = new File(RESULT_FILE_PATH);
        try {
            if (!file.exists())
            file.createNewFile();
            printWriter = new PrintWriter(new FileOutputStream(RESULT_FILE_PATH, true));
            printWriter.write(""+inInt+LINE_SEPARATOR);
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }
}

