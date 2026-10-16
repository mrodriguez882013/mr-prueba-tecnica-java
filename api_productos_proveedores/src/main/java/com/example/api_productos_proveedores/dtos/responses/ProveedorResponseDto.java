package com.example.api_productos_proveedores.dtos.responses;

import lombok.Data;

@Data
public class ProveedorResponseDto {
    private Long id;
    private String nombre;
    private String numeroTelefono;
    private String correoElectronico;
    private String direccion;
}