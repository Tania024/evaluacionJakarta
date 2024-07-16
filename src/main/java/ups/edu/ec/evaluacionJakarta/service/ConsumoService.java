package ups.edu.ec.evaluacionJakarta.service;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ups.edu.ec.evaluacionJakarta.business.GestionConsumo;
import ups.edu.ec.evaluacionJakarta.model.Cliente;
import ups.edu.ec.evaluacionJakarta.model.Consumo;

public class ConsumoService {
	@Inject
    private GestionConsumo gconsumo;

    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Consumo consumo) {
        try {
            gconsumo.createConsumo(consumo);
            return Response.ok(consumo).build();
        } catch(Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }

    @PUT
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Consumo consumo) {
        try {
            gconsumo.updateConsumo(consumo);
            return Response.ok(consumo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        try {
            gconsumo.deleteConsumo(id);
            return Response.ok().entity(new Respuesta(Respuesta.OK, "Consumo eliminado correctamente")).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).entity(new Respuesta(Respuesta.ERROR, "Error en BD")).build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("/cliente/{cedula}")
    public List<Consumo> getByCliente(@PathParam("cedula") String cedula) {
        try {
            Cliente cliente = new Cliente();
            cliente.setCedula(cedula);
            return gconsumo.getConsumos(cliente);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Produces("application/json")
    public List<Consumo> list() {
        return gconsumo.getAllConsumos();
    }

}
