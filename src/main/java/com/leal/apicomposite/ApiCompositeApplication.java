package com.leal.apicomposite;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Time;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@SpringBootApplication
@Slf4j
public class ApiCompositeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApiCompositeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // runAsync não retorna nada
        CompletableFuture<Void> futureVoid = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                // ignore
            }
            log.info("2 [thread-{}] Operação asyncrona não bloqueante concluída", Thread.currentThread().getName());
        });

        log.info("1 [thread-{}] Exemplo de operação asyncrona não bloqueante disparada", Thread.currentThread().getName());


        // supplyAsync com retorno
        CompletableFuture<String> futureString = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(4);

                } catch (InterruptedException e){
                    // ignore
                }
                return String.format("4 [thread-%s] Exemplo de operação asyncrona não bloqueante com retorno", Thread.currentThread().getName());
            }
        });
        log.info("2 [thread-{}] Exemplo de operação asyncrona não bloqueante com retorno", Thread.currentThread().getName());
        String futureStringResult = futureString.get();

        System.out.println(futureStringResult);
    }

}
