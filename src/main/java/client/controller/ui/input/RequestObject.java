//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.controller.ui.input;

import client.controller.ui.out.Colored;
import client.controller.ui.out.ConsoleColor;
import client.controller.ui.out.Out;
import client.controller.ui.out.Prefixed;
import commons.model.Coordinates;
import commons.model.Location;
import commons.model.Person;
import commons.model.Ticket;
import commons.model.TicketType;

public final class RequestObject {
    private final Input in;
    private final Out out;

    public RequestObject(Input in, Out out) {
        this.in = in;
        this.out = out;
    }

    public Coordinates coordinates() {
        Coordinates.CoordinatesBuilder coordinates = Coordinates.builder();
        this.out.print("coordinates: " + ConsoleColor.BLUE.wrapped("{\n"));
        RequestPrimitive primitive = this.requestPrimitive(ConsoleColor.BLUE);
        coordinates.x((Double)primitive.request(RequestPrimitive.doubleFor("x", Coordinates::validateX))).y((Double)primitive.request(RequestPrimitive.doubleFor("y", Coordinates::validateY)));
        this.out.print(ConsoleColor.BLUE.wrapped("}\n"));
        return coordinates.build();
    }

    public Location location() {
        Location.LocationBuilder location = Location.builder();
        this.out.print("location: " + ConsoleColor.CYAN.wrapped("{\n"));
        RequestPrimitive primitive = this.requestPrimitive(ConsoleColor.CYAN);
        location.name((String)primitive.request(RequestPrimitive.stringFor("name", Location::validateName))).x((Float)primitive.request(RequestPrimitive.floatFor("x", Location::validateX))).y((Double)primitive.request(RequestPrimitive.doubleFor("y", Location::validateY))).z((Float)primitive.request(RequestPrimitive.floatFor("z", Location::validateZ)));
        this.out.print(ConsoleColor.CYAN.wrapped("}\n"));
        return location.build();
    }

    public Person person() {
        Person.PersonBuilder person = Person.builder();
        this.out.print("person: " + ConsoleColor.YELLOW.wrapped("{\n"));
        RequestPrimitive primitive = this.requestPrimitive(ConsoleColor.YELLOW);
        RequestObject request = new RequestObject(this.in, this.inner(ConsoleColor.YELLOW));
        person.height((Integer)primitive.request(RequestPrimitive.intFor("height", Person::validateHeight))).passportId((String)primitive.request(RequestPrimitive.stringFor("passport", Person::validatePassportId))).location(request.location());
        this.out.print(ConsoleColor.YELLOW.wrapped("}\n"));
        return person.build();
    }

    public Ticket ticket() {
        Ticket.TicketBuilder ticket = Ticket.builder();
        this.out.print("ticket: " + ConsoleColor.PURPLE.wrapped("{\n"));
        RequestPrimitive primitive = this.requestPrimitive(ConsoleColor.PURPLE);
        RequestObject request = new RequestObject(this.in, this.inner(ConsoleColor.PURPLE));
        ticket.name((String)primitive.request(RequestPrimitive.stringFor("name", Ticket::validateName))).coordinates(request.coordinates()).price((Integer)primitive.request(RequestPrimitive.intFor("price", Ticket::validatePrice))).type((TicketType)primitive.request(RequestPrimitive.enumFor("type", TicketType.class))).person(request.person());
        this.out.print(ConsoleColor.PURPLE.wrapped("}\n"));
        return ticket.build();
    }

    private RequestPrimitive requestPrimitive(ConsoleColor color) {
        return new RequestPrimitive(this.in, this.inner(color));
    }

    private Out inner(ConsoleColor color) {
        return new Colored(color, new Prefixed("    ", this.out));
    }
}
