package com.fiap;

import java.util.List;

import com.fiap.Models.Noticia;
import com.fiap.Services.NoticiaService;

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

@Path("/noticias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoticiaResource {
 
    @Inject 
    NoticiaService noticiaService;

    @GET 
    public List<Noticia> getAllNoticias() {
        return noticiaService.getAllNoticias();
    }

    @GET 
    @Path("/{id}")
    public Response getNoticiaById(@PathParam ("id") Long id) {
        return noticiaService.getNoticiaById(id)
                .map(noticia -> Response.ok(noticia).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST 
    public Response createNoticia(Noticia noticia) {
        Noticia createdNoticia = noticiaService.createNoticia(noticia);
        return Response.status(Response.Status.CREATED).entity(createdNoticia).build();
    }

    @PUT 
    @Path("/{id}")
    public Response updateNoticia(@PathParam("id") Long id, Noticia noticia) {
        noticiaService.updateNoticia(id, noticia);
        return Response.ok().build();
    }

    @DELETE 
    @Path("/{id}")
    public Response deleteNoticia(@PathParam("id") Long id) {
        noticiaService.deleteNoticia(id);
        return Response.noContent().build();
    }
}