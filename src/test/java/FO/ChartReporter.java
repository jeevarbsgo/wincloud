package FO;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ChartReporter implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        int passed = 0;
        int failed = 0;
        int skipped = 0;

        for (ISuite suite : suites) {
            for (ISuiteResult result : suite.getResults().values()) {
                ITestContext context = result.getTestContext();
                passed += context.getPassedTests().size();
                failed += context.getFailedTests().size();
                skipped += context.getSkippedTests().size();
            }
        }

        String html = generateHtml(passed, failed, skipped);
        try (FileWriter writer = new FileWriter(outputDirectory + "/TestNG_BarChart_Report.html")) {
            writer.write(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateHtml(int passed, int failed, int skipped) {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>TestNG Bar Chart Report</title>\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>\n" +
                "    <style>\n" +
                "        body { font-family: Arial, sans-serif; text-align: center; }\n" +
                "        .chart-container { display: flex; justify-content: center; margin-top: 20px; }\n" +
                "        #barChart { width: 300px !important; height: 200px !important; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Test Summary</h1>\n" +
                "    <p>Passed: " + passed + "</p>\n" +
                "    <p>Failed: " + failed + "</p>\n" +
                "    <p>Skipped: " + skipped + "</p>\n" +
                "    <div class=\"chart-container\">\n" +
                "        <canvas id=\"barChart\"></canvas>\n" +
                "    </div>\n" +
                "    <script>\n" +
                "        const ctx = document.getElementById('barChart').getContext('2d');\n" +
                "        new Chart(ctx, {\n" +
                "            type: 'bar',\n" +
                "            data: {\n" +
                "                labels: ['Passed', 'Failed', 'Skipped'],\n" +
                "                datasets: [{\n" +
                "                    label: 'Test Results',\n" +
                "                    data: [" + passed + ", " + failed + ", " + skipped + "],\n" +
                "                    backgroundColor: ['green', 'red', 'orange'],\n" +
                "                    maxBarThickness: 40\n" +
                "                }]\n" +
                "            },\n" +
                "            options: {\n" +
                "                responsive: false,\n" +
                "                plugins: {\n" +
                "                    legend: { display: false },\n" +
                "                    title: {\n" +
                "                        display: true,\n" +
                "                        text: 'TestNG Bar Chart Summary',\n" +
                "                        font: { size: 16 }\n" +
                "                    }\n" +
                "                },\n" +
                "                scales: {\n" +
                "                    y: {\n" +
                "                        beginAtZero: true,\n" +
                "                        precision: 0,\n" +
                "                        ticks: { stepSize: 1 },\n" +
                "                        title: { display: true, text: 'Tests Count' }\n" +
                "                    },\n" +
                "                    x: {\n" +
                "                        title: { display: true, text: 'Status' }\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        });\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";
    }
}
