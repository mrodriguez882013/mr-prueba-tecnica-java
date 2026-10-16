package com.example.api_productos_proveedores.dtos.responses;

import com.example.api_productos_proveedores.dtos.responses.ProveedorResponseDto;
import lombok.Data;

@Data
public class ProductoResponseDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer existencias;
    private String fotografia;
    private ProveedorResponseDto proveedor;
}