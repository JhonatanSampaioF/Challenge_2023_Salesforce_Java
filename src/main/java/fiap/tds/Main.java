package fiap.tds;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/*public class Main {
    public static void main(String[] args) {
        var produto = new Produto();
        var prodRepo = new ProdutoRepositoryOracle();
        var especialista = new Especialista();
        var especRepo = new EspecialistaRepositoryOracle();
        var feedback = new Feedback();
        var fbRepo = new FeedbackRepositoryOracle();
        var cliente = new Cliente();
        var clieRepo = new ClienteRepositoryOracle();
        Scanner scanner = new Scanner(System.in);

        var cont = 1;
        while (cont == 1) {
            System.out.println("1 - Cliente" +
                "\n2 - Especialista" +
                "\n3 - Feedback" +
                "\n4 - Produto");
            System.out.println("Digite a opção desejada: ");
            int opc = scanner.nextInt();
            switch (opc) {
                case 1: {
                    System.out.println("1 - Criar" +
                        "\n2 - Exibir" +
                        "\n3 - Atualizar" +
                        "\n4 - Excluir");
                    System.out.println("Digite a opção desejada: ");
                    opc = scanner.nextInt();
                    switch (opc) {
                        case 1: {
                            clieRepo.create(cliente);
                            break;
                        }
                        case 2: {
                            System.out.println("1 - Buscar cliente" +
                                "\n2 - Exibir todos");
                            System.out.println("Digite a opção desejada: ");
                            opc = scanner.nextInt();
                            switch (opc) {
                                case 1: {
                                    System.out.println("Digite o id do cliente desejado: ");
                                    var id = scanner.next();
                                    clieRepo.findById(id);
                                    break;
                                }
                                case 2: {
                                    clieRepo.readAll();
                                    break;
                                }
                                default: {
                                    System.out.println("Opção inválida!");
                                    break;
                                }
                            }
                            break;
                        }
                        case 3: {
                            clieRepo.update(cliente);
                            break;
                        }
                        case 4: {
                            System.out.println("Digite o id do cliente que deseja excluir: ");
                            var id = scanner.next();
                            clieRepo.delete(id);
                            break;
                        }
                        default: {
                            System.out.println("Opção inválida!");
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("1 - Criar" +
                        "\n2 - Exibir" +
                        "\n3 - Atualizar" +
                        "\n4 - Excluir");
                    System.out.println("Digite a opção desejada: ");
                    opc = scanner.nextInt();
                    switch (opc) {
                        case 1: {
                            especRepo.create(especialista);
                            break;
                        }
                        case 2: {
                            System.out.println("1 - Buscar especialista" +
                                "\n2 - Exibir todos");
                            System.out.println("Digite a opção desejada: ");
                            opc = scanner.nextInt();
                            switch (opc) {
                                case 1: {
                                    System.out.println("Digite o id do especialista desejado: ");
                                    var id = scanner.next();
                                    especRepo.findById(id);
                                    break;
                                }
                                case 2: {
                                    especRepo.readAll();
                                    break;
                                }
                                default: {
                                    System.out.println("Opção inválida!");
                                    break;
                                }
                            }
                            break;
                        }
                        case 3: {
                            especRepo.update(especialista);
                            break;
                        }
                        case 4: {
                            System.out.println("Digite o id do especialista que deseja excluir: ");
                            var id = scanner.next();
                            especRepo.delete(id);
                            break;
                        }
                        default: {
                            System.out.println("Opção inválida!");
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println("1 - Criar" +
                        "\n2 - Exibir" +
                        "\n3 - Atualizar" +
                        "\n4 - Excluir");
                    System.out.println("Digite a opção desejada: ");
                    opc = scanner.nextInt();
                    switch (opc) {
                        case 1: {
                            fbRepo.create(feedback);
                            break;
                        }
                        case 2: {
                            System.out.println("1 - Buscar feedback" +
                                "\n2 - Exibir todos");
                            System.out.println("Digite a opção desejada: ");
                            opc = scanner.nextInt();
                            switch (opc) {
                                case 1: {
                                    System.out.println("Digite o id do feedback desejado: ");
                                    var id = scanner.next();
                                    fbRepo.findById(id);
                                    break;
                                }
                                case 2: {
                                    fbRepo.readAll();
                                    break;
                                }
                                default: {
                                    System.out.println("Opção inválida!");
                                    break;
                                }
                            }
                            break;
                        }
                        case 3: {
                            fbRepo.update(feedback);
                            break;
                        }
                        case 4: {
                            System.out.println("Digite o id do feedback que deseja excluir: ");
                            var id = scanner.next();
                            fbRepo.delete(id);
                            break;
                        }
                        default: {
                            System.out.println("Opção inválida!");
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    System.out.println("1 - Criar" +
                        "\n2 - Exibir" +
                        "\n3 - Atualizar" +
                        "\n4 - Excluir");
                    System.out.println("Digite a opção desejada: ");
                    opc = scanner.nextInt();
                    switch (opc) {
                        case 1: {
                            prodRepo.create(produto);
                            break;
                        }
                        case 2: {
                            System.out.println("1 - Buscar produto" +
                                "\n2 - Exibir todos");
                            System.out.println("Digite a opção desejada: ");
                            opc = scanner.nextInt();
                            switch (opc) {
                                case 1: {
                                    System.out.println("Digite o id do produto desejado: ");
                                    var id = scanner.next();
                                    prodRepo.findById(id);
                                    break;
                                }
                                case 2: {
                                    prodRepo.readAll();
                                    break;
                                }
                                default: {
                                    System.out.println("Opção inválida!");
                                    break;
                                }
                            }
                            break;
                        }
                        case 3: {
                            prodRepo.update(produto);
                            break;
                        }
                        case 4: {
                            System.out.println("Digite o id do produto que deseja excluir: ");
                            var id = scanner.next();
                            prodRepo.delete(id);
                            break;
                        }
                        default: {
                            System.out.println("Opção inválida!");
                            break;
                        }
                    }
                    break;
                }
                default: {
                    System.out.println("Opção inválida!");
                    break;
                }
            }
            System.out.println("Deseja realizar outra operação? (1-SIM/0-NAO): ");
            cont = scanner.nextInt();
        }
    }

}*/

public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/";
    public static final Logger LOGGER = LogManager.getLogger(Main.class);


    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("fiap.tds");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        LOGGER.info("Iniciano aplicação...");
        System.out.println(String.format("Jersey app started with endpoints available at "
            + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.stop();
        LOGGER.info("Aplicação finalizada....");
    }
}

