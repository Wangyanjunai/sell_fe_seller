package com.imooc.sell.converter;

import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.form.ProductForm;

/**
 * Created by IntelliJ IDEA
 *
 * @Author 王艳军
 * @Date 2018-01-10 17:20
 * @Description
 */
public class ProductForm2CartDTOConverter {

    public static CartDTO convert(ProductForm productForm){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId(productForm.getProductId());
        cartDTO.setProductQuantity(productForm.getProductQuantity());
        return cartDTO;
    }
}
