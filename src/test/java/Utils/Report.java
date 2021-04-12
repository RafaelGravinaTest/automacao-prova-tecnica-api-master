package Utils;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.Description;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.StatusConfigurator;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Report {
    private static ExtentReports extentReport;
    private static ExtentTest logger;
    private static Description description;

    public Report(Description desc) {
        description = desc;
    }

    public static void create() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(getReportPath());
        htmlReporter.config().setEncoding("UTF-8");
        htmlReporter.config().setTheme(Theme.DARK);
        defineStatusHierarchy();
        extentReport = new ExtentReports();
        extentReport.attachReporter(htmlReporter);
    }

    public static void startTest(String description) {
        if (extentReport == null) {
            create();
        }

        logger = extentReport.createTest(description);
    }

    protected ExtentTest createChildStart(String strNomeTeste) {
        ExtentTest child = logger.createNode(strNomeTeste);
        return child;
    }

    protected void childLogInfo(ExtentTest child, String log) {
        child.log(Status.INFO, log);
    }

    protected void childLogPass(ExtentTest child, String log) {
        child.log(Status.PASS, log);
    }

    protected void childLogFail(ExtentTest child, String log) {
        child.log(Status.FAIL, log);
    }

    public static void log(Status logStatus, String message) {
        if (logger == null) {
            startTest("Falha! Log não iniciado");
        }
        logger.log(logStatus, message);
    }

    public static void logPass(String message) {
        log(Status.PASS, message);
    }

    public static void logFail(String message) {
        log(Status.FAIL, message);
    }

    public static void logInfo(String message) {
        log(Status.INFO, message);
    }

    public static void close() {
        if (extentReport != null) {
            extentReport.flush();
        } else {
            startTest("Falha na criação dos testes");
            log(Status.INFO, "O teste encerrou.");
            close();
        }
    }

    private static void defineStatusHierarchy() {
        List<Status> statusHierarchy = Arrays.asList(Status.FATAL, Status.FAIL, Status.ERROR, Status.PASS,
                Status.WARNING, Status.SKIP, Status.INFO, Status.DEBUG);
        StatusConfigurator.getInstance().setStatusHierarchy(statusHierarchy);
    }

    private static String getReportPath() {
        return "target/relatorios/" + description.getDisplayName().replace("tests.", "") + ".html";
    }
}
