//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.controller.high;

import commons.protocol.low.Request;
import commons.protocol.low.Response;
import commons.protocol.method.LoginMethod;
import commons.protocol.method.MethodInvocation;
import commons.protocol.method.MethodResult;
import commons.protocol.method.RegisterMethod;
import commons.protocol.method.Status;
import commons.protocol.method.StatusMethod;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Logger;
import server.advanced.controller.Packet;
import server.advanced.persistence.AuthService;
import server.advanced.persistence.exception.IncorrectPasswordException;
import server.advanced.persistence.exception.UserAlreadyExistsException;
import server.advanced.persistence.exception.UserNotFoundException;
import server.advanced.service.MethodExecutor;

public final class RequestHandler implements Consumer<Request> {
    private static final Logger log = Logger.getLogger(RequestHandler.class.getName());
    private final SocketAddress client;
    private final AuthService service;
    private final Consumer<Packet<Response>> responseConsumer;
    private Optional<MethodExecutor> executor;

    public RequestHandler(SocketAddress client, AuthService userRepository, Consumer<Packet<Response>> responseConsumer) {
        this.client = client;
        this.service = userRepository;
        this.responseConsumer = responseConsumer;
        this.executor = Optional.empty();
    }

    public void accept(Request request) {
        MethodInvocation<?> invocation = (MethodInvocation)request.payloadAs(MethodInvocation.class);
        log.info(String.format("Starts handling invocation of %s: %s", this.client, invocation));
        if (invocation instanceof StatusMethod.Invocation) {
            this.accept(request.id(), new StatusMethod.Result(Status.OK, "Прости, Егор Бугаенко..."));
        } else if (invocation instanceof RegisterMethod.Invocation) {
            try {
                RegisterMethod.Invocation i = (RegisterMethod.Invocation)invocation;
                this.service.register(i.login(), i.password());
                this.accept(request.id(), new RegisterMethod.Result(Status.OK));
            } catch (UserAlreadyExistsException var6) {
                this.accept(request.id(), new RegisterMethod.Result(Status.USER_ALREADY_EXISTS));
            }
        } else if (invocation instanceof LoginMethod.Invocation) {
            try {
                LoginMethod.Invocation i = (LoginMethod.Invocation)invocation;
                this.executor = Optional.of(new MethodExecutor(this.service.ticketRepository(i.login(), i.password())));
                System.out.printf("Executor for %s is ready: %s", i.login(), this.executor);
                this.accept(request.id(), new LoginMethod.Result());
            } catch (UserNotFoundException var4) {
                this.accept(request.id(), new LoginMethod.Result(Status.NO_SUCH_USER));
            } catch (IncorrectPasswordException var5) {
                this.accept(request.id(), new LoginMethod.Result(Status.INCORRECT_PASSWORD));
            }
        } else if (this.executor.isPresent()) {
            this.accept(request.id(), ((MethodExecutor)this.executor.get()).execute(invocation));
        } else {
            this.accept(request.id(), invocation.emptyResultWithStatus(Status.NOT_AUTHORIZED));
        }

    }

    private void accept(int requestId, MethodResult result) {
        Iterator var3 = result.split().iterator();

        while(var3.hasNext()) {
            MethodResult r = (MethodResult)var3.next();
            log.info(String.format("Result (%s) for %s: %s", result.status(), this.client, r));
            this.responseConsumer.accept(new Packet(this.client, new Response(requestId, r)));
        }

    }
}
