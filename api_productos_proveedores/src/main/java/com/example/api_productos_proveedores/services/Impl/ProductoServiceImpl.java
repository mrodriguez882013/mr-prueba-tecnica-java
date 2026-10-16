package com.example.api_productos_proveedores.services.Impl;

import com.example.api_productos_proveedores.dtos.requests.ProductoRequestDto;
import com.example.api_productos_proveedores.dtos.responses.ProductoResponseDto;
import com.example.api_productos_proveedores.dtos.responses.ProveedorResponseDto;
import com.example.api_productos_proveedores.entities.Producto;
import com.example.api_productos_proveedores.entities.Proveedor;
import com.example.api_productos_proveedores.repositories.ProductoRepository;
import com.example.api_productos_proveedores.repositories.ProveedorRepository;
import com.example.api_productos_proveedores.services.interfaces.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Transactional(readOnly = true)
    public Page<ProductoResponseDto> findAllProducts(Pageable pageable) {
        return productoRepository.findAll(pageable)
                .map(this::convertToResponseDto);
    }

    @Transactional(readOnly = true)
    public ProductoResponseDto findProductById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
        return convertToResponseDto(producto);
    }

    @Transactional
    public ProductoResponseDto saveProduct(ProductoRequestDto requestDto) {
        // Validar y buscar el proveedor asociado antes de guardar
        Proveedor proveedor = proveedorRepository.findById(requestDto.getProveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + requestDto.getProveedorId()));

        Producto producto = convertToEntity(requestDto, proveedor);
        Producto saved = productoRepository.save(producto);
        return convertToResponseDto(saved);
    }

    @Transactional
    public ProductoResponseDto updateProduct(Long id, ProductoRequestDto requestDto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));

        producto.setNombre(requestDto.getNombre());
        producto.setDescripcion(requestDto.getDescripcion());
        producto.setPrecio(requestDto.getPrecio());
        producto.setExistencias(requestDto.getExistencias());
        producto.setFotografia(requestDto.getFotografia());

        // Actualizar proveedor si viene especificado
        if (requestDto.getProveedorId() != null) {
            Proveedor proveedor = proveedorRepository.findById(requestDto.getProveedorId())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + requestDto.getProveedorId()));
            producto.setProveedor(proveedor);
        }

        Producto updated = productoRepository.save(producto);
        return convertToResponseDto(updated);
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Producto no encontrado con el ID: " + id);
        }
        productoRepository.deleteById(id);
    }

    // --- Mapeos Privados (Clean Architecture Helpers) ---

    private ProductoResponseDto convertToResponseDto(Producto producto) {
        ProductoResponseDto dto = new ProductoResponseDto();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setExistencias(producto.getExistencias());
        dto.setFotografia(producto.getFotografia());

        if (producto.getProveedor() != null) {
            ProveedorResponseDto proveedorDto = new ProveedorResponseDto();
            proveedorDto.setId(producto.getProveedor().getId());
            proveedorDto.setNombre(producto.getProveedor().getNombre());
            proveedorDto.setNumeroTelefono(producto.getProveedor().getNumeroTelefono());
            proveedorDto.setCorreoElectronico(producto.getProveedor().getCorreoElectronico());
            proveedorDto.setDireccion(producto.getProveedor().getDireccion());

            dto.setProveedor(proveedorDto);
        }
        return dto;
    }

    private Producto convertToEntity(ProductoRequestDto requestDto, Proveedor proveedor) {
        Producto producto = new Producto();
        producto.setNombre(requestDto.getNombre());
        producto.setDescripcion(requestDto.getDescripcion());
        producto.setPrecio(requestDto.getPrecio());
        producto.setExistencias(requestDto.getExistencias());
        producto.setFotografia(requestDto.getFotografia());
        producto.setProveedor(proveedor);
        return producto;
    }
}