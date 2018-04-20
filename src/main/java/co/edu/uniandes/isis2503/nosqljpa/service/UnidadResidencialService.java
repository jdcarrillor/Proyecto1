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
package co.edu.uniandes.isis2503.nosqljpa.service;


import co.edu.uniandes.isis2503.nosqljpa.interfaces.IResidenciaLogic;

import co.edu.uniandes.isis2503.nosqljpa.interfaces.IUnidadResidencialLogic;

import co.edu.uniandes.isis2503.nosqljpa.logic.ResidenciaLogic;

import co.edu.uniandes.isis2503.nosqljpa.logic.UnidadResidencialLogic;

import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.ResidenciaDTO;

import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.UnidadResidencialDTO;
import com.sun.istack.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author m.sicard10
 */
@Path("/unidadResidencial")
@Produces(MediaType.APPLICATION_JSON)
public class UnidadResidencialService {
    
    private final IUnidadResidencialLogic unidadResidencialLogic;
    private final IResidenciaLogic residenciaLogic;

    public UnidadResidencialService() {
        this.unidadResidencialLogic = new UnidadResidencialLogic();
        this.residenciaLogic = new ResidenciaLogic();
    }

    @POST
    public UnidadResidencialDTO add(UnidadResidencialDTO dto) {
        return unidadResidencialLogic.add(dto);
    }

    @POST
    @Path("{id}/residencia")
    public ResidenciaDTO addResidencia(@PathParam("id") String id, ResidenciaDTO dto) {
        UnidadResidencialDTO uniadad = unidadResidencialLogic.find(id);
        ResidenciaDTO result = residenciaLogic.add(dto);
        uniadad.addResidencias(dto.getId());
        unidadResidencialLogic.update(uniadad);
        return result;
    }

    @PUT
    public UnidadResidencialDTO update(UnidadResidencialDTO dto) {
        return unidadResidencialLogic.update(dto);
    }

    @GET
    @Path("/{id}")
    public UnidadResidencialDTO find(@PathParam("id") String id) {
        return unidadResidencialLogic.find(id);
    }

    @GET
    public List<UnidadResidencialDTO> all() {
        return unidadResidencialLogic.all();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        try {
            unidadResidencialLogic.delete(id);
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("Sucessful: Unidad Residencial was deleted").build();
        } catch (Exception e) {
            Logger.getLogger(AdministradorService.class).log(Level.WARNING, e.getMessage());
            return Response.status(500).header("Access-Control-Allow-Origin", "*").entity("We found errors in your query, please contact the Web Admin.").build();
        }
    }
    
}
