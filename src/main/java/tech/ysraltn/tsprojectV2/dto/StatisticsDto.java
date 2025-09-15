package tech.ysraltn.tsprojectV2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticsDto {
    private long productCount;
    private long model100SCount;
    private long model100NXCount;
    private long modelNXCount;
    private long institutionCount;
    private long userCount;

}

