
package com.gitrekt.resort.model.services;

import com.gitrekt.resort.model.entities.Bill;
import com.gitrekt.resort.model.entities.BillItem;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import javax.print.PrintService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class BillService {
    
    public void printBill(Bill bill) throws IOException, PrinterException {
        // TODO: Remove test code;
        for(int i = 0; i < 20; i++) {
            bill.getCharges().add(new BillItem("Test bill item name printed here", 15.52623, 3));
        }

        BillPdfGenerator pdfGenerator = new BillPdfGenerator(bill);
        PDDocument pdf = pdfGenerator.getBillAsPdf();
        
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintService(choosePrinter());
        job.setPageable(new PDFPageable(pdf));
        job.print();
        pdf.close();
    }
    
    public static PrintService choosePrinter() {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        if(printJob.printDialog()) {
            return printJob.getPrintService();          
        }
        else {
            return null;
        }
    }
    
}
