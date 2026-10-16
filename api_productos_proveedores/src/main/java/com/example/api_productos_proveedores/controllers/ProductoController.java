package com.example.api_productos_proveedores.controllers;

import com.example.api_productos_proveedores.dtos.requests.ProductoRequestDto;
import com.example.api_productos_proveedores.dtos.responses.ProductoResponseDto;
import com.example.api_productos_proveedores.services.interfaces.ProductoService;
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
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Endpoints Enterprise para la gestión integral de productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    @Operation(summary = "Guardar un producto", description = "Crea y registra un nuevo producto asociado a un proveedor en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProductoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o proveedor no encontrado", content = @Content)
    })
    public ResponseEntity<ProductoResponseDto> saveProduct(
            @Valid @RequestBody ProductoRequestDto requestDto) {
        ProductoResponseDto newProduct = productoService.saveProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @GetMapping
    @Operation(summary = "Listar todos los productos paginados", description = "Retorna una página con los productos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista paginada obtenida exitosamente",
                    content = @Content(schema = @Schema(implementation = ProductoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Parámetros de paginación inválidos", content = @Content)
    })
    public ResponseEntity<Page<ProductoResponseDto>> findAllProducts(
            @ParameterObject
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable) {
        Page<ProductoResponseDto> products = productoService.findAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto por ID", description = "Obtiene los detalles de un producto específico mediante su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado",
                    content = @Content(schema = @Schema(implementation = ProductoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    public ResponseEntity<ProductoResponseDto> findProductById(
            @Parameter(description = "Identificador único del producto", example = "1")
            @PathVariable Long id) {
        ProductoResponseDto product = productoService.findProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Modifica la información de un producto existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = ProductoResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Producto o proveedor no encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content)
    })
    public ResponseEntity<ProductoResponseDto> updateProduct(
            @Parameter(description = "Identificador único del producto a actualizar", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDto requestDto) {
        ProductoResponseDto updatedProduct = productoService.updateProduct(id, requestDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto del sistema dado su ID (requerido por la prueba)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "Identificador único del producto a eliminar", example = "1")
            @PathVariable Long id) {
        productoService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}