package uz.pdp.app_jwt.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDto {
    private Integer poorCardId;
    private Integer richCardId;
    private Double amount;
    private Double commissionAmount;
}
