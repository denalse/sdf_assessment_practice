package nus.iss;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println();

        try {
            String csvFile = args[0]; // mail.csv
            String templateFile = args[1]; // template.txt

            // load csv file
            List<MailData> mailData = load(csvFile);

            // read and merge the template
            mergeTemplate(mailData, templateFile);

        } catch (Exception e) {
            System.err.println("Error reading file! Please try again! ");
            e.printStackTrace();
        }

    }

    private static List<MailData> load(String dataCSV) throws IOException {

        List<MailData> dataList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(dataCSV);
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] input = line.split(",");
                // System.out.println("input: "+Arrays.toString(input));

                MailData data = new MailData(
                        input[0],
                        input[1],
                        input[2],
                        // in constructor is *int*, use this method to change)
                        Integer.parseInt(input[3]));

                dataList.add(data);
                // System.out.println(dataList);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    private static String readTemplate(String template) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(template));
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
        
        return sb.toString();
    }

    private static void mergeTemplate(List<MailData> dataList, String template) throws IOException {

        try {
            String temp = readTemplate(template);
            // System.out.println("temp: "+temp);
            
            for (MailData d : dataList) {
                String temp_ = temp;
                // Either formatAdress example works!!
                // String formatAddress = d.getAddress().replaceAll("\\\\n", "\n");
                String formatAddress = d.getAddress().replaceAll("\\\\n", System.lineSeparator());
                temp_ = temp_.replace("__address__", formatAddress);
                temp_ = temp_.replace("__first_name__", d.getFirstName());
                temp_ = temp_.replace("__years__", String.valueOf(d.getYears()) + " years");
                
                System.out.println(temp_);
                System.out.println("-------------------------------------------------");
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
