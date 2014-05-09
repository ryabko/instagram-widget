package ru.kalcho.instagram.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 */
@Path("/api")
public class Resources {

    @GET
    @Produces("text/plain")
    public String test() {
        return "OK";
    }

    @GET
    @Path("/2")
    @Produces("application/json")
    public Obj test2() {
        return new Obj(1, "Sergey");
    }

    private class Obj {
        private int id;
        private String name;

        private Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
