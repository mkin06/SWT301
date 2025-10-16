package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface IPrinter {
    void print(String message);
}

class ConsolePrinter implements IPrinter {
    private static final Logger logger = LoggerFactory.getLogger(ConsolePrinter.class);

    @Override
    public void print(String message) {
        logger.info(message);
    }
}

class Report {
    private final IPrinter printer;

    public Report(IPrinter printer) {
        this.printer = printer;
    }

    public void generate(String reportContent) {
        printer.print(reportContent);
    }
}

public class TightCouplingExample {
    public static void main(String[] args) {
        IPrinter myPrinter = new ConsolePrinter();
        Report financialReport = new Report(myPrinter);

        financialReport.generate("Đang tạo báo cáo tài chính quý 3...");
        financialReport.generate("Báo cáo đã hoàn tất.");
    }
}