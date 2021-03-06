/*
 * The MIT License
 *
 * Copyright 2018 Universidad De Los Andes - Departamento de Ingeniería de Sistemas.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.edu.uniandes.isis2503.nosqljpa.model.dto.converter;

import co.edu.uniandes.isis2503.nosqljpa.interfaces.IAdministradorConverter;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.AdministradorDTO;
import co.edu.uniandes.isis2503.nosqljpa.model.entity.AdministradorEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author m.sicard10
 */
public class AdministradorConverter implements IAdministradorConverter {

    public static IAdministradorConverter CONVERTER = new AdministradorConverter();
    
    public AlarmaConverter alarmaconv;

    public AdministradorConverter() {
    }

    @Override
    public AdministradorDTO entityToDto(AdministradorEntity entity) {
        AdministradorDTO dto = new AdministradorDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setUnidadResidencial(entity.getUnidadResidencial().entityToDTO());
        dto.setAlarmas( alarmaconv.listEntitiesToListDTOs(entity.getAlarmas()));
        return dto;
    }

    @Override
    public AdministradorEntity dtoToEntity(AdministradorDTO dto) {
        AdministradorEntity entity = new AdministradorEntity();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setunidadResidencial(dto.getUnidadResidencial().DTOToentity());
        entity.setAlarmas(alarmaconv.listDTOsToListEntities(dto.getAlarmas()));
        return entity;
    }

    @Override
    public List<AdministradorDTO> listEntitiesToListDTOs(List<AdministradorEntity> entities) {
        ArrayList<AdministradorDTO> dtos = new ArrayList<>();
        for (AdministradorEntity entity : entities) {
            dtos.add(entityToDto(entity));
        }
        return dtos;
    }

    @Override
    public List<AdministradorEntity> listDTOsToListEntities(List<AdministradorDTO> dtos) {
        ArrayList<AdministradorEntity> entities = new ArrayList<>();
        for (AdministradorDTO dto : dtos) {
            entities.add(dtoToEntity(dto));
        }
        return entities;
    }
    
}
