package ua.profitsoft.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class XmlWriter {
    public static void writeStatisticsToXml(Map<String, Integer> attributeCounts, String attribute) {
        String fileName = "statistics_by_" + attribute + ".xml";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<statistics>\n");

            for (Map.Entry<String, Integer> entry : attributeCounts.entrySet()) {
                writer.write("  <item>\n");
                writer.write("    <value>" + entry.getKey() + "</value>\n");
                writer.write("    <count>" + entry.getValue() + "</count>\n");
                writer.write("  </item>\n");
            }

            writer.write("</statistics>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
