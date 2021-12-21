package com.pdfgenerate;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PdfDoc {
    private static final String
            OUTPUT_FILE_NAME = "/home/perennial/Documents/HelloWorld.pdf",
            LOGO_LOCATION = "/home/perennial/Documents/logo.png",
            TEXT = "Hello World";

    public static void main(String[] args) {
        createDocument(TEXT, OUTPUT_FILE_NAME, LOGO_LOCATION);
    }

    private static void createDocument(String text, String outputFileName, String logoLocation) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputFileName));
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA , 10, BaseColor.BLACK);
        Chunk chunk = new Chunk(text, font);

        try {
            document.add(chunk);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        addRows(table);
        try {
            addCustomRows(table, logoLocation);
            document.add(table);
        } catch (DocumentException | URISyntaxException | IOException e1) {
            e1.printStackTrace();
        }

        document.close();
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("patient Id","first name","last name","email","address","gender","phone","age","reason").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
    }

    private static void addRows(PdfPTable table) {
        table.addCell("");
        table.addCell("row 1, col 2");
        table.addCell("row 1, col 3");
    }

    private static void addCustomRows(PdfPTable table, String logoLocation) throws URISyntaxException, BadElementException, IOException {
        Path path = Paths.get(logoLocation);
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.scalePercent(10);

        PdfPCell imageCell = new PdfPCell(img);
        table.addCell(imageCell);

        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(horizontalAlignCell);

        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(verticalAlignCell);
    }
}
