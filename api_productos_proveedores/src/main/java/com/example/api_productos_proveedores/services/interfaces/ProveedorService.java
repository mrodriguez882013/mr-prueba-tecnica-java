package com.example.api_productos_proveedores.services.interfaces;

import com.example.api_productos_proveedores.dtos.requests.ProveedorRequestDto;
import com.example.api_productos_proveedores.dtos.responses.ProveedorResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProveedorService {
    Page<ProveedorResponseDto> findAllProviders(Pageable pageable);
    ProveedorResponseDto findProviderById(Long id);
    ProveedorResponseDto saveProvider(ProveedorRequestDto requestDto);
    ProveedorResponseDto updateProvider(Long id, ProveedorRequestDto requestDto);
}