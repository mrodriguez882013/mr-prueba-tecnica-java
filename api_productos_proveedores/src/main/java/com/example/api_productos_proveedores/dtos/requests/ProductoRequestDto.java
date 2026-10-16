package com.example.api_productos_proveedores.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductoRequestDto {

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
    private String nombre;

    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio debe ser mayor o igual a cero")
    private Double precio;

    @NotNull(message = "Las existencias son obligatorias")
    @PositiveOrZero(message = "Las existencias deben ser mayores o iguales a cero")
    private Integer existencias;

    @Size(max = 255, message = "La fotografía no puede tener más de 255 caracteres")
    private String fotografia;

    @NotNull(message = "El ID del proveedor es obligatorio")
    private Long proveedorId;
}