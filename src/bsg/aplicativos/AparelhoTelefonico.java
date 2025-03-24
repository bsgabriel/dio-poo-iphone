package bsg.aplicativos;

import bsg.entidades.ContatoTelefone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AparelhoTelefonico {

    private final List<ContatoTelefone> contatos;
    private boolean emLigacao;

    public AparelhoTelefonico() {
        this.contatos = new ArrayList<>();
        this.emLigacao = false;
    }

    public void abrir() {
        Scanner input = new Scanner(System.in);
        int opc;
        do {
            System.out.print("Agenta telefônica\n");
            System.out.println("O que deseja fazer?");
            System.out.println("1- Efetuar ligação");
            System.out.println("2- Encerrar ligação atual");
            System.out.println("3- Listar contatos");
            System.out.println("4- Adicionar contato");
            System.out.println("5- Remover contato");
            System.out.println("6- Enviar mensagem");
            System.out.println("0- Sair");
            System.out.print("Opção: ");
            opc = input.nextInt();
            switch (opc) {
                case 1:
                    efetuarLigacao();
                    break;
                case 2:
                    encerrarLigacao();
                    break;
                case 3:
                    exibirContatos();
                    break;
                case 4:
                    adicionarContato();
                    break;
                case 5:
                    removerContato();
                    break;
                case 6:
                    enviarMensagem();
                default:
                    System.out.println("Opção inválida");
            }
        } while (opc != 0);
    }

    private void exibirContatos() {
        if (this.contatos.isEmpty()) {
            System.out.println("Você não tem nenhum contato");
            return;
        }

        StringBuilder sb = new StringBuilder().append("Contatos: \n");
        this.contatos.forEach(c -> {
            int idx = this.contatos.indexOf(c) + 1;
            sb.append(String.format("%d- %s\n", idx, c.getNome()));

            c.getNumeros().forEach(n -> {
                sb.append("\t").append(n).append("\n");
            });
        });

        System.out.println(sb);
    }

    private void efetuarLigacao() {
        if (this.emLigacao) {
            System.out.println("Já está em ligação");
            return;
        }

        int opc;
        Long numero = null;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("O que deseja fazer?");
            System.out.println("1- Discar número");
            if (!this.contatos.isEmpty())
                System.out.println("2- Buscar nos contatos");

            System.out.println("0- Cancelar");
            System.out.print("Opção: ");
            opc = input.nextInt();

            switch (opc) {
                case 1:
                    numero = input.nextLong();
                    break;
                case 2:
                    if (contatos.isEmpty()) {
                        System.out.println("Opção inválida!");
                    } else {
                        numero = selecionarNumeroContato(selecionarContato());
                        break;
                    }
                case 3:
                    numero = null;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opc != 0);

        if (numero == null) {
            return;
        }

        System.out.println("Ligando para... " + numero);
    }

    private void encerrarLigacao() {
        this.emLigacao = false;
        System.out.println("Encerrou ligação");
    }

    private ContatoTelefone selecionarContato() {
        int opc;
        Scanner input = new Scanner(System.in);
        while (true) {
            exibirContatos();
            System.out.println("0- Cancelar");
            System.out.print("Opção: ");
            opc = input.nextInt();

            if (opc == 0) {
                return null;
            }

            if (opc > contatos.size() || opc < 0) {
                System.out.println("Opção inválida");
                continue;
            }

            return this.contatos.get(opc - 1);
        }
    }

    private Long selecionarNumeroContato(ContatoTelefone contato) {
        if (contato == null)
            return null;

        if (contato.getNumeros().size() == 1)
            return contato.getNumeros().get(0);

        int opc;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.printf("Selecione um dos números de %s\n", contato.getNome());
            contato.getNumeros().forEach(n -> {
                int idx = contato.getNumeros().indexOf(n) + 1;
                System.out.printf("%d- %d\n", idx, n);
            });
            System.out.println("0- Cancelar");
            System.out.print("Opção: ");
            opc = input.nextInt();

            if (opc == 0) {
                return null;
            }

            if (opc > contato.getNumeros().size() || opc < 0) {
                System.out.println("Opção inválida");
                continue;
            }

            return contato.getNumeros().get(opc - 1);

        }
    }

    private void adicionarContato() {
        Scanner input = new Scanner(System.in);

        System.out.print("Nome do contato: ");
        String nome = input.nextLine();

        boolean primeiroNumero = true;
        List<Long> numeros = new ArrayList<>();

        while (true) {
            if (!primeiroNumero)
                System.out.println("Informe outro número. Para cancelar, digite 0");

            System.out.print("Telefone: ");
            Long numero = input.nextLong();

            if (!primeiroNumero && numero.equals(0L)) {
                break;
            }

            if (String.valueOf(numero).length() != 11) {
                System.out.println("Número deve ter 11 dígitos");
                continue;
            }
            numeros.add(numero);
            primeiroNumero = false;
        }

        Long[] arrNumeros = new Long[numeros.size()];
        for (int i = 0; i < numeros.size(); i++) {
            arrNumeros[i] = numeros.get(i);
        }

        this.contatos.add(new ContatoTelefone(nome, arrNumeros));
    }

    private void enviarMensagem() {
        int opc;
        List<Long> numeros = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        boolean temContatoAdicionado = !this.contatos.isEmpty();
        while (true) {
            System.out.println("O que deseja fazer?");
            System.out.println("1- Digitar destinatário");

            if (temContatoAdicionado) {
                System.out.println("2- Selecionar destinatário");
                System.out.println("3- Escrever mensagem");
            } else {
                System.out.println("2- Escrever mensagem");
            }
            System.out.println("0- Cancelar");
            System.out.print("Opção: ");
            opc = input.nextInt();

            if (opc == 0) {
                return;
            }

            if (opc == 1) {
                System.out.print("Digite o número: ");
                numeros.add(input.nextLong());
                continue;
            }

            if (temContatoAdicionado && opc == 3 || !temContatoAdicionado && opc == 2) {
                break;
            }

            if (temContatoAdicionado && opc == 2) {
                numeros.add(selecionarNumeroContato(selecionarContato()));
            }

            System.out.println("Opção inválida");
        }

        System.out.print("Digite a mensagem. Para cancelar, basta enviar uma mensagem sem conteúdo: ");
        String mensagem = input.nextLine();
        if (mensagem.isBlank()) {
            return;
        }

        String strDestinatarios = numeros.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        System.out.printf("""
                Enviando mensagem...
                Conteúdo: %s
                Para os números: %s
                """, mensagem, strDestinatarios);
    }

    private void removerContato() {
        var contato = selecionarContato();
        if (contato != null) {
            this.contatos.remove(contato);
        }
    }

}
