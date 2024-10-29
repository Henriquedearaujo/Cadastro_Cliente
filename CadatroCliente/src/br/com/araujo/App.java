package br.com.araujo;

import br.com.araujo.dao.IClienteDAO;
import br.com.araujo.dao.ClienteMapDAO;
import br.com.araujo.domain.Cliente;

import javax.swing.*;

public class App {

    private  static IClienteDAO iCLienteDAO;

    public static void main(String args[]) {
        iCLienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite: \n1 Para cadatrar \n2 Para consultar \n3 Para excluir \n4 ara alterar \n5 Para sair",
                "Cadastro de clientes", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção invalida digite: \n1 Para cadastrar \n2 Para consultar \n3 Para excluir \n4 Para alterar \n5 Para sair",
                    "Cadastro de clientes", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vígula, " +
                                "conforme o exemplo: Nome, CPF, Telefone, Endereço, Numero, Cidade e Estado",
                        "Cadastro",JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if (isConsultar(opcao)){
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o cpf",
                        "Consultar",JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            }

            opcao = JOptionPane.showInputDialog(null,
                    "Digite: \n1 Para cadastrar \n2 Para consultar \n3 Para excluir \n4 Para alterar \n5 Para sair",
                    "Cadastro de clientes", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void consultar(String dados) {
        Cliente cliente = iCLienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.toString(), "Sucesso",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado: ", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static boolean isConsultar(String opcao) {
        if ("2".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(
                dadosSeparados[0],
                Long.parseLong(dadosSeparados[1].trim()),
                Long.parseLong(dadosSeparados[2].trim()),
                dadosSeparados[3],
                Integer.parseInt(dadosSeparados[2].trim()),
                dadosSeparados[5],
                dadosSeparados[6]);
        Boolean isCadastrado = iCLienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.", "Successo",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra cadatrado", "Erro",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static boolean isCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até Logo", "Sair",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        }
        return false;
    }

}
