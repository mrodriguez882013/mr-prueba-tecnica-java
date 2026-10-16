package com.example.api_productos_proveedores.services.Impl;

import com.example.api_productos_proveedores.dtos.requests.ProveedorRequestDto;
import com.example.api_productos_proveedores.dtos.responses.ProveedorResponseDto;
import com.example.api_productos_proveedores.entities.Proveedor;
import com.example.api_productos_proveedores.repositories.ProveedorRepository;
import com.example.api_productos_proveedores.services.interfaces.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Transactional(readOnly = true)
    public Page<ProveedorResponseDto> findAllProviders(Pageable pageable) {
        return proveedorRepository.findAll(pageable)
                .map(this::convertToResponseDto);
    }

    @Transactional(readOnly = true)
    public ProveedorResponseDto findProviderById(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + id));
        return convertToResponseDto(proveedor);
    }

    @Transactional
    public ProveedorResponseDto saveProvider(ProveedorRequestDto requestDto) {
        // Validar si el correo electrónico ya existe al registrar uno nuevo
        proveedorRepository.findByCorreoElectronico(requestDto.getCorreoElectronico()).ifPresent(p -> {
            throw new RuntimeException("El correo electrónico '" + requestDto.getCorreoElectronico() + "' ya se encuentra registrado.");
        });

        Proveedor proveedor = convertToEntity(requestDto);
        Proveedor saved = proveedorRepository.save(proveedor);
        return convertToResponseDto(saved);
    }

    @Transactional
    public ProveedorResponseDto updateProvider(Long id, ProveedorRequestDto requestDto) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + id));

        // CORREGIDO: Usar findByCorreoElectronico para que retorne Optional y funcione .ifPresent()
        proveedorRepository.findByCorreoElectronico(requestDto.getCorreoElectronico()).ifPresent(existingProveedor -> {
            if (!existingProveedor.getId().equals(id)) {
                throw new RuntimeException("El correo electrónico '" + requestDto.getCorreoElectronico() + "' ya está registrado por otro proveedor.");
            }
        });

        proveedor.setNombre(requestDto.getNombre());
        proveedor.setNumeroTelefono(requestDto.getNumeroTelefono());
        proveedor.setCorreoElectronico(requestDto.getCorreoElectronico());
        proveedor.setDireccion(requestDto.getDireccion());

        Proveedor updated = proveedorRepository.save(proveedor);
        return convertToResponseDto(updated);
    }

    // --- Mapeos Privados (Clean Architecture Helpers) ---

    private ProveedorResponseDto convertToResponseDto(Proveedor proveedor) {
        ProveedorResponseDto dto = new ProveedorResponseDto();
        dto.setId(proveedor.getId());
        dto.setNombre(proveedor.getNombre());
        dto.setNumeroTelefono(proveedor.getNumeroTelefono());
        dto.setCorreoElectronico(proveedor.getCorreoElectronico());
        dto.setDireccion(proveedor.getDireccion());
        return dto;
    }

    private Proveedor convertToEntity(ProveedorRequestDto dto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(dto.getNombre());
        proveedor.setNumeroTelefono(dto.getNumeroTelefono());
        proveedor.setCorreoElectronico(dto.getCorreoElectronico());
        proveedor.setDireccion(dto.getDireccion());
        return proveedor;
    }
}