package tech.ysraltn.tsprojectV2.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import tech.ysraltn.tsprojectV2.service.export.ExcelExportService;
import tech.ysraltn.tsprojectV2.util.ExcelGenerator;

import java.io.IOException;

@RestController
@RequestMapping("/api/exports")
@RequiredArgsConstructor
public class ExportController {

    private final ExcelExportService excelExportService;

    @GetMapping("/cycles")
    public void exportCyclesToExcel(
            @RequestParam(required = false) Integer year,
            HttpServletResponse response
    ) throws IOException {

        String fileName = ExcelGenerator.generateFileName();


        // Excel ayarları
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        // Excel streamini yaz
        var out = excelExportService.exportCycles(year);
        response.getOutputStream().write(out.toByteArray());
        response.getOutputStream().flush();
    }
}


