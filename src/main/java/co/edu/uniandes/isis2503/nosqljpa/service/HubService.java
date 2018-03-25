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

import co.edu.uniandes.isis2503.nosqljpa.interfaces.IHubLogic;
import co.edu.uniandes.isis2503.nosqljpa.logic.HubLogic;
import co.edu.uniandes.isis2503.nosqljpa.model.dto.model.HubDTO;
import java.util.List;
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
@Path("/hub")
@Produces(MediaType.APPLICATION_JSON)
public class HubService 
{
    private final IHubLogic hubLogic;
    
    
     public HubService() {
        this.hubLogic = new HubLogic();
    }

    @POST
    public HubDTO add(HubDTO dto) {
        return hubLogic.add(dto);
    }

    /*/
    @POST
    @Path("{code}/rooms")
    public RoomDTO addRoom(@PathParam("code") String code, RoomDTO dto) {
        FloorDTO floor = floorLogic.findCode(code);
        RoomDTO result = roomLogic.add(dto);
        floor.addRoom(dto.getId());
        floorLogic.update(floor);
        return result;
    }
/*/
   
    @PUT
    public HubDTO update(HubDTO dto) {
        return hubLogic.update(dto);
    }

    @GET
    @Path("/{id}")
    public HubDTO find(@PathParam("id") String id) {
        return hubLogic.find(id);
    }

    @GET
    public List<HubDTO> all() {
        return hubLogic.all();
    }

    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        try {
            hubLogic.delete(id);
            return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("Sucessful: Hub was deleted").build();
        } catch (Exception e) {
            System.out.println("co.edu.uniandes.isis2503.nosqljpa.service.HubService.delete()");
            return null;
        }
    }
}
