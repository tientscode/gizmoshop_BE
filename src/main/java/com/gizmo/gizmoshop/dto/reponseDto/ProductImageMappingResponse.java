package com.gizmo.gizmoshop.dto.reponseDto;

import com.gizmo.gizmoshop.entity.ProductImageMapping;
import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageMappingResponse {
    private long id;
    private Long idProduct;
    private Long idProductImage;
    private String fileDownloadUri;

    public ProductImageMappingResponse(ProductImageMapping productImageMapping) {
    }
}
