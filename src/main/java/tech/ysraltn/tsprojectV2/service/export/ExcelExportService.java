package tech.ysraltn.tsprojectV2.service.export;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.ysraltn.tsprojectV2.dto.CycleDto;
import tech.ysraltn.tsprojectV2.service.CycleService;
import tech.ysraltn.tsprojectV2.util.ExcelGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelExportService {

    private final CycleService cycleService;

    public ByteArrayOutputStream exportCycles(Integer year) throws IOException {
        List<CycleDto> dtos = cycleService.getCyclesByYear(year);
        return ExcelGenerator.exportCyclesPivot(dtos); // Statik metotsa doğrudan çağır
    }
}


