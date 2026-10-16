package com.example.api_productos_proveedores.controllers;

import com.example.api_productos_proveedores.dtos.requests.ProveedorRequestDto;
import com.example.api_productos_proveedores.dtos.responses.ProveedorResponseDto;
import com.example.api_productos_proveedores.services.interfaces.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proveedores")
@Tag(name = "Proveedores", description = "Endpoints Enterprise para la gestión integral de proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    @Operation(summary = "Guardar un proveedor", description = "Crea y registra un nuevo proveedor en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proveedor creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProveedorResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o correo duplicado", content = @Content)
    })
    public ResponseEntity<ProveedorResponseDto> saveProvider(
            @Valid @RequestBody ProveedorRequestDto requestDto) { // <-- 2. Agregado @Valid aquí
        ProveedorResponseDto newProvider = proveedorService.saveProvider(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProvider);
    }

    @GetMapping
    @Operation(summary = "Listar todos los proveedores paginados", description = "Retorna una página con los proveedores registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista paginada obtenida exitosamente",
                    content = @Content(schema = @Schema(implementation = ProveedorResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Parámetros de paginación inválidos", content = @Content)
    })
    public ResponseEntity<Page<ProveedorResponseDto>> findAllProviders(
            @ParameterObject
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable) {
        Page<ProveedorResponseDto> providers = proveedorService.findAllProviders(pageable);
        return ResponseEntity.ok(providers);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar proveedor por ID", description = "Obtiene los detalles de un proveedor específico mediante su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado",
                    content = @Content(schema = @Schema(implementation = ProveedorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado", content = @Content)
    })
    public ResponseEntity<ProveedorResponseDto> findProviderById(
            @Parameter(description = "Identificador único del proveedor", example = "1")
            @PathVariable Long id) {
        ProveedorResponseDto provider = proveedorService.findProviderById(id);
        return ResponseEntity.ok(provider);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proveedor", description = "Modifica la información de un proveedor existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProveedorResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Conflicto por correo electrónico duplicado o datos inválidos", content = @Content)
    })
    public ResponseEntity<ProveedorResponseDto> updateProvider(
            @Parameter(description = "Identificador único del proveedor a actualizar", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ProveedorRequestDto requestDto) { // <-- 3. Agregado @Valid aquí también
        ProveedorResponseDto updatedProvider = proveedorService.updateProvider(id, requestDto);
        return ResponseEntity.ok(updatedProvider);
    }
}