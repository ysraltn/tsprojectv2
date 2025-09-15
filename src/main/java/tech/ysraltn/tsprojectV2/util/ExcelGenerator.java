package tech.ysraltn.tsprojectV2.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tech.ysraltn.tsprojectV2.dto.CycleDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExcelGenerator {

    public static ByteArrayOutputStream exportCyclesPivot(List<CycleDto> cycleDtos) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Cycles");

        // Başlıklar
        Row header = sheet.createRow(0);
        String[] columns = {
                "Kurum", "Sahibi", "Seri No", "Marka", "Model", "Yıl",
                "Ocak",
                "Şubat", "Tüketim Şubat",
                "Mart", "Tüketim Mart",
                "Nisan", "Tüketim Nisan",
                "Mayıs", "Tüketim Mayıs",
                "Haziran", "Tüketim Haziran",
                "Temmuz", "Tüketim Temmuz",
                "Ağustos", "Tüketim Ağustos",
                "Eylül", "Tüketim Eylül",
                "Ekim", "Tüketim Ekim",
                "Kasım", "Tüketim Kasım",
                "Aralık", "Tüketim Aralık"
        };
        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }

        // Sütun genişlikleri
        sheet.setColumnWidth(0, 25 * 256); // Kurum
        sheet.setColumnWidth(1, 25 * 256); // Sahibi
        sheet.setColumnWidth(2, 15 * 256); // Seri No

        // Ürün bazlı gruplama
        Map<String, Map<Integer, Integer>> data = new LinkedHashMap<>();
        Map<String, CycleDto> infoMap = new LinkedHashMap<>();

        for (CycleDto dto : cycleDtos) {
            String key = dto.getProductId() + "-" + dto.getYear();
            data.putIfAbsent(key, new HashMap<>());
            infoMap.putIfAbsent(key, dto);
            data.get(key).put(dto.getMonth(), dto.getCycleCount());
        }

        // Satırları oluştur
        int rowNum = 1;
        for (String key : data.keySet()) {
            CycleDto dto = infoMap.get(key);
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(dto.getInstitutionName());
            row.createCell(1).setCellValue(dto.getOwnerName());
            row.createCell(2).setCellValue(dto.getProductSerial());
            row.createCell(3).setCellValue(dto.getProductBrand());
            row.createCell(4).setCellValue(dto.getProductModel());
            row.createCell(5).setCellValue(dto.getYear());

            Map<Integer, Integer> monthMap = data.get(key);
            int col = 6;
            Integer previous = null;

            for (int m = 1; m <= 12; m++) {
                Integer current = monthMap.get(m);
                Cell cycleCell = row.createCell(col++);

                if (current != null && current != 0) {
                    cycleCell.setCellValue(current);
                } else {
                    cycleCell.setBlank();
                }

                if (m > 1) {
                    Cell diffCell = row.createCell(col++);
                    if (current != null && previous != null) {
                        int diff = current - previous;
                        if (diff != 0) {
                            diffCell.setCellValue(diff);
                        } else {
                            diffCell.setBlank();
                        }
                    } else {
                        diffCell.setBlank();
                    }
                }

                previous = current;
            }
        }

        // Yazdır
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return out;
    }

    public static String generateFileName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy-HH.mm");
        return "dongu-" + LocalDateTime.now().format(formatter) + ".xlsx";
    }
}
