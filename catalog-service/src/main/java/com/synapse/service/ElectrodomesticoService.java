package com.synapse.service;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.synapse.dto.ElectrodomesticoDTO;
import com.synapse.dto.ManualDTO;
import com.synapse.dto.PagedResponseDTO;
import com.synapse.model.Electrodomestico;
import com.synapse.repository.ElectrodomesticoRepository;

import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ElectrodomesticoService {

    @Inject
    ElectrodomesticoRepository elecRepo;

    public Electrodomestico getElecById(UUID id){
        return elecRepo.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Ese electrodomestico no existe"));
    }


    public List<Electrodomestico> listarElectrodomesticos(){
        return elecRepo.listAll();
    }

    public ElectrodomesticoDTO toDTO(Electrodomestico e){
        ElectrodomesticoDTO dto = new ElectrodomesticoDTO();
        dto.id = e.getId().toString();
        dto.marca = e.getMarca();
        dto.modelo = e.getModelo();
        dto.categoria = e.getCategoria();
        dto.price = e.getPrice();

        if (e.getManuales() != null) {
        dto.manuales = e.getManuales().stream().map(m -> {
            ManualDTO mDto = new ManualDTO();
            mDto.id = m.getId().toString();
            mDto.nombre = m.getCustom();
            // POV: Construyes la URL que apunta al GATEWAY (puerto 8080)
            mDto.downloadUrl = "http://localhost:8080/manuals/" + m.getId();
            return mDto;
        }).collect(Collectors.toList());
    }
        return dto;
    }

    public PagedResponseDTO<ElectrodomesticoDTO> findAdvanced(
        String marca, String modelo, String categoria,
        String sortField, String direction, int page, int size
    ){
        StringBuilder query = new StringBuilder("1=1"); 
        Parameters params = new Parameters();


        if (marca != null && !marca.isEmpty()) {
            query.append(" AND lower(marca) = lower(:marca)");
            params.and("marca", marca);
        }
        if (modelo != null && !modelo.isEmpty()) {
            query.append(" AND lower(modelo) LIKE lower(:modelo)"); 
            params.and("modelo", "%" + modelo + "%");
        }
        if (categoria != null && !categoria.isEmpty()) {
            query.append(" AND lower(categoria) = lower(:categoria)");
            params.and("categoria", categoria);
        }

        Sort sort = direction.equalsIgnoreCase("asc") 
                    ? Sort.by(sortField).ascending() 
                    : Sort.by(sortField).descending();

        var panacheQuery = elecRepo.find(query.toString(), sort, params);
        panacheQuery.page(Page.of(page, size));

        List<Electrodomestico> results = panacheQuery.list();
        PagedResponseDTO<ElectrodomesticoDTO> response = new PagedResponseDTO<>();
        response.data = results.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
        
        response.totalElements = panacheQuery.count();
        response.totalPages = panacheQuery.pageCount();
        response.currentPage = page;
        
        return response;
    }


    public File getPhoto(UUID id) {
        Electrodomestico profile = elecRepo.findById(id);

        String path = profile.getPhotoUrl();

        File imageFile = new File(path);

        return imageFile;
    }
}
