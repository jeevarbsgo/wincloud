package FO;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CustomGraphReporter implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        int passed = 0, failed = 0, skipped = 0;

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult result : suiteResults.values()) {
                ITestContext context = result.getTestContext();
                passed += context.getPassedTests().size();
                failed += context.getFailedTests().size();
                skipped += context.getSkippedTests().size();
            }
        }

        try (FileWriter writer = new FileWriter(outputDirectory + "/CustomGraphReport.html")) {
            writer.write("<!DOCTYPE html>\n<html>\n<head>\n");
            writer.write("<title>TestNG Report with Chart</title>\n");
            writer.write("<script src='https://cdn.jsdelivr.net/npm/chart.js'></script>\n");
            writer.write("<style>\n");
            writer.write("  body { font-family: Arial, sans-serif; text-align: center; }\n");
            writer.write("  .chart-container { display: flex; justify-content: center; margin-top: 20px; }\n");
            writer.write("  #myChart { width: 250px !important; height: 250px !important; }\n");
            writer.write("</style>\n");
            writer.write("</head>\n<body>\n");

            writer.write("<h1>Test Summary</h1>\n");
            writer.write("<p>Passed: " + passed + "</p>\n");
            writer.write("<p>Failed: " + failed + "</p>\n");
            writer.write("<p>Skipped: " + skipped + "</p>\n");

            writer.write("<div class='chart-container'>\n");
            writer.write("<canvas id='myChart'></canvas>\n");
            writer.write("</div>\n");

            writer.write("<script>\n");
            writer.write("  const ctx = document.getElementById('myChart').getContext('2d');\n");
            writer.write("  new Chart(ctx, {\n");
            writer.write("    type: 'pie',\n");
            writer.write("    data: {\n");
            writer.write("      labels: ['Passed', 'Failed', 'Skipped'],\n");
            writer.write("      datasets: [{\n");
            writer.write("        data: [" + passed + ", " + failed + ", " + skipped + "],\n");
            writer.write("        backgroundColor: ['green', 'red', 'orange']\n");
            writer.write("      }]\n");
            writer.write("    },\n");
            writer.write("    options: {\n");
            writer.write("      plugins: {\n");
            writer.write("        title: {\n");
            writer.write("          display: true,\n");
            writer.write("          text: 'Test Results Distribution',\n");
            writer.write("          font: { size: 16 }\n");
            writer.write("        }\n");
            writer.write("      }\n");
            writer.write("    }\n");
            writer.write("  });\n");
            writer.write("</script>\n");

            writer.write("</body>\n</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
