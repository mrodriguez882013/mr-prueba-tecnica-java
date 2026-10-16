package com.example.api_productos_proveedores.services.interfaces;

import com.example.api_productos_proveedores.dtos.requests.ProductoRequestDto;
import com.example.api_productos_proveedores.dtos.responses.ProductoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoService {
    Page<ProductoResponseDto> findAllProducts(Pageable pageable);
    ProductoResponseDto findProductById(Long id);
    ProductoResponseDto saveProduct(ProductoRequestDto requestDto);
    ProductoResponseDto updateProduct(Long id, ProductoRequestDto requestDto);
    void deleteProduct(Long id);
}