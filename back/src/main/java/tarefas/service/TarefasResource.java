/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarefas.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import tarefas.Tarefas;

@Stateless
@Path("tarefas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TarefasResource extends AbstractFacade<Tarefas> {

    @PersistenceContext(unitName = "tarefasPU")
    private EntityManager em;

    public TarefasResource() {
        super(Tarefas.class);
    }

    @POST
    @Override
    public void create(Tarefas entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Tarefas entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    public Tarefas find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    public List<Tarefas> findAll() {
        return super.findAll();
    }

    @POST
    @Path("{id}/concluir")
    public void concluir(@PathParam("id") Integer id) {
        Tarefas tarefa = find(id);
        tarefa.setConcluida(!tarefa.getConcluida());
        super.edit(tarefa);
    }

    @GET
    @Path("/busca")
    public List<Tarefas> buscaPorDescricao(@QueryParam("descricao") String descricao, @QueryParam("concluida") Boolean concluida) {
        if (descricao != null) {
            List<Tarefas> resultList = em
                    .createNamedQuery("Tarefas.findByDescricao", Tarefas.class)
                    .setParameter("descricao", "%" + descricao + "%")
                    .getResultList();
            return resultList;
        } else if(concluida != null) {
            List<Tarefas> resultList = em
                    .createNamedQuery("Tarefas.findByConcluida", Tarefas.class)
                    .setParameter("concluida", concluida)
                    .getResultList(); 
            return resultList;
        }
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
