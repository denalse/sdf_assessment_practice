package nus.iss;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println();

        try {
            String csvFile = args[0]; // mail.csv
            String templateFile = args[1]; // template.txt

            // System.out.println(">>>" + csvFile + "///" + templateFile);

            // load csv file
            List<MailData> dataCSV = load(csvFile);

            // read and merge the template
            mergeTemplate(dataCSV, templateFile);

        } catch (Exception e) {
            System.err.println("Error reading file! Please try again! ");
            e.printStackTrace();
        }

    }

    private static List<MailData> load(String csvFile) throws IOException {

        List<MailData> dataList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] input = line.split(",");
                MailData d = new MailData(
                        input[0], input[1], input[2],
                        Integer.parseInt(input[3]));

                dataList.add(d);

            }

            br.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    private static void mergeTemplate(List<MailData> dataList, String temp) throws IOException {

        try {
            String tempData = readTemplate(temp);
            
            for (MailData d : dataList) {
                // String address
                String newTemp = tempData;
                newTemp = newTemp.replace("__address__", d.getAddress().replace("\\n", "\n") + "\n\n");
                newTemp = newTemp.replace("__first_name__", d.getFirstName());
                newTemp = newTemp.replace(",", "\n");
                newTemp = newTemp.replace("__years__", String.valueOf(d.getYears()));
                
                System.out.println(newTemp);
                System.out.println("________________________________________");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String readTemplate(String mail_data) throws IOException {
        Path path = Paths.get(mail_data);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line);
        }
        return sb.toString();
    }


}
