import java.io.IOException;
import java.util.Scanner;
import aplicativos.*;

public class AppPilotos {
    
    public static void main(String[] args) throws InterruptedException, IOException {
        int MAX_ELEMENTOS = 20, MAX_AERONAVES = 2;
        int opcao, qtdCadastrados = 0, qtdAC = 0;
        Aeronave[] aeronaves = new Aeronave[MAX_AERONAVES];
        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS]; 
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar piloto(s) cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento para pilotos");
            System.out.println("5 - Cadastrar aeronave(s)");
            System.out.println("6 - Listar aeronave(s) cadastradas"); 
            System.out.println("7 - Aumentar espaço de armazenamento para aeronaves"); 
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine();

            if (opcao == 1) {
                // Caso a capacidade de pilotos for alcançada
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }

                //Cadastre seu piloto aqui
               try{ 
                Piloto piloto = new Piloto();        
        System.out.println("Nome do piloto:" );
        piloto.setNome(in.nextLine());
        System.out.println("CPF do piloto:" );
        piloto.setCpf(in.nextLine());
        System.out.println("Insira aqui o breve do piloto");
        piloto.setBreve(in.nextLine());
        System.out.println("Insira aqui a matrícula do piloto");
        piloto.setMatricula(in.nextInt());   
        in.nextLine();
            pilotos[qtdCadastrados] = piloto;
            qtdCadastrados++;

        System.out.println("\nCadastro do piloto realizado!");
        voltarMenu(in);
    } catch (Exception e) {
        in.nextLine(); 
        System.out.println("\n Erro ao cadastrar este piloto: Matrícula só aceita números, ou o limite máximo de cadastramento foi alcançado");
        System.out.println("\n Cadastro anulado, tente novamente");
        voltarMenu(in); 
    }
}

             else if (opcao == 2) {
                // Caso não possua pilotos cadastrados
                if (qtdCadastrados == 0) {
                    System.out.println("\nNo momento não há pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                // Exiba os pilotos aqui

                for(Pessoa piloto : pilotos){
                    if(piloto != null){    
                     System.out.printf("\nNome: %s\n", piloto.getNome());
                     System.out.printf("CPF: %s\n", piloto.getCpf());
                     System.out.printf("Breve: %s\n", ((Piloto) piloto).getBreve());
                     System.out.printf("Matricula: %d\n", ((Piloto) piloto).getMatricula());
                     System.out.println("___________________________");
                    }
                }

                voltarMenu(in);
            } else if (opcao == 3) {
                System.out.print("\n______________\n| CONSULTA CPF |\n______________\n");
                System.out.print("\nDigite o CPF para busca: ");
                String CpfDigitado = in.nextLine(); 

                for (int i = 0; i < pilotos.length + 1; i++) {
                    try {
                        if (pilotos[i].getCpf().toString().equals(CpfDigitado)) {
    System.out.printf("\nNº: %s Nome: %s CPF: %s Breve: %s Matricula: %d\n", i + 1,
                    pilotos[i].getNome(), 
                    pilotos[i].getCpf(), 
                    ((Piloto) pilotos[i]).getBreve(),
                    ((Piloto) pilotos[i]).getMatricula());
                    voltarMenu(in);
                    break;
                } else if (i < pilotos.length + 1) {
                         continue;
                        }
                    } catch (Exception e) {
                        System.out.println("\nPiloto não cadastrado, verifique o CPF e tente novamente.");
                        voltarMenu(in);
                        break;
                    }
                }
            } else if (opcao == 4) {
                System.out.print("\nInsira a quantidade de pilotos que deseja cadastrar: ");
                int novaQtd = 0;
                try {
                 novaQtd = in.nextInt();
                 in.nextLine();
                if (novaQtd != 0){ 
                 Pessoa novaListaPilotos[] = new Pessoa[pilotos.length + novaQtd];
                 for (int i = 0; i < pilotos.length; i++) {
                novaListaPilotos[i] = pilotos[i];
                }
                MAX_ELEMENTOS = novaListaPilotos.length; 
                pilotos = novaListaPilotos;

                System.out.printf("Você adicionou mais " + novaQtd + " espaço(s) de piloto");
                voltarMenu(in);
            }
           

            else if (novaQtd == 0) {
                System.out.println("\nO número tem que ser maior que \"0\"");
                voltarMenu(in);
                
                    }
                } catch (Exception e) {
                    System.out.println("ERROR: Verifique a quantidade digitada e tente novamente.");
                    in.nextLine();
                    voltarMenu(in);
                }
            }
    
            else if (opcao == 5){  
                if(qtdAC == MAX_AERONAVES){

                System.out.println("Não é possivel adicionar mais aeronaves, tente adicionar mais espaços");
                voltarMenu(in);
                continue;
                }
                Aeronave aeronave = new Aeronave(); 
            System.out.println("Insira aqui o modelo da aeronave: ");
            aeronave.setModelo(in.nextLine());
            System.out.println("Insira aqui a categoria do avião \nf - Asa fixa\nm - Asa movel ");
            aeronave.setCategoria(in.next().charAt(0));  
            in.nextLine();
            aeronaves[qtdAC] = aeronave;
                qtdAC++;
                System.out.println("Aeronave cadastrada com sucesso");
                
            voltarMenu(in);
            }
            else if (opcao == 6){
                //Caso não possua aeronaves cadastradas
                if (qtdAC == 0) {
                    System.out.println("\nNo momento não possui aeronaves cadastradas para exibir.");
                    voltarMenu(in);
                    continue;
                }
                for(Aeronave aeronave : aeronaves){
                    if(aeronave != null){    
            System.out.printf("\nModelo da aeronave: %s ", aeronave.getModelo()); 
            System.out.printf("\nCategoria da aeronave: %c ", aeronave.getCategoria());            
            System.out.println("\n___________________________");
            
                    }
                 } 
                 voltarMenu(in);
            }else if (opcao == 7){

                System.out.print("\nInsira a quantidade de aeronaves que deseja cadastrar: ");
                int novaQtdA = 0;
                try {
                 novaQtdA = in.nextInt();
                 in.nextLine();
                if (novaQtdA != 0){ 
                 Aeronave novaListaAeronaves[] = new Aeronave[aeronaves.length + novaQtdA];
                 for (int i = 0; i < aeronaves.length; i++) {
                novaListaAeronaves[i] = aeronaves[i];
                }
                MAX_AERONAVES = novaListaAeronaves.length; 
                aeronaves = novaListaAeronaves;

                System.out.printf("Você adicionou mais " + novaQtdA + " espaço(s) de aeronave(s)");
                voltarMenu(in);
            }
           
            else if (novaQtdA == 0) {
                System.out.println("\nO número tem que ser maior que \"0\"");
                voltarMenu(in);
                    }
                } catch (Exception e) {
                    System.out.println("ERROR: Verifique a quantidade digitada e tente novamente.");
                    in.nextLine();
                    voltarMenu(in);
                }
            }

            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
                voltarMenu(in);
            }
        

        } while (opcao != 0);

        System.out.println("Programa finalizado com sucesso!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}