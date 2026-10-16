package com.example.api_productos_proveedores.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProveedorRequestDto {

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    @Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
    private String nombre;

    @Size(max = 255, message = "El número de teléfono no puede tener más de 255 caracteres")
    private String numeroTelefono;

    @Size(max = 255, message = "El correo electrónico no puede tener más de 255 caracteres")
    @Email(message = "El formato del correo electrónico es inválido")
    private String correoElectronico;

    @Size(max = 255, message = "La dirección no puede tener más de 255 caracteres")
    private String direccion;
}